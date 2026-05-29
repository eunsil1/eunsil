import {Link, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getRanking, postScore} from "../api/api.js";
import './Ranking.css'

function Ranking(){
    const {gameId} = useParams();
    const [ranking, setRanking] = useState([]);
    const [loading, setLoading] = useState(true);
    const [score, setScore] = useState('')

    //랭킹 불러오기(함수로 분리 - 등록 후 재호출 위해)
    const loadRanking = () => {
        getRanking(gameId)
            .then((res) => {
                setRanking(res.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error('랭킹 불러오기 실패: ', err);
                setLoading(false);
            })
    }

    useEffect(() => {
        loadRanking();
    }, [gameId]);

    //점수 등록
    const handleSubmit = async () => {
        //로그인 체크
        if(!localStorage.getItem('username')){
            alert('로그인이 필요해요!');
            return;
        }
        if(!score){
            alert('점수를 입력해주세요!');
            return;
        }

        try{
            await postScore(gameId, parseInt(score));
            setScore(''); //입력창 비우기
            loadRanking(); //랭킹 새로고침
        }catch(err){
            console.error('점수 등록 실패: ', err);
            alert('점수 등록 실패! 로그인 상태를 확인해주세요.');
        }
    }

    if(loading) return <p>불러오는 중...</p>

    const medal = (rank) =>
        rank === 1 ? '🥇' : rank === 2 ? '🥈' : rank === 3 ? '🥉' : rank;

    return (
        <div className="container">
        {/*    뒤로가기 링크 */}
            <Link to="/" className="link">← 게임 목록으로</Link>
            <h1 className="neon-title">🏆 TOP 10 RANKING</h1>

            {/*점수등록 */}
            <div className="score-form">
                <input
                    className="input"
                    type="number"
                    placeholder="점수 입력"
                    value={score}
                    onChange={(e) => setScore(e.target.value)}
                    style={{marginBottom: 0, flex: 1}}
                />
                <button onClick={handleSubmit} className="btn">
                    등록하기
                </button>

            </div>
            {/*랭킹리스트*/}
            {ranking.length === 0 ? (
                <p className="empty">아직 등록된 점수가 없어요.</p>
            ) : (
                <div className="rank-list">
                    {ranking.map((entry) => (
                        <div key={entry.rank} className={`rank-row rank-${entry.rank <= 3 ? entry.rank : 'normal' }`}>
                            <span className="rank-num">{medal(entry.rank)}</span>
                            <span className="rank-name">{entry.username}</span>
                            <span className="rank-score">{entry.score.toLocaleString()}</span>
                        </div>
                    ))}
                </div>
            )}
        </div>
    )
}

export default Ranking;
