import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import { AuthProvider } from './hooks/useAuth'
import PostListPage   from './pages/PostListPage'
import PostDetailPage from './pages/PostDetailPage'
import PostFormPage   from './pages/PostFormPage'
import LoginPage      from './pages/LoginPage'
import RegisterPage   from './pages/RegisterPage'

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/"               element={<PostListPage />} />
          <Route path="/posts/:id"      element={<PostDetailPage />} />
          <Route path="/posts/new"      element={<PostFormPage />} />
          <Route path="/posts/:id/edit" element={<PostFormPage />} />
          <Route path="/login"          element={<LoginPage />} />
          <Route path="/register"       element={<RegisterPage />} />
          <Route path="*"              element={<Navigate to="/" />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  )
}