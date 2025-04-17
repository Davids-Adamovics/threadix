import { useNavigate } from "react-router-dom"; // Import useNavigate for redirection
import { FaBell, FaEnvelope, FaUserCircle, FaPlus } from "react-icons/fa"; // Import FaPlus icon
import "../../styles/common/header.scss";
import { useState } from "react";

function Header() {
  const [dropdownVisible, setDropdownVisible] = useState(false); // State to manage dropdown visibility
  const navigate = useNavigate(); // Initialize navigate hook

  // Function to handle the Plus button click
  const handlePlusClick = () => {
    navigate("/post/create"); // Redirect to /post/create
  };

  // Function to handle the logo click (redirect to /posts)
  const handleLogoClick = () => {
    navigate("/posts"); // Redirect to /posts
  };

  // Function to toggle the dropdown visibility
  const toggleDropdown = () => {
    setDropdownVisible((prevState: any) => !prevState); // Toggle dropdown state
  };

  // Handle logout
  const handleLogout = () => {
    localStorage.removeItem("authToken"); // Clear login state
    navigate("/"); // Redirect to home page
  };

  return (
    <header className="header">
      {/* Left Section */}
      <div className="left-header-items">
        <button className="menu-extends-button" id="optionsMenu">
          <img src="/menu-extend-button.png" alt="Menu" />
        </button>
        
        {/* Threadix Logo - Make it clickable */}
        <img
          className="threadix-logo"
          src="/Threadix.png"
          alt="Threadix Logo"
          onClick={handleLogoClick} // Attach click handler to redirect to /posts
        />
        
        {/* Threadix Title - Make it clickable */}
        <img
          className="threadix-logo2"
          src="/threadix-logo-title.png"
          alt="Threadix Title"
          onClick={handleLogoClick} // Attach click handler to redirect to /posts
        />
      </div>

      {/* Middle Section - Search Bar */}
      <div className="middle-header-search">
        <input type="text" className="search-bar" placeholder="Search..." />
      </div>

      {/* Plus Button for Redirecting */}
      <FaPlus
        className="icon plus-icon"
        title="Create Post"
        onClick={handlePlusClick} // Attach click handler
      />
      
      {/* Right Section - Icons */}
      <div className="right-header-items">
        <FaEnvelope className="icon" title="Messages" />
        <FaBell className="icon" title="Notifications" />
        
        {/* User Icon - Toggle Dropdown */}
        <div className="profile-icon-wrapper">
          <FaUserCircle
            className="icon profile-icon"
            title="Profile"
            onClick={toggleDropdown} // Toggle the dropdown visibility
          />
          
          {/* Dropdown Menu */}
          {dropdownVisible && (
            <div className="dropdown-menu">
              <ul>
                <li onClick={() => navigate("/profile")}>My Profile</li>
                <li onClick={() => navigate("/settings")}>Settings</li>
                <li onClick={handleLogout}>Logout</li> {/* Perform logout on click */}
              </ul>
            </div>
          )}
        </div>
      </div>
      
      <hr />
    </header>
  );
}

export default Header;
