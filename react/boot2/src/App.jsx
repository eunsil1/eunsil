import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import './index.css'

export default function App() {
  return (
    <Routes>
      <Route element={<Layout />}>
      </Route>
    </Routes>
  );
}