/**
 * example-ts-todo에는 없는 컴포넌트 — 제목/설명만 props로 받아 표시합니다.
 */
export default function TodoPageHeader({ title, children }) {
  return (
    <header className="header">
      <h1>{title}</h1>
      {children ? <p className="lead">{children}</p> : null}
    </header>
  );
}