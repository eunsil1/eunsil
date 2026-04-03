import { useState } from 'react';
import { Container, Form, Button, Row, Col, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default function ContactPage() {
  const [sent, setSent] = useState(false);
  const [form, setForm] = useState({ name: '', email: '', message: '' });

  const handleSubmit = (e) => {
    e.preventDefault();
    setSent(true);
  };

  return (
    <div className="py-5">
      <Container>
        <Row className="g-5">
          <Col lg={5}>
            <h1 className="fw-bold mb-3">문의하기</h1>
            <p className="text-muted mb-4">
              데모 폼입니다. 제출해도 실제 서버로 전송되지 않으며, 성공 메시지만 표시됩니다.
            </p>
            <div className="d-flex flex-column gap-3 small text-muted">
              <span>
                <i className="bi bi-building me-2 text-primary" />
                Boot2 데모 스튜디오
              </span>
              <span>
                <i className="bi bi-telephone me-2 text-primary" />
                02-0000-0000
              </span>
              <span>
                <i className="bi bi-clock me-2 text-primary" />
                평일 09:00 – 18:00
              </span>
            </div>
            <hr className="my-4" />
            <p className="small mb-0">
              <Link to="/services">← 서비스 소개</Link>
              <span className="mx-2 text-muted">|</span>
              <Link to="/">홈으로</Link>
            </p>
          </Col>
          <Col lg={7}>
            <div className="bg-white border rounded-3 shadow-sm p-4 p-md-5">
              {sent ? (
                <Alert variant="success" className="mb-0">
                  <i className="bi bi-check-circle me-2" />
                  <strong>전송 완료</strong>
                  <br />
                  {form.name}님, 문의가 접수되었습니다. (데모)
                </Alert>
              ) : (
                <Form onSubmit={handleSubmit}>
                  <Form.Group className="mb-3" controlId="contactName">
                    <Form.Label>이름</Form.Label>
                    <Form.Control
                      required
                      value={form.name}
                      onChange={(e) => setForm({ ...form, name: e.target.value })}
                      placeholder="홍길동"
                    />
                  </Form.Group>
                  <Form.Group className="mb-3" controlId="contactEmail">
                    <Form.Label>이메일</Form.Label>
                    <Form.Control
                      type="email"
                      required
                      value={form.email}
                      onChange={(e) => setForm({ ...form, email: e.target.value })}
                      placeholder="you@example.com"
                    />
                  </Form.Group>
                  <Form.Group className="mb-4" controlId="contactMessage">
                    <Form.Label>메시지</Form.Label>
                    <Form.Control
                      as="textarea"
                      rows={5}
                      required
                      value={form.message}
                      onChange={(e) => setForm({ ...form, message: e.target.value })}
                      placeholder="문의 내용을 입력하세요"
                    />
                  </Form.Group>
                  <Button type="submit" variant="primary" size="lg" className="w-100">
                    보내기
                  </Button>
                </Form>
              )}
            </div>
          </Col>
        </Row>
      </Container>
    </div>
  );
}
