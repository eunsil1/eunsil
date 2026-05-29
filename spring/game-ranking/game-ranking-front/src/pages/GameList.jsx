import {useEffect, useState} from "react";
import {getGames} from "../api/api.js";
import {Link} from "react-router-dom";
import './GameList.css'


function GameList(){
    const[games, setGames] = useState([]);
    const[loading, setLoading] = useState(true);

    useEffect(() => {
        //화면이 뜨면 게임 목록 불러오기
        getGames()
            .then((res) => {
                setGames(res.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error('게임 목록 불러오기 실패: ', err);
                setLoading(false);
            });
    },[]);

    const username = localStorage.getItem('username');

    if(loading) return <div className="container"><p>불러오는 중...</p></div>;

    return(

        <div className="container">
        {/*    상단바*/}
            <div className="topbar">
                {username ? (
                    <span>👤 <strong style={{color: 'var(--neon-cyan)'}}>{username}</strong>님 환영합니다!</span>
                ) : <span>로그인이 필요해요</span>}
                {username ? (
                    <span>
                    <Link to="/mypage" className="link">마이페이지</Link>
                    {' · '}
                    <button className="btn btn-ghost" onClick={() => {
                        localStorage.removeItem('username');
                        window.location.reload();
                    }}>로그아웃</button>
                    </span>
                ) : (
                    <Link to="/login" className="btn">로그인 / 회원가입</Link>
                )}
            </div>

            <h1 className="neon-title">🎮 GAME RANKING</h1>
           
            <div className="game-grid">
                {games.map((game) => (
                    <Link to={`/ranking/${game.id}`} key={game.id} className="game-card">
                        <div className="game-card-category">{game.category}</div>
                        <h3 className="game-card-name">{game.name}</h3>
                        <p className="game-card-desc">{game.description}</p>
                        <span className="game-card-arrow">랭킹 보기</span>
                    </Link>
                ))}
            </div>
        </div>

    );
}

export default GameList;