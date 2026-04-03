import Slider from "react-slick/lib/slider";
import { Link } from 'react-router-dom';
import { Container } from 'react-bootstrap';

// 🔥 중요: default fallback 처리
const Slick = Slider?.default || Slider;

const slides = [
  {
    title: '빠르고 안정적인 웹',
    subtitle: 'React와 Bootstrap으로 구성하는 모던 UI',
    image: 'https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=1600&q=80',
    cta: '서비스 보기',
    to: '/services',
  },
  {
    title: '슬릭(Slick) 슬라이더',
    subtitle: '메인 배너 자동 재생',
    image: 'https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=1600&q=80',
    cta: '문의하기',
    to: '/contact',
  },
];

export default function HeroSlider() {
  const settings = {
    dots: true,
    infinite: true,
    speed: 600,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 4000,
    arrows: true,
  };

  // 🔥 Slider 깨졌을 때 fallback UI
  if (!Slick) {
    return <div>슬라이더 로딩 실패</div>;
  }

  return (
    <div className="hero-slider">
      <Slick {...settings}>
        {slides.map((s) => (
          <div key={s.title}>
            <div
              className="hero-slide text-white"
              style={{
                backgroundImage: `url(${s.image})`,
                height: '400px',
                display: 'flex',
                alignItems: 'center',
              }}
            >
              <Container>
                <h2>{s.title}</h2>
                <p>{s.subtitle}</p>
                <Link to={s.to} className="btn btn-primary">
                  {s.cta}
                </Link>
              </Container>
            </div>
          </div>
        ))}
      </Slick>
    </div>
  );
}