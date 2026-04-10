/**
 * 로그인 API만 필요할 때 쓰는 최소 예제 (교육용).
 * 실제 앱 전체는 `client.js`의 `api`를 사용합니다.
 */

async function request(path, options = {}) {
  const { json, ...init } = options;
  const headers = { ...init.headers };
  if (json !== undefined) {
    headers['Content-Type'] = 'application/json';
    init.body = JSON.stringify(json);
  }
  const res = await fetch(path, {
    ...init,
    headers,
    credentials: 'include',
  });
  const text = await res.text();
  let data = null;
  if (text) {
    try {
      data = JSON.parse(text);
    } catch {
      data = text;
    }
  }
  if (!res.ok) {
    const err = new Error(data?.error || res.statusText || 'Request failed');
    err.status = res.status;
    err.data = data;
    throw err;
  }
  return data;
}

async function requestForm(path, method, formData) {
  const res = await fetch(path, {
    method,
    body: formData,
    credentials: 'include',
  });
  const text = await res.text();
  let data = null;
  if (text) {
    try {
      data = JSON.parse(text);
    } catch {
      data = text;
    }
  }
  if (!res.ok) {
    const err = new Error(data?.error || res.statusText || 'Request failed');
    err.status = res.status;
    err.data = data;
    throw err;
  }
  return data;
}

export const api = {
  getCategories: () => request('/api/categories'),
  getPosts: (params) => {
    const q = new URLSearchParams();
    if (params?.categoryId) q.set('categoryId', String(params.categoryId));
    if (params?.page) q.set('page', String(params.page));
    if (params?.pageSize) q.set('pageSize', String(params.pageSize));
    const s = q.toString();
    return request(`/api/posts${s ? `?${s}` : ''}`);
  },

  //get : 글상세 + 댓글(서버에서 조회 수 증가)
  getPost:(id) =>request(`/api/posts/${id}` ),
  //GET : 편집 폼용 글 한 건(본인만, 조회수 증가 없음)
  getPostForEdit: (id) => request(`/api/posts/${id}/edit`),

  //post 새글 multipart Formdata(이미지 필드 포함)
  createPost: (formData) => requestForm('/api/posts', 'POST', formData),

  //PATCH : 글 수정 - FormData로 본문, 이미지 교체/ 삭제
  updatePost : (id,formData) => requestForm(`/api/posts/${id}`,'PATCH',formData),

  //post : 해당 글에 댓글 작성 (JSON body)
  createComment : (postId,body) =>
    request(`/api/posts/${postId}/comments`, {method: 'POST', json:body}),
  //get 현재 세션 로그인 여부 회원 정보
  me: () => request('/api/auth/me'),

  /** DELETE: 글 삭제 */
  deletePost: (id) => request(`/api/posts/${id}`, { method: 'DELETE' }),

  login: (body) => request('/api/auth/login', { method: 'POST', json: body }),
  logout: () => request('/api/auth/logout', { method: 'POST' }),
  join: (body) => request('/api/auth/join', { method: 'POST', json: body }),
};