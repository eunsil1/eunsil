# useMemo 테스트

## 실행 방법

```bash
cd useMemo-test
npm install
npm run dev
```

## 테스트 방법

1. 브라우저에서 F12 → Console 탭 열기
2. **useMemo 사용** 체크 시:
   - input에 타이핑해도 `🔴 계산 실행됨` **안 나옴** (users가 안 바뀌었으므로)
   - 사용자 추가 버튼 클릭 시에만 계산 실행
3. **useMemo 사용** 체크 해제 시:
   - input에 타이핑할 때마다 `🔴 계산 실행됨` **나옴**
   - 매 렌더마다 countActiveUsers 호출됨
