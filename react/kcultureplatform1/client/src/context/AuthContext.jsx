import { createContext, useCallback, useContext, useEffect, useMemo, useState } from 'react';
// import { api } from '../api/client';
import { api } from '../api/loginOnly';
//로그인 상태를 context 관리하고, 어디서든 로그인 / 로그아웃 / 회원가입을 이용할 수 있는 구조

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [member, setMember] = useState(null);
  //현재 로그인 사용자 - null (로그인 안됨) 객체(로그인)
  const [loading, setLoading] = useState(true); //로그인 상태 확인

  //자동 로그인 체크 - 페이지를 새로고침해도 로그인 유지
  const refresh = useCallback(async () => {
    try {
      const { member: m } = await api.me(); //새로고침해도 api.me 호출
      setMember(m); //세션 쿠키가 유효하면 다시 사용자 정보 받아옴
    } catch {
      setMember(null);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    refresh();
  }, [refresh]);

  const login = useCallback(async (email, password) => {
    const { member: m } = await api.login({ email, password }); //로그인 성공
    setMember(m); //멤버 저장
    return m;
  }, []);

  const logout = useCallback(async () => {
    await api.logout();
    setMember(null); //상태 초기화
  }, []);

  const join = useCallback(async (payload) => {
    const { member: m } = await api.join(payload); //회원가입 후 
    setMember(m); //로그인
    return m;
  }, []);

  const value = useMemo( //성능 최적화
    () => ({
      member,
      loading,
      refresh,
      login,
      logout,
      join,
    }),
    [member, loading, refresh, login, logout, join]
  );
  //앱 전체에 공유
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within AuthProvider');
  return ctx;
}
//const {login, logout, join} = useAuth()