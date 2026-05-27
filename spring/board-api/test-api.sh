#!/usr/bin/env bash
# ============================================================
# Board REST API — curl 테스트 스크립트
# 서버 실행 후: bash test-api.sh
# ============================================================
set -e

BASE="http://localhost:8080"
BOLD='\033[1m'; GREEN='\033[0;32m'; RED='\033[0;31m'; NC='\033[0m'

ok()  { echo -e "${GREEN}✔ $1${NC}"; }
hdr() { echo -e "\n${BOLD}=== $1 ===${NC}"; }

# ── 1. 회원가입 ────────────────────────────────────────────
hdr "1. 회원가입 (POST /api/members)"
MEMBER=$(curl -s -X POST "$BASE/api/members" \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"1234","nickname":"앨리스"}')
echo "$MEMBER" | python3 -m json.tool
MEMBER_ID=$(echo "$MEMBER" | python3 -c "import sys,json; print(json.load(sys.stdin)['id'])")
ok "회원 ID: $MEMBER_ID"

# ── 2. 중복 가입 → 409 ────────────────────────────────────
hdr "2. 중복 username → 409 Conflict"
curl -s -X POST "$BASE/api/members" \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"1234","nickname":"앨리스2"}' \
  | python3 -m json.tool

# ── 3. 글 등록 ────────────────────────────────────────────
hdr "3. 글 등록 (POST /api/posts)"
POST=$(curl -s -X POST "$BASE/api/posts" \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"첫 번째 글\",\"content\":\"내용입니다\",\"memberId\":$MEMBER_ID}")
echo "$POST" | python3 -m json.tool
POST_ID=$(echo "$POST" | python3 -c "import sys,json; print(json.load(sys.stdin)['id'])")
ok "글 ID: $POST_ID"

# ── 4. 글 등록 2 ──────────────────────────────────────────
curl -s -X POST "$BASE/api/posts" \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"두 번째 글\",\"content\":\"내용2\",\"memberId\":$MEMBER_ID}" > /dev/null

# ── 5. 목록 조회 (페이징) ─────────────────────────────────
hdr "4. 글 목록 (GET /api/posts?page=0&size=10)"
curl -s "$BASE/api/posts?page=0&size=10" | python3 -m json.tool

# ── 6. 단건 조회 ──────────────────────────────────────────
hdr "5. 글 상세 (GET /api/posts/$POST_ID)"
curl -s "$BASE/api/posts/$POST_ID" | python3 -m json.tool

# ── 7. 수정 ───────────────────────────────────────────────
hdr "6. 글 수정 (PUT /api/posts/$POST_ID)"
curl -s -X PUT "$BASE/api/posts/$POST_ID" \
  -H "Content-Type: application/json" \
  -d '{"title":"수정된 제목","content":"수정된 내용"}' \
  | python3 -m json.tool

# ── 8. 없는 글 조회 → 404 ────────────────────────────────
hdr "7. 없는 글 조회 → 404"
curl -s "$BASE/api/posts/9999" | python3 -m json.tool

# ── 9. 검증 실패 → 400 ───────────────────────────────────
hdr "8. 제목 빈 값 → 400 Bad Request"
curl -s -X POST "$BASE/api/posts" \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"\",\"content\":\"내용\",\"memberId\":$MEMBER_ID}" \
  | python3 -m json.tool

# ── 10. 삭제 ──────────────────────────────────────────────
hdr "9. 글 삭제 (DELETE /api/posts/$POST_ID)"
HTTP_CODE=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE "$BASE/api/posts/$POST_ID")
echo "HTTP Status: $HTTP_CODE"
[ "$HTTP_CODE" = "204" ] && ok "삭제 성공 (204 No Content)" || echo -e "${RED}✘ 실패${NC}"

# ── 11. 삭제된 글 조회 → 404 ─────────────────────────────
hdr "10. 삭제 후 조회 → 404"
curl -s "$BASE/api/posts/$POST_ID" | python3 -m json.tool

echo -e "\n${GREEN}${BOLD}✔ 모든 테스트 완료${NC}"
