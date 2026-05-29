import axios from "axios";

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    withCredentials: true, //로그인 세션 쿠키 주고받기용
});

//게임 목록 조회
export const getGames = () => api.get('/games');

//게임별 랭킹 조회
export const getRanking = (gameId) => api.get(`/scores/ranking/${gameId}`);

//회원가입(JSON 방식)
export const signup = (data) => api.post('/auth/signup', data);

//점수등록(로그인 세션으로 유저 자동 판별)
export const postScore = (gameId, score) => api.post('/scores', {gameId, score}); //userId 안보냄

//로그인(폼 데이터 방식)
export const login = (username, password) => {
    return api.post('/auth/login', {username, password});
}

//로그아웃
export const logout = () => api.post('/auth/logout');

//내 점수 기록 조회
export const getMyScores = () => api.get('/scores/my');

export default api;