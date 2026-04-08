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
  createPost: (formData) => requestForm('/api/posts', 'POST', formData),
  login: (body) => request('/api/auth/login', { method: 'POST', json: body }),
  logout: () => request('/api/auth/logout', { method: 'POST' }),
  join: (body) => request('/api/auth/join', { method: 'POST', json: body }),
};