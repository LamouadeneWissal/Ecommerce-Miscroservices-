import { NavLink } from 'react-router-dom'

export default function NavBar(){
  return (
    <nav className="navbar">
      <div className="brand">Stock Market</div>
      <div className="links">
        <NavLink to="/companies" className={({isActive})=> isActive? 'active' : ''}>Companies</NavLink>
        <NavLink to="/stocks" className={({isActive})=> isActive? 'active' : ''}>Stocks</NavLink>
      </div>
    </nav>
  )
}
