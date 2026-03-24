import { useState, useMemo } from 'react'

// 시뮬레이션: 무거운 계산 (콘솔로 실행 횟수 확인)
function countActiveUsers(users) {
  console.log('🔴 계산 실행됨 (countActiveUsers)')
  return users.filter(user => user.active).length
}

function App() {
  console.log('🟢 App 렌더링')

  const [inputs, setInputs] = useState({ username: '', email: '' })
  const { username, email } = inputs

  const [users, setUsers] = useState([
    { id: 1, username: 'velopert', email: 'a@a.com', active: true },
    { id: 2, username: 'tester', email: 'b@b.com', active: false },
    { id: 3, username: 'liz', email: 'c@c.com', active: true },
  ])

  const [useMemoMode, setUseMemoMode] = useState(true) // 토글로 비교

  const onChange = (e) => {
    const { name, value } = e.target
    setInputs({ ...inputs, [name]: value })
  }

  const onCreate = () => {
    if (!username.trim() || !email.trim()) return
    const user = { id: Date.now(), username, email, active: true }
    setUsers(users.concat(user))
    setInputs({ username: '', email: '' })
  }

  const onToggle = (id) => {
    setUsers(users.map(u => (u.id === id ? { ...u, active: !u.active } : u)))
  }

  const onRemove = (id) => {
    setUsers(users.filter(u => u.id !== id))
  }

  // useMemo는 항상 호출 (훅 규칙). 의존성 배열만 다르게 설정
  // useMemo O: [users]만 → users 변경 시에만 계산
  // useMemo X: [users, {}] → {}는 매 렌더마다 새로 생성되므로 항상 재계산
  const count = useMemo(
    () => countActiveUsers(users),
    useMemoMode ? [users] : [users, {}]
  )

  return (
    <div style={{ padding: 20, fontFamily: 'sans-serif' }}>
      <h1>useMemo 테스트</h1>

      <div style={{ marginBottom: 16, padding: 12, background: '#f0f0f0', borderRadius: 8 }}>
        <label>
          <input
            type="checkbox"
            checked={useMemoMode}
            onChange={(e) => setUseMemoMode(e.target.checked)}
          />
          {' '}useMemo 사용 (체크 해제 = 매번 계산)
        </label>
      </div>

      <div style={{ marginBottom: 16 }}>
        <input
          name="username"
          placeholder="username"
          value={username}
          onChange={onChange}
          style={{ padding: 8, marginRight: 8 }}
        />
        <input
          name="email"
          placeholder="email"
          value={email}
          onChange={onChange}
          style={{ padding: 8, marginRight: 8 }}
        />
        <button onClick={onCreate} style={{ padding: 8 }}>추가</button>
      </div>

      <p><strong>활성 사용자 수: {count}</strong></p>

      <div style={{ marginTop: 16 }}>
        <strong>사용자 목록</strong> (클릭=활성토글, X=삭제)
        <ul style={{ listStyle: 'none', paddingLeft: 0, marginTop: 8 }}>
          {users.map(user => (
            <li
              key={user.id}
              style={{
                padding: '8px 12px',
                marginBottom: 4,
                background: user.active ? '#e8f5e9' : '#f5f5f5',
                borderRadius: 4,
                display: 'flex',
                justifyContent: 'space-between',
                alignItems: 'center'
              }}
            >
              <span
                onClick={() => onToggle(user.id)}
                style={{ cursor: 'pointer', flex: 1 }}
              >
                {user.username} ({user.email}) {user.active ? '✓' : ''}
              </span>
              <button
                onClick={() => onRemove(user.id)}
                style={{ marginLeft: 8, cursor: 'pointer' }}
              >
                삭제
              </button>
            </li>
          ))}
        </ul>
      </div>

      <p style={{ color: '#666', fontSize: 14, marginTop: 16 }}>
        💡 input에 타이핑할 때 콘솔(F12)을 확인하세요.<br/>
        useMemo O → input 타이핑 시 계산 안 함 / useMemo X → 타이핑할 때마다 계산 실행
      </p>
    </div>
  )
}

export default App
