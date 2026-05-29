import {Link, useNavigate} from "react-router-dom";
import {useState} from "react";
import {signup} from "../api/api.js";
import './Auth.css';

function Signup(){
    const navigate = useNavigate();
    
    //입력값 상태
    const [form, setForm] = useState({
        username: '',
        password: '',
        email: '',
    });
    const [message, setMessage] = useState('');
    
    //입력값 변경 핸들러
    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value});
    };
    
    //회원가입 제출
    const handleSubmit = async () => {
        try{
            await signup(form);
            alert('회원가입 성공! 로그인 해주세요.');
            navigate('/login'); //로그인 페이지로 이동
        }catch (err) {
            //백엔드에서 보낸 에러 메세지 표시
            setMessage(err.response?.data || '회원가입 실패');
        }
    };
    
    return (
        <div className="container">
            <div className="auth-box">
                <h1 className="neon-title">SIGN UP</h1>
                <input className="input" name="username" placeholder="아이디"
                value={form.username} onChange={handleChange}/>
                <input className="input" name="password" placeholder="비밀번호"
                value={form.password} onChange={handleChange}/>
                <input className="input" name="email" placeholder="이메일"
                value={form.email} onChange={handleChange}/>
                <button className="btn btn-full" onClick={handleSubmit}>가입하기</button>
                {message && <p className="auth-msg">{message}</p>}
                <p className="auth-switch">이미 계정이 있나요?<Link to="/login" className="link">로그인</Link></p>
            </div>
        </div>
    )
}

export default Signup;