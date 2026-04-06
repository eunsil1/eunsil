/**
 * 로그인 API만 필요할 때 쓰는 최소 예제 (교육용).
 * 실제 앱 전체는 `client.js`의 `api`를 사용합니다.
 */

async function request(path, options = {}) { //모든 api 요청의 공통처리 담당
  const { json, ...init } = options;
  const headers = { ...init.headers };
  if (json !== undefined) {
    headers['Content-Type'] = 'application/json';
    init.body = JSON.stringify(json);
  } //전송
  // {
  // "email": "...",
  // "password": "..."
  // } 내용이 전송
  const res = await fetch(path, {
    ...init,
    headers,
    credentials: 'include', //세션 쿠키 포함 - 로그인 성공해도 유지안됨
  });
  const text = await res.text(); //먼저 텍스트로 받고
  let data = null;
  if (text) {
    try {
      data = JSON.parse(text); //json 파일이면 파싱
    } catch {
      data = text;
    }
  }
  if (!res.ok) { //에러처리
    const err = new Error(data?.error || res.statusText || 'Request failed');
    err.status = res.status;
    err.data = data;
    throw err;
  }
  return data;
}

export const api = {
  login: (body) => request('/api/auth/login', { method: 'POST', json: body }),
};
