import { useEffect, useState } from "react";
import axios from "axios";
import '../../styles/profile/profile.scss';

interface User {
  userId: number;
  username: string;
  displayName: string;
  email: string;
  profilePicture: string | null;
  bio: string | null;
  comments: any[];
  anonymous: boolean;
}

const Profile = () => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const token = localStorage.getItem("authToken");

    if (token) {
      axios
        .get("http://77.37.54.78:8080/api/v1/users/profile", {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((response) => {
          console.log("User Data from DB:", response.data);
          setUser(response.data);
          setLoading(false);
        })
        .catch(() => {
          setError("Failed to load user profile");
          setLoading(false);
        });
    } else {
      setError("No authentication token found");
      setLoading(false);
    }
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div className="error">{error}</div>;
  if (!user) return <div>User not found</div>;

  return (
    <div className="profile-page">
      {/* Left Sidebar */}
      <aside className="profile-sidebar">
        <img
          src={`http://localhost:8080${user.profilePicture}`}
          alt="Profile"
          className="profile-picture"
        />

        <h2 className="displayName">{user.displayName}</h2>
        <p className="username">@{user.username}</p>
        {user.bio && <p className="bio">"{user.bio}"</p>}
        <p className="isAnnonymous"><strong>Anonymous:</strong> {user.anonymous ? "Yes" : "No"}</p>
        <p className="comments"><strong>Comments:</strong> {user.comments.length}</p>
      </aside>

      {/* Main Content */}
      <main className="profile-content">
        <h2>Recent Activity</h2>
        <div className="post-placeholder">
          <p>No recent posts yet.</p>
        </div>
      </main>
    </div>
  );
};

export default Profile;
