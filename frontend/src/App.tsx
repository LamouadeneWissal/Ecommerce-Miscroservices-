import { Routes, Route, Navigate } from 'react-router-dom'
import CompaniesPage from './pages/CompaniesPage'
import StocksPage from './pages/StocksPage'
import NavBar from './components/NavBar'

export default function App() {
  return (
    <div className="container">
      <NavBar />
      <Routes>
        <Route path="/companies" element={<CompaniesPage />} />
        <Route path="/stocks" element={<StocksPage />} />
        <Route path="*" element={<Navigate to="/companies" replace />} />
      </Routes>
    </div>
  )
}
