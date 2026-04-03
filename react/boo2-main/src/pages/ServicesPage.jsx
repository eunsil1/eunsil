import { Container, Row, Col, Card, Badge } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const plans = [
  {
    name: '스타터',
    price: '₩0',
    desc: '학습용 기본 템플릿',
    features: ['반응형 레이아웃', '네비 + 푸터', '슬라이드 1종'],
  },
  {
    name: '프로',
    price: '문의',
    desc: '실서비스에 가까운 구성',
    features: ['페이지 분리 라우팅', '이미지 카드', 'Slick 옵션 커스텀'],
    highlight: true,
  },
  {
    name: '엔터프라이즈',
    price: '별도',
    desc: '팀 단위 커스터마이징',
    features: ['API 연동', '인증', '배포 파이프라인'],
  },
];

export default function ServicesPage() {
  return (
    <div className="py-5">
      <Container>
        <div className="text-center mb-5">
          <Badge bg="primary" className="mb-2">
            Services
          </Badge>
          <h1 className="fw-bold">서비스 소개</h1>
          <p className="text-muted mb-0">
            Boot2 예제에서 사용한 기술 스택을 플랜 형태로 정리했습니다.
          </p>
        </div>

        <Row className="g-4 mb-5">
          {plans.map((p) => (
            <Col key={p.name} md={4}>
              <Card
                className={`h-100 border-0 shadow-sm ${p.highlight ? 'border-primary border-2' : ''}`}
              >
                <Card.Body className="p-4">
                  {p.highlight && (
                    <Badge bg="primary" className="mb-2">
                      추천
                    </Badge>
                  )}
                  <Card.Title className="fs-4">{p.name}</Card.Title>
                  <p className="display-6 fw-bold text-primary mb-2">{p.price}</p>
                  <Card.Text className="text-muted small mb-4">{p.desc}</Card.Text>
                  <ul className="list-unstyled small mb-4">
                    {p.features.map((f) => (
                      <li key={f} className="mb-2">
                        <i className="bi bi-check-circle-fill text-success me-2" />
                        {f}
                      </li>
                    ))}
                  </ul>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>

        <div className="rounded-3 bg-light p-4 p-md-5 text-center">
          <h3 className="fw-bold mb-3">다음 단계가 궁금하다면</h3>
          <p className="text-muted mb-4">
            문의 페이지에서 간단한 폼 예제도 함께 확인할 수 있습니다.
          </p>
          <Link to="/contact" className="btn btn-primary btn-lg">
            문의 페이지로 이동
          </Link>
        </div>
      </Container>
    </div>
  );
}
