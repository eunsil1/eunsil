/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { motion } from "motion/react";
import { 
  ChevronRight, 
  Download, 
  ArrowRight, 
  Menu, 
  Globe, 
  ExternalLink, 
  Target, 
  Cpu, 
  Users, 
  Database, 
  Monitor, 
  BarChart3, 
  Zap,
  ChevronLeft
} from "lucide-react";
import { useState } from "react";

const Navbar = () => {
  return (
    <nav className="fixed top-0 left-0 w-full z-50 bg-white/90 backdrop-blur-md border-b border-gray-100">
      <div className="container-custom h-20 flex justify-between items-center">
        <div className="flex items-center gap-12">
          <div className="text-2xl font-black tracking-tighter">NHN <span className="text-ace-blue">ACE</span></div>
          <div className="hidden md:flex gap-8 text-sm font-semibold">
            <a href="#" className="hover:text-ace-blue transition-colors">Solutions</a>
            <a href="#" className="hover:text-ace-blue transition-colors">Resources</a>
            <a href="#" className="hover:text-ace-blue transition-colors">Company</a>
          </div>
        </div>
        <div className="flex items-center gap-6">
          <div className="hidden md:flex items-center gap-2 text-xs font-bold cursor-pointer hover:text-ace-blue">
            <Globe className="w-4 h-4" />
            <span>KOR</span>
          </div>
          <Menu className="w-6 h-6 cursor-pointer" />
        </div>
      </div>
    </nav>
  );
};

const Hero = () => {
  return (
    <section className="relative h-[80vh] min-h-[600px] flex items-center overflow-hidden pt-20">
      <div className="absolute inset-0 z-0">
        <img 
          src="https://picsum.photos/seed/office-tech/1920/1080" 
          alt="Hero Background" 
          className="w-full h-full object-cover brightness-50"
          referrerPolicy="no-referrer"
        />
      </div>
      <div className="container-custom relative z-10 text-white">
        <motion.div
          initial={{ opacity: 0, y: 30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
          className="max-w-3xl"
        >
          <h1 className="text-4xl md:text-6xl font-bold leading-tight mb-6">
            광고 마케팅 사업의 미래를 <br />
            이끄는 거대한 힘 <span className="text-ace-blue">NHN ACE</span>
          </h1>
          <p className="text-lg md:text-xl text-gray-300 font-light mb-8">
            NHN ACE, Energetic movement for Advertisement Marketing.
          </p>
          <div className="flex items-center gap-4">
            <div className="text-sm font-bold">01 / 04</div>
            <div className="h-[2px] w-24 bg-white/20 relative">
              <div className="absolute left-0 top-0 h-full w-1/4 bg-white"></div>
            </div>
            <div className="flex gap-2">
              <button className="p-2 border border-white/20 rounded-full hover:bg-white/10 transition-colors">
                <ChevronLeft className="w-4 h-4" />
              </button>
              <button className="p-2 border border-white/20 rounded-full hover:bg-white/10 transition-colors">
                <ChevronRight className="w-4 h-4" />
              </button>
            </div>
          </div>
        </motion.div>
      </div>
    </section>
  );
};

const QuickInfo = () => {
  const cards = [
    {
      title: "DEVELOPMENT GUIDE",
      links: ["Publisher Integration Guide", "ADLIB SDK Integration Guide", "ACETRADER Creative Specifications"]
    },
    {
      title: "NHN ACE DOWNLOAD",
      links: ["ACE Trader", "ACE eXchange", "ACE ADLIB"],
      isDownload: true
    },
    {
      title: "NOTICE",
      links: ["NHN ACE 홈페이지 개편 안내"],
      isNotice: true
    }
  ];

  return (
    <section className="bg-white -mt-20 relative z-20 pb-20">
      <div className="container-custom">
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {cards.map((card, i) => (
            <motion.div
              key={card.title}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: i * 0.1 }}
              className={`p-8 rounded-xl card-shadow ${i === 0 ? 'bg-gray-800 text-white' : 'bg-gray-100 text-gray-800'}`}
            >
              <h3 className="text-sm font-bold mb-6 tracking-wider">{card.title}</h3>
              <ul className="space-y-3">
                {card.links.map((link) => (
                  <li key={link} className="flex items-center justify-between group cursor-pointer">
                    <span className="text-sm font-medium group-hover:underline">{link}</span>
                    {card.isDownload ? <Download className="w-4 h-4 opacity-40" /> : <ExternalLink className="w-4 h-4 opacity-40" />}
                  </li>
                ))}
              </ul>
              {card.isNotice && (
                <div className="mt-12 flex justify-end">
                  <div className="w-10 h-10 bg-black rounded-full flex items-center justify-center text-white cursor-pointer hover:bg-ace-blue transition-colors">
                    <ArrowRight className="w-5 h-5" />
                  </div>
                </div>
              )}
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  );
};

const ProductShowcase = () => {
  return (
    <section className="section-padding bg-gray-50">
      <div className="container-custom text-center">
        <h2 className="text-3xl md:text-4xl font-bold mb-16">NEW & BETTER WAY of Targeting AD!</h2>
        
        <div className="bg-white p-12 rounded-3xl card-shadow max-w-5xl mx-auto flex flex-col md:flex-row items-center gap-12">
          <div className="w-full md:w-1/2 flex justify-center">
            <div className="relative w-48 h-48 bg-gray-100 rounded-full flex items-center justify-center overflow-hidden">
              <div className="absolute inset-0 bg-gradient-to-tr from-ace-blue/20 to-transparent"></div>
              <Target className="w-24 h-24 text-ace-blue" />
            </div>
          </div>
          <div className="w-full md:w-1/2 text-left">
            <h3 className="text-2xl font-bold mb-4">ACE <span className="text-gray-400">TRADER</span></h3>
            <p className="text-text-muted text-sm leading-relaxed mb-8">
              NHN Trader는 국내 서비스를 제공하는 광고주에 최적화된 플랫폼으로, 웹/앱 광고 서비스를 통해 정교하고 효과적인 타겟팅을 제공합니다. 국내외 전문가들과 함께 다양한 데이터 기반의 분석과 자동화 기능을 통해 최대의 광고 효율을 선사합니다.
            </p>
            <button className="bg-black text-white px-8 py-3 rounded-full text-xs font-bold hover:bg-ace-blue transition-colors flex items-center gap-2">
              더 알아보기 <ArrowRight className="w-4 h-4" />
            </button>
          </div>
        </div>
        
        <div className="mt-16 flex flex-wrap justify-center gap-12 opacity-30 grayscale">
          <div className="text-xl font-black">ACE <span className="font-light">TRADER</span></div>
          <div className="text-xl font-black">ACE <span className="font-light">EXCHANGE</span></div>
          <div className="text-xl font-black">ACE <span className="font-light">ADLIB</span></div>
        </div>
      </div>
    </section>
  );
};

const TechGrid = () => {
  const techs = [
    { title: "맞춤형 타겟팅 광고", desc: "광고 목표에 최적화된 타겟팅", icon: <Target className="w-8 h-8" /> },
    { title: "머신러닝 자동 최적화", desc: "성과 기반 자동 최적화", icon: <Cpu className="w-8 h-8" /> },
    { title: "실시간 오디언스", desc: "행동 기반 실시간 분석", icon: <Users className="w-8 h-8" /> },
    { title: "최대 규모 양질 데이터", desc: "광범위한 행동 데이터 확보", icon: <Database className="w-8 h-8" /> },
    { title: "프리미엄 미디어", desc: "검증된 고품질 매체", icon: <Monitor className="w-8 h-8" /> },
    { title: "정교한 비딩 시스템", desc: "효율 높은 광고 노출 전략", icon: <BarChart3 className="w-8 h-8" /> },
    { title: "양방향 RTB", desc: "입찰 효율 최적화 구조", icon: <Zap className="w-8 h-8" /> }
  ];

  return (
    <section className="section-padding">
      <div className="container-custom">
        <div className="text-center mb-16">
          <h2 className="text-3xl md:text-4xl font-bold mb-4">데이터로 완성하는 광고 성과, NHN ACE의 기술력</h2>
          <p className="text-text-muted">정교한 타겟팅부터 자동 최적화까지, NHN ACE의 기술이 퍼포먼스를 만듭니다.</p>
        </div>
        
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {techs.map((tech, i) => (
            <motion.div
              key={tech.title}
              initial={{ opacity: 0, scale: 0.95 }}
              whileInView={{ opacity: 1, scale: 1 }}
              viewport={{ once: true }}
              className="p-8 bg-gray-50 rounded-2xl hover:bg-white hover:card-shadow transition-all group"
            >
              <div className="flex justify-between items-start mb-8">
                <div>
                  <h3 className="text-lg font-bold mb-2">{tech.title}</h3>
                  <p className="text-xs text-text-muted">{tech.desc}</p>
                </div>
                <div className="text-ace-blue opacity-20 group-hover:opacity-100 transition-opacity">
                  {tech.icon}
                </div>
              </div>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  );
};

const QuoteSection = () => {
  return (
    <section className="relative h-[400px] flex items-center overflow-hidden">
      <div className="absolute inset-0 z-0">
        <img 
          src="https://picsum.photos/seed/business-walking/1920/600" 
          alt="Quote Background" 
          className="w-full h-full object-cover brightness-50"
          referrerPolicy="no-referrer"
        />
      </div>
      <div className="container-custom relative z-10 text-white">
        <h2 className="text-3xl md:text-5xl font-bold leading-tight">
          데이터로 이루어내는 성과, <br />
          NHN ACE가 함께합니다.
        </h2>
      </div>
    </section>
  );
};

const Insights = () => {
  const posts = [
    {
      id: 6,
      title: "앞으로의 영상 광고, ACE Trader와 함께",
      tags: ["#AceTrader", "#크로스디바이스", "#CTV"],
      image: "https://picsum.photos/seed/ad-tech-1/600/400"
    },
    {
      id: 5,
      title: "FAST, 새로운 무료 방송의 시대",
      tags: ["#FAST", "#LG채널", "#삼성플러스TV"],
      image: "https://picsum.photos/seed/ad-tech-2/600/400"
    },
    {
      id: 4,
      title: "OTT와 CTV의 등장, CTV : 인터넷과 TV",
      tags: ["#connectedTV", "#CTV", "#Qtone"],
      image: "https://picsum.photos/seed/ad-tech-3/600/400"
    }
  ];

  return (
    <section className="section-padding bg-white">
      <div className="container-custom">
        <div className="text-center mb-16">
          <h2 className="text-3xl md:text-4xl font-bold mb-4 uppercase tracking-tight">AD MARKETING INSIGHT & TRENDS</h2>
          <p className="text-text-muted">NHN ACE의 시각으로 읽는 업계 동향, 그리고 인사이트를 확인하세요.</p>
        </div>
        
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {posts.map((post) => (
            <div key={post.id} className="group cursor-pointer">
              <div className="aspect-video overflow-hidden rounded-xl mb-6">
                <img 
                  src={post.image} 
                  alt={post.title} 
                  className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
                  referrerPolicy="no-referrer"
                />
              </div>
              <h3 className="text-lg font-bold mb-4 group-hover:text-ace-blue transition-colors">{post.id}. {post.title}</h3>
              <div className="flex flex-wrap gap-2">
                {post.tags.map(tag => (
                  <span key={tag} className="text-[10px] text-text-muted bg-gray-100 px-2 py-1 rounded">{tag}</span>
                ))}
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

const ContactForm = () => {
  return (
    <section className="section-padding bg-ace-blue text-white">
      <div className="container-custom">
        <div className="mb-16">
          <span className="text-sm font-bold uppercase tracking-widest opacity-60">Contact us</span>
          <h2 className="text-3xl md:text-5xl font-bold mt-4 leading-tight">
            당신의 마케팅, <br />
            NHN ACE와 함께하세요
          </h2>
          <p className="mt-6 opacity-80 max-w-xl">
            광고 성과를 높이고 싶은 모든 브랜드를 위해 NHN ACE는 데이터 기반 솔루션과 전문 인사이트를 함께합니다.
          </p>
        </div>
        
        <div className="bg-white text-text-main p-8 md:p-12 rounded-3xl card-shadow">
          <form className="grid grid-cols-1 md:grid-cols-2 gap-8">
            <div className="space-y-6">
              <div>
                <label className="label-text">서비스 구분</label>
                <select className="input-field appearance-none">
                  <option>선택</option>
                </select>
              </div>
              <div>
                <label className="label-text">이름 (회사명)</label>
                <input type="text" className="input-field" placeholder="이름 (회사명)" />
              </div>
              <div>
                <label className="label-text">이메일</label>
                <input type="email" className="input-field" placeholder="이메일" />
              </div>
            </div>
            
            <div className="space-y-6">
              <div>
                <label className="label-text">문의 구분</label>
                <select className="input-field appearance-none">
                  <option>선택</option>
                </select>
              </div>
              <div>
                <label className="label-text">전화번호 (-포함)</label>
                <input type="text" className="input-field" placeholder="전화번호 (-포함)" />
              </div>
              <div>
                <label className="label-text">제목</label>
                <input type="text" className="input-field" placeholder="제목" />
              </div>
            </div>
            
            <div className="md:col-span-2">
              <label className="label-text">문의내용</label>
              <textarea className="input-field h-32 resize-none" placeholder="문의내용"></textarea>
            </div>
            
            <div className="md:col-span-2 flex flex-col items-center gap-8">
              <div className="flex items-center gap-3 text-sm text-text-muted">
                <input type="checkbox" className="w-4 h-4" />
                <span>개인정보 수집 및 이용안내에 동의합니다.</span>
              </div>
              
              <div className="w-full max-w-xs p-4 border border-gray-100 rounded-lg flex items-center justify-between bg-gray-50">
                <div className="flex items-center gap-3">
                  <input type="checkbox" className="w-6 h-6" />
                  <span className="text-xs font-bold">로봇이 아닙니다.</span>
                </div>
                <div className="text-right">
                  <div className="text-[8px] opacity-40">reCAPTCHA</div>
                  <div className="text-[8px] opacity-40">개인정보보호 - 약관</div>
                </div>
              </div>
              
              <button className="w-full max-w-md bg-black text-white py-4 rounded-full font-bold hover:bg-ace-blue transition-all">
                문의하기
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
};

const Footer = () => {
  return (
    <footer className="py-16 bg-white border-t border-gray-100">
      <div className="container-custom">
        <div className="flex flex-col md:flex-row justify-between items-start gap-12">
          <div>
            <div className="text-2xl font-black tracking-tighter mb-8">NHN <span className="text-ace-blue">ACE</span></div>
            <div className="text-xs text-text-muted leading-relaxed space-y-1">
              <p>경기도 성남시 분당구 대왕판교로 645번길 16 플레이뮤지엄 NHN ACE</p>
              <p>사업자등록번호 : 313-81-35670 | 이메일 : atx@nhnace.com</p>
              <p>Copyright ⓒ NHN ACE All rights reserved.</p>
            </div>
          </div>
          
          <div className="flex flex-wrap gap-8 text-xs font-bold">
            <a href="#" className="hover:text-ace-blue">회사소개</a>
            <a href="#" className="hover:text-ace-blue">이용약관</a>
            <a href="#" className="hover:text-ace-blue text-ace-blue">개인정보 처리방침</a>
            <a href="#" className="hover:text-ace-blue">채용</a>
          </div>
          
          <div className="relative group">
            <div className="border border-gray-200 px-6 py-2 rounded-full text-xs font-bold flex items-center gap-4 cursor-pointer">
              Family Site <ChevronRight className="w-4 h-4 rotate-90" />
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default function App() {
  return (
    <div className="selection:bg-ace-blue selection:text-white">
      <Navbar />
      <main>
        <Hero />
        <QuickInfo />
        <ProductShowcase />
        <TechGrid />
        <QuoteSection />
        <Insights />
        <ContactForm />
      </main>
      <Footer />
    </div>
  );
}
