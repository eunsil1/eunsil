import {Link, useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getGames, postScore} from "../api/api.js";

function GamePlay(){
    const {gameId} = useParams();
    const navigate = useNavigate();
    const [game, setGame] = useState(null);
    const [loading, setLoading] = useState(true);

    const username = localStorage.getItem('username');

    useEffect(() => {
        getGames()
            .then((res) => {
                const found = res.data.find((g) => g.id === parseInt(gameId));
                setGame(found);
                setLoading(false);
            })
            .catch((err) => {console.error('게임 정보 실패: ' + err); setLoading(false);});
    }, [gameId]);

    //게임이 끝나면 호출되는 점수(모든 게임 공통!)
    const handleGameOver = async (score) => {
        try{
            await postScore(gameId, score);
            alert(`점수 ${score}점이 등록되었어요! 🎉`);
            navigate(`/ranking/${gameId}`);
        }catch(err){
            console.error('점수 등록 실패: ', err);
            alert('점수 등록 실패! 로그인 상태를 확인해주세요.');
        }
    }

    if(loading) return <div className="container"><p>불러오는 중...</p></div>
    if(!game) return <div className="container"><p>게임을 찾을 수 없어요.</p></div>

    if(!username){
        return(
            <div className="container">
                <p className="empty">게임을 하려면 로그인이 필요해요! <Link to="/login" className="link">로그인하기</Link></p>
            </div>
        )
    }

    //게임 이름에 따라 알맞은 게임 보여주기
    const renderGame = () => {
        switch (game.name){
            case '클릭 챌린지':
                return <ClickChallenge onGameOver={handleGameOver}/>;
            // case '반응속도 테스트': return <ReactionTest onGameOver={handleGameOver} />;
            // case '타겟 클릭': return <TargetClick onGameOver={handleGameOver} />;
            // case '숫자 기억': return <NumberMemory onGameOver={handleGameOver} />;
            default:
                return <p>준비 중인 게임이에요!</p>
        }
    }

    return (
        <div className="container">
            <Link to={`/ranking/${gameId}`} className="link">← 랭킹으로</Link>
            <h1 className="neon-title">{game.name}</h1>
            <p className="game-desc">{game.description}</p>
            {renderGame()}
        </div>
    )
}

export default GamePlay;