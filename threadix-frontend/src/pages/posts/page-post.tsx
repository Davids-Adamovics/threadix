import React, { useEffect, useState } from "react";
import PostConfig from "../../config/PostConfig";
import "../../styles/posts/page-post.scss";
import { FaArrowUp, FaArrowDown, FaCommentAlt } from "react-icons/fa";
import UploadPost from "./CreatePost";

interface Post {
  postId: number;
  title: string;
  content: string;
  timestamp: string;
  visibility: string;
  likesCount: number;
  imagePath?: string; // Optional field for image path
}

const MainPage: React.FC = () => {
  const [posts, setPosts] = useState<Post[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    PostConfig.getAllPosts()
      .then((response: { data: React.SetStateAction<Post[]> }) => {
        setPosts(response.data);
      })
      .catch((error: any) => {
        console.error("Error fetching posts:", error);
        setError("Failed to load posts.");
      });
  }, []);

  return (
    <div className="post-page">
      {error ? (
        <p className="error-message">{error}</p>
      ) : (
        <div className="post-list">
          {posts.map((post) => (
            <div key={post.postId} className="post">
              <div className="vote-section">
                <button className="vote-button upvote">
                  <FaArrowUp />
                </button>
                <span className="vote-count">{post.likesCount}</span>
                <button className="vote-button downvote">
                  <FaArrowDown />
                </button>
              </div>
              <div className="post-content">
                <h3 className="post-title">{post.title}</h3>
                <p className="post-body">{post.content}</p>
                {/* Render image if it exists */}
                {post.imagePath && (
                  <div className="post-image-container">
                    {/* Blurred Background Image */}
                    <img
                      src={`http://localhost:8080${post.imagePath}`}
                      alt="Post Background"
                      className="post-image-background"
                    />
                    {/* Main Foreground Image */}
                    <img
                      src={`http://localhost:8080${post.imagePath}`}
                      alt="Post Image"
                      className="post-image-foreground"
                    />
                  </div>
                )}
                <div className="post-info">
                  <span className="post-visibility">🔒 {post.visibility}</span>
                  <span className="post-timestamp">
                    📅 {new Date(post.timestamp).toLocaleString()}
                  </span>
                  <span className="post-comments">
                    <FaCommentAlt /> 0 Comments
                  </span>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default MainPage;
