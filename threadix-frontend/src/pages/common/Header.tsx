import { useNavigate } from "react-router-dom"; // Import useNavigate for redirection
import { FaBell, FaEnvelope, FaUserCircle, FaPlus } from "react-icons/fa"; // Import FaPlus icon
import "../../styles/common/header.scss";
import UserService from "../../config/axiosConfig";
import { useState, useEffect } from "react";
import profileImage from "../../../public/default-avatar.jpg";

function Header() {
  const [dropdownVisible, setDropdownVisible] = useState(false); // State to manage dropdown visibility
  const navigate = useNavigate(); // Initialize navigate hook
  const [searchQuery, setSearchQuery] = useState("");
  const [results, setResults] = useState<any[]>([]);

  // Function to handle the Plus button click
  const handlePlusClick = () => {
    navigate("/post/create"); // Redirect to /post/create
  };

  // Function to handle the logo click (redirect to /posts)
  const handleLogoClick = () => {
    navigate("/"); // Redirect to /posts
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

  useEffect(() => {
    if (!searchQuery) {
      setResults([]);
      return;
    }

    const timeout = setTimeout(() => {
      UserService.searchUsers(searchQuery).then((res) => {
        const users = res.data.users || [];
        const posts = res.data.postTitles || [];

        const combinedResults = [
          ...users.map((u: any) => ({
            type: "user",
            id: u.id,
            displayName: u.displayName,
          })),
          ...posts.map((p: any) => ({
            type: "post",
            id: p.postId,
            title: p.title,
            content: p.content,
          })),
        ];

        setResults(combinedResults);
      });
    }, 300);

    return () => clearTimeout(timeout);
  }, [searchQuery]);

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
        <input
          type="text"
          className="search-bar-main"
          placeholder="Search..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />
        {results.length > 0 && (
          <ul className="search-results-dropdown">
            {results.some((item) => item.type === "post") && (
              <>
                <p className="p-search-rec">POSTS</p>
                {results
                  .filter((item) => item.type === "post")
                  .map((item) => (
                    <li key={`post-${item.id}`}>
                      <strong>{item.title}</strong>
                      <p className="search-bar-content">{item.content}</p>
                    </li>
                  ))}
              </>
            )}

            {results.some((item) => item.type === "user") && (
              <>
                <p className="p-search-rec">USERS</p>
                {results
                  .filter((item) => item.type === "user")
                  .map((item) => (
                    <li key={`user-${item.id}`}>
                      <img
                        // src={`http://localhost:8080${item.profilePicture}`}
                        src={profileImage}
                        alt="Profile"
                        className="profile-picture-search"
                      />
                      {item.displayName}
                    </li>
                  ))}
              </>
            )}
          </ul>
        )}
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
                <li onClick={handleLogout}>Logout</li>
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
