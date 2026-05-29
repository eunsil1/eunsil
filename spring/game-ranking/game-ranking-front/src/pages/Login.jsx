import {Link, useNavigate} from "react-router-dom";
import {useState} from "react";
import {login} from "../api/api.js";
import './Auth.css';

function Login(){
    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleSubmit = async () => {
        try{
            await login(username, password);
            alert('로그인 성공!');
            //로그인 상태를 브라우저에 간단히 저장
            localStorage.setItem('username', username);
            navigate('/'); //게임목록으로 이동
        }catch(err){
            setMessage('아이디 또는 비밀번호가 틀렸어요.');
        }
    }

    return (
        <div className="container">
            <div className="auth-box">
                <h1 className="neon-title">LOGIN</h1>
                <input className="input" placeholder="아이디"
                value={username} onChange={(e) => setUsername(e.target.value)}/>
                <input className="input" type="password" placeholder="비밀번호"
                value={password} onChange={(e) => setPassword(e.target.value)}/>
                <button className="btn btn-full" onClick={handleSubmit}>로그인</button>
                {message && <p className="auth-msg">{message}</p>}
                <p className="auth-switch">계정이 없나요? <Link to="/signup" className="link">회원가입</Link></p>
            </div>
        </div>
    )
}

export default Login;