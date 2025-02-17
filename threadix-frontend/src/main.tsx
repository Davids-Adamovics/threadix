import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import MainPage from './pages/posts/page-post.tsx'
import Header from './pages/common/Header.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Header />
    <MainPage />
  </StrictMode>,
)
