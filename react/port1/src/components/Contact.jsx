import React from 'react'
import './Contact.css';
import {useState} from 'react'
import { FaEnvelope, FaPhone, FaMapMarkerAlt } from 'react-icons/fa'

function Contact() {
  const [formData, setFormData] = useState({
    name:'',
    email:'',
    message:''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name] : e.target.value
    });
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('form submitted:',formData)
    alert('메세지가 전송되었습니다.')
    setFormData({
      name:'',
      email:'',
      message:''
    })
  }

  return (
    <section id='contact' className='contact section'>
      <div className="container">
        <h2 className="section-title">연락처</h2>
        <div className="contact-content">

          <div className="contact-info">
            <h3>함께 일하고 싶으신가요?</h3>
            <p>
              새로운 프로젝트나 협업 기회에 대해 이야기하고 싶으시다면
              언제든지 연락주세요. 빠르게 답변드리겠습니다.
            </p>
            <div className="contact-details">
              <div className="contact-item">
                <FaEnvelope className="contact-icon" />
                <div>
                  <h4>이메일</h4>
                  <p>your.email@example.com</p>
                </div>
              </div>
              <div className="contact-item">
                <FaPhone className="contact-icon" />
                <div>
                  <h4>전화번호</h4>
                  <p>010-1234-5678</p>
                </div>
              </div>
              <div className="contact-item">
                <FaMapMarkerAlt className="contact-icon" />
                <div>
                  <h4>위치</h4>
                  <p>서울, 대한민국</p>
                </div>
              </div>
            </div>
          </div>
          <form className="contact-form" onSubmit={handleSubmit}>

            <div className="form-group">
              <label htmlFor="name">이름</label>
              <input 
              type="text"
              id='name'
              name='name'
              value={formData.name}
              onChange={handleChange}
              required
              placeholder='이름을 입력하세요'
              />
            </div>

            <div className="form-group">
              <label htmlFor="email">이메일</label>
              <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                placeholder="이메일을 입력하세요"
              />
            </div>

            <div className="form-group">
              <label htmlFor="message">메시지</label>
              <textarea
                id="message"
                name="message"
                value={formData.message}
                onChange={handleChange}
                required
                rows="6"
                placeholder="메시지를 입력하세요"
              ></textarea>
              
            </div>
            <button type="submit" className='btn btn-primary'>
              메세지 보내기
            </button>
          </form>
        </div>
      </div>
    </section>
  )
}

export default Contact