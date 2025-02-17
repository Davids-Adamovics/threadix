import '../../styles/common/header.scss';
import { FaBell, FaEnvelope, FaUserCircle } from 'react-icons/fa';

function Header() {
    return (
        <header className="header">
            {/* Left Section */}
            <div className="left-header-items">
                <button className="menu-extends-button" id="optionsMenu">
                    <img src="/menu-extend-button.png" alt="Menu" />
                </button>
                <img className="threadix-logo" src="/Threadix.png" alt="Threadix Logo" />
                <img className="threadix-logo2" src="/threadix-logo-title.png" alt="Threadix Title" />
            </div>

            {/* Middle Section - Search Bar */}
            <div className="middle-header-search">
                <input type="text" className="search-bar" placeholder="Search..." />
            </div>

            {/* Right Section - Icons */}
            <div className="right-header-items">
                <FaEnvelope className="icon" title="Messages" />
                <FaBell className="icon" title="Notifications" />
                <FaUserCircle className="icon profile-icon" title="Profile" />
            </div>
            <hr/>
        </header>
    );
}

export default Header;
