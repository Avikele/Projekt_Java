import {NavLink, useLocation} from 'react-router-dom'
import "./navbar.scss"
import NotificationsSharpIcon from '@mui/icons-material/NotificationsSharp';
import SearchSharpIcon from '@mui/icons-material/SearchSharp';

const isBookshelfActive = () => {
    // Check if the current path starts with '/bookshelf/book/' or '/bookshelf'
    return location.pathname.startsWith('/bookshelf/book/') ? 'deactive' : (location.pathname === '/bookshelf' ? 'active' : 'deactive');
};

const isHomeActive =() => {
    return location.pathname.startsWith('/book') ? 'deactive' : (location.pathname === '/' ? 'active' : 'deactive');
}

const logout = () => {
    localStorage.clear();
}

const Navbar = () => {
    return(
        <div className="navbar">
            <div className="left">
                <NavLink to="/" className={isHomeActive}>
                <button className="home"> Home </button>
                </NavLink>
                <NavLink to="/bookshelf"className={isBookshelfActive}>
                <button> Bookshelf</button>
                </NavLink>
                <SearchSharpIcon className="searchicon"/>
                <input type="text" placeholder="Search"/>
            </div>
            <div className="right">
                <NotificationsSharpIcon className="icon"/>
                {/*<div className="counter">1</div> */}
                <NavLink to="/profile/1" className={({isActive}) => isActive ? 'active': ''}>
                <button>Account</button>
                </NavLink>
                <NavLink to="/login" className=''>
                <button onClick={logout}>Logout</button>
                </NavLink>
            </div>
        
        </div>
    
    )
}

export default Navbar