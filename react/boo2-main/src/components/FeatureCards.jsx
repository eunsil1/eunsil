import { Container, Row, Col, Card, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const items = [
  {
    title: 'UI 구축',
    text: 'Bootstrap 그리드와 컴포넌트로 빠르게 레이아웃을 잡습니다.',
    img: 'https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&q=80',
    to: '/services',
  },
  {
    title: '라우팅',
    text: 'React Router로 홈·서비스·문의 페이지를 연결했습니다.',
    img: 'https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&q=80',
    to: '/contact',
  },
  {
    title: '슬라이더',
    text: 'react-slick으로 메인 히어로 배너를 구성했습니다.',
    img: 'https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&q=80',
    to: '/services',
  },
];

export default function FeatureCards() {
  return (
    <section className="py-5 bg-light">
      <Container>
        <h2 className="text-center fw-bold mb-2">주요 구성</h2>
        <p className="text-center text-muted mb-5">이미지 카드로 핵심 기능을 소개합니다.</p>
        <Row className="g-4">
          {items.map((item) => (
            <Col key={item.title} md={4}>
              <Card className="h-100 border-0 shadow-sm overflow-hidden">
                <Card.Img variant="top" src={item.img} alt="" />
                <Card.Body>
                  <Card.Title className="fs-5">{item.title}</Card.Title>
                  <Card.Text className="text-muted small">{item.text}</Card.Text>
                  <Button as={Link} to={item.to} variant="outline-primary" size="sm">
                    자세히
                  </Button>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      </Container>
    </section>
  );
}
