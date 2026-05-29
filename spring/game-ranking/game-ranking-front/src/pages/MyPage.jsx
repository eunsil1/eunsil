import {useEffect, useState} from "react";
import {getMyScores} from "../api/api.js";
import {Link} from "react-router-dom";
import './MyPage.css'

function MyPage(){
    const [scores, setScores] = useState([]);
    const [loading, setLoading] = useState(true);
    const username = localStorage.getItem('username');

    useEffect(() => {
        getMyScores()
            .then((res) => {setScores(res.data); setLoading(false)})
            .catch((err) => { console.error('내 기록 불러오기 실패: '+ err); setLoading(false)})

    },[]);

    if(!username){
        return(
            <div className="container">
                <p className="empty">로그인이 필요해요. <Link to="/login" className="link">로그인하기</Link> </p>
            </div>
        )
    }

    if(loading) return <div className="container"><p>불러오는 중...</p></div>;

    //통계 계산
    const totalPlays = scores.length;
    const uniqueGames = new Set(scores.map((s) => s.gameName)).size;
    const bestScore = scores.length > 0 ? Math.max(...scores.map((s) => s.score)) : 0;

    //날짜 포맷(2026-05-29T13:08 → 2026.05.29)
    const formatDate = (dateStr) => {
        const d = new Date(dateStr);
        return `${d.getFullYear()}.${String(d.getMonth() + 1).padStart(2, '0')}.${String(d.getDate()).padStart(2, '0')}`;

    };

    return(
        <div className="container">
            <Link to="/" className="link">← 게임 목록으로</Link>
            <h1 className="neon-title">👤 {username}님의 기록</h1>

        {/*    통계 카드*/}
            <div className="stat-grid">
                <div className="stat-card">
                    <span className="stat-value">{totalPlays}</span>
                    <span className="stat-label">총 플레이</span>
                </div>
                <div className="stat-card">
                    <span className="stat-value">{uniqueGames}</span>
                    <span className="stat-label">플레이한 게임</span>
                </div>
                <div className="stat-card">
                    <span className="stat-value">{bestScore.toLocaleString()}</span>
                    <span className="stat-label">최고 점수</span>
                </div>
            </div>

        {/*    기록리스트*/}
            <h2 className="section-title">📜 내 점수 기록</h2>
            {scores.length === 0 ? (
                <p className="empty">아직 등록한 점수가 없어요</p>
            ) : (
                <div className="record-list">
                    {scores.map((s,idx) => (
                        <div key={idx} className="record-row">
                            <div className="record-game">
                                <span className="record-name">{s.gameName}</span>
                                <span className="record-cat">{s.category}</span>
                            </div>
                            <span className="record-score">{s.score.toLocaleString()}</span>
                            <span className="record-date">{formatDate(s.playedAt)}</span>
                        </div>
                    ))}
                </div>
            )}
        </div>
    )

}
export default MyPage;

