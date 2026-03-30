/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { motion } from "motion/react";
import { Github, Linkedin, Mail, ExternalLink, ChevronDown } from "lucide-react";
import { useState } from "react";

const Navbar = () => {
  return (
    <nav className="fixed top-0 left-0 w-full z-50 px-6 py-8 flex justify-between items-center mix-blend-difference">
      <motion.div 
        initial={{ opacity: 0, x: -20 }}
        animate={{ opacity: 1, x: 0 }}
        className="text-xl font-serif italic tracking-tighter"
      >
        Portfolio.
      </motion.div>
      <div className="flex gap-8 text-xs uppercase tracking-widest font-medium">
        {["Home", "About", "Portfolio", "Contact"].map((item, i) => (
          <motion.a
            key={item}
            href={`#${item.toLowerCase()}`}
            initial={{ opacity: 0, y: -10 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: i * 0.1 }}
            className="hover:text-accent transition-colors"
          >
            {item}
          </motion.a>
        ))}
      </div>
    </nav>
  );
};

const Hero = () => {
  return (
    <section id="home" className="min-h-screen flex flex-col justify-center px-6 md:px-24 relative overflow-hidden">
      <motion.div
        initial={{ opacity: 0, y: 30 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.8 }}
        className="max-w-4xl"
      >
        <span className="text-accent text-sm uppercase tracking-[0.3em] font-semibold mb-4 block">
          Creative Developer & Designer
        </span>
        <h1 className="text-6xl md:text-9xl font-serif leading-[0.9] mb-8 tracking-tighter">
          Crafting Digital <br />
          <span className="italic text-muted">Experiences.</span>
        </h1>
        <p className="text-lg md:text-xl text-muted max-w-xl font-light leading-relaxed">
          사용자 중심의 가치를 기술로 구현하며, 단순함을 통해 복잡함을 해결하는 것을 지향합니다.
        </p>
      </motion.div>
      
      <motion.div 
        initial={{ opacity: 0 }}
        animate={{ opacity: 1 }}
        transition={{ delay: 1, duration: 1 }}
        className="absolute bottom-12 left-1/2 -translate-x-1/2 flex flex-col items-center gap-2"
      >
        <span className="text-[10px] uppercase tracking-widest text-muted">Scroll</span>
        <ChevronDown className="w-4 h-4 text-muted animate-bounce" />
      </motion.div>
    </section>
  );
};

const About = () => {
  const skills = ["React", "TypeScript", "Tailwind CSS", "Node.js", "Framer Motion", "UI/UX Design"];
  
  return (
    <section id="about" className="py-32 px-6 md:px-24 bg-[#0a0a0a]">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-24">
        <motion.div
          initial={{ opacity: 0, x: -30 }}
          whileInView={{ opacity: 1, x: 0 }}
          viewport={{ once: true }}
        >
          <h2 className="text-4xl md:text-5xl mb-12 italic">About Me</h2>
          <div className="space-y-6 text-muted font-light leading-relaxed text-lg">
            <p>
              저는 5년 차 프론트엔드 개발자로, 심미적인 디자인과 견고한 코드 사이의 균형을 찾는 것을 즐깁니다.
            </p>
            <p>
              단순히 기능을 구현하는 것을 넘어, 사용자가 제품을 사용하는 과정에서 느끼는 감정과 경험의 질을 높이는 데 집중합니다.
            </p>
            <div className="pt-8">
              <h3 className="text-white font-medium mb-4 uppercase text-xs tracking-widest">Philosophy</h3>
              <p className="italic border-l-2 border-accent pl-6 py-2">
                "Less is more, but better is essential."
              </p>
            </div>
          </div>
        </motion.div>
        
        <motion.div
          initial={{ opacity: 0, x: 30 }}
          whileInView={{ opacity: 1, x: 0 }}
          viewport={{ once: true }}
          className="flex flex-col justify-center"
        >
          <h3 className="text-white font-medium mb-8 uppercase text-xs tracking-widest">Technical Stack</h3>
          <div className="flex flex-wrap gap-4">
            {skills.map((skill) => (
              <span key={skill} className="px-6 py-3 border border-white/10 rounded-full text-sm hover:bg-white hover:text-black transition-all cursor-default">
                {skill}
              </span>
            ))}
          </div>
          
          <div className="mt-16 grid grid-cols-2 gap-8">
            <div>
              <span className="text-3xl font-serif block mb-1">05+</span>
              <span className="text-[10px] uppercase tracking-widest text-muted">Years Experience</span>
            </div>
            <div>
              <span className="text-3xl font-serif block mb-1">20+</span>
              <span className="text-[10px] uppercase tracking-widest text-muted">Projects Completed</span>
            </div>
          </div>
        </motion.div>
      </div>
    </section>
  );
};

const Portfolio = () => {
  const projects = [
    {
      title: "Minimalist E-commerce",
      category: "Web Development",
      tech: "React, Stripe, Tailwind",
      image: "https://picsum.photos/seed/shop/800/600",
      result: "Conversion rate increased by 25%"
    },
    {
      title: "AI Content Platform",
      category: "Full Stack",
      tech: "Next.js, OpenAI, PostgreSQL",
      image: "https://picsum.photos/seed/ai/800/600",
      result: "10k+ active monthly users"
    },
    {
      title: "Design System 2.0",
      category: "UI/UX Design",
      tech: "Figma, Storybook, React",
      image: "https://picsum.photos/seed/design/800/600",
      result: "Reduced dev time by 40%"
    },
    {
      title: "Financial Dashboard",
      category: "Data Visualization",
      tech: "D3.js, TypeScript, Vite",
      image: "https://picsum.photos/seed/finance/800/600",
      result: "Real-time tracking for 50+ assets"
    }
  ];

  return (
    <section id="portfolio" className="py-32 px-6 md:px-24">
      <div className="flex justify-between items-end mb-16">
        <motion.h2 
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="text-4xl md:text-5xl italic"
        >
          Selected Works
        </motion.h2>
        <span className="text-[10px] uppercase tracking-widest text-muted mb-2">2022 — 2024</span>
      </div>
      
      <div className="grid grid-cols-1 md:grid-cols-2 gap-12">
        {projects.map((project, i) => (
          <motion.div
            key={project.title}
            initial={{ opacity: 0, y: 30 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            transition={{ delay: i * 0.1 }}
            className="group cursor-pointer"
          >
            <div className="relative aspect-[4/3] overflow-hidden mb-6 bg-[#111]">
              <img 
                src={project.image} 
                alt={project.title}
                referrerPolicy="no-referrer"
                className="object-cover w-full h-full grayscale group-hover:grayscale-0 group-hover:scale-105 transition-all duration-700"
              />
              <div className="absolute inset-0 bg-black/40 opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center">
                <ExternalLink className="w-8 h-8 text-white" />
              </div>
            </div>
            <div className="flex justify-between items-start">
              <div>
                <span className="text-[10px] uppercase tracking-widest text-accent mb-2 block">{project.category}</span>
                <h3 className="text-2xl font-serif mb-2">{project.title}</h3>
                <p className="text-sm text-muted mb-4">{project.tech}</p>
              </div>
              <div className="text-right">
                <span className="text-[10px] uppercase tracking-widest text-white/40 block mb-1">Impact</span>
                <span className="text-xs font-medium italic">{project.result}</span>
              </div>
            </div>
          </motion.div>
        ))}
      </div>
    </section>
  );
};

const Contact = () => {
  return (
    <section id="contact" className="py-32 px-6 md:px-24 bg-[#0a0a0a] relative overflow-hidden">
      <div className="max-w-5xl mx-auto">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-24">
          <div>
            <h2 className="text-4xl md:text-5xl mb-8 italic">Let's Connect</h2>
            <p className="text-muted font-light mb-12 max-w-sm">
              새로운 프로젝트 제안이나 협업 문의, 혹은 단순히 인사를 나누고 싶다면 언제든 환영입니다.
            </p>
            
            <div className="space-y-8">
              <a href="mailto:hello@example.com" className="flex items-center gap-4 group">
                <div className="w-12 h-12 rounded-full border border-white/10 flex items-center justify-center group-hover:bg-white group-hover:text-black transition-all">
                  <Mail className="w-5 h-5" />
                </div>
                <div>
                  <span className="text-[10px] uppercase tracking-widest text-muted block">Email</span>
                  <span className="text-lg">hello@example.com</span>
                </div>
              </a>
              
              <div className="flex gap-6 pt-4">
                {[
                  { icon: <Linkedin className="w-5 h-5" />, label: "LinkedIn" },
                  { icon: <Github className="w-5 h-5" />, label: "GitHub" }
                ].map((social) => (
                  <a key={social.label} href="#" className="w-10 h-10 rounded-full border border-white/10 flex items-center justify-center hover:border-accent hover:text-accent transition-all">
                    {social.icon}
                  </a>
                ))}
              </div>
            </div>
          </div>
          
          <motion.form 
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="space-y-6"
            onSubmit={(e) => e.preventDefault()}
          >
            <div className="space-y-2">
              <label className="text-[10px] uppercase tracking-widest text-muted">Name</label>
              <input 
                type="text" 
                className="w-full bg-transparent border-b border-white/10 py-3 focus:border-accent outline-none transition-colors font-light"
                placeholder="Your Name"
              />
            </div>
            <div className="space-y-2">
              <label className="text-[10px] uppercase tracking-widest text-muted">Email</label>
              <input 
                type="email" 
                className="w-full bg-transparent border-b border-white/10 py-3 focus:border-accent outline-none transition-colors font-light"
                placeholder="your@email.com"
              />
            </div>
            <div className="space-y-2">
              <label className="text-[10px] uppercase tracking-widest text-muted">Message</label>
              <textarea 
                rows={4}
                className="w-full bg-transparent border-b border-white/10 py-3 focus:border-accent outline-none transition-colors font-light resize-none"
                placeholder="Tell me about your project"
              />
            </div>
            <button className="w-full py-4 bg-white text-black text-xs uppercase tracking-[0.2em] font-bold hover:bg-accent hover:text-white transition-all mt-8">
              Send Message
            </button>
          </motion.form>
        </div>
        
        <footer className="mt-32 pt-8 border-t border-white/5 flex justify-between items-center text-[10px] uppercase tracking-widest text-muted">
          <span>© 2026 Portfolio</span>
          <span>Seoul, South Korea</span>
        </footer>
      </div>
    </section>
  );
};

export default function App() {
  return (
    <div className="selection:bg-accent selection:text-white">
      <Navbar />
      <main>
        <Hero />
        <About />
        <Portfolio />
        <Contact />
      </main>
    </div>
  );
}
