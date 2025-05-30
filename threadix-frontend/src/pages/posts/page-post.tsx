import React, { useEffect, useState } from "react";
import PostConfig from "../../config/axiosConfig";
import "../../styles/posts/page-post.scss";
import { FaArrowUp, FaArrowDown, FaCommentAlt } from "react-icons/fa";
import ModelViewer from "../../components/ModelViewer";
import { useNavigate } from "react-router-dom";

interface Post {
  postId: number;
  title: string;
  content: string;
  timestamp: string;
  visibility: string;
  likesCount: number;
  imagePath?: string;
}

const MainPage: React.FC = () => {
  const [posts, setPosts] = useState<Post[]>([]);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  const [loadedModels, setLoadedModels] = useState<{ [key: number]: boolean }>(
    {}
  );

  // Store model sizes in MB keyed by postId
  const [modelSizes, setModelSizes] = useState<{ [key: number]: number }>({});

  const handleLoadModel = (postId: number) => {
    setLoadedModels((prev) => ({ ...prev, [postId]: true }));
  };

  // Fetch posts once
  useEffect(() => {
    PostConfig.getAllPosts()
      .then((response: { data: Post[] }) => {
        setPosts(response.data);
      })
      .catch((error: any) => {
        console.error("Error fetching posts:", error);
        setError("Failed to load posts.");
      });
  }, []);

  // For each post with .glb, fetch HEAD to get size
  useEffect(() => {
    posts.forEach((post) => {
      if (
        post.imagePath &&
        post.imagePath.endsWith(".glb") &&
        !modelSizes[post.postId]
      ) {
        fetch(`http://localhost:8080${post.imagePath}`, { method: "HEAD" })
          .then((res) => {
            const size = res.headers.get("content-length");
            if (size) {
              setModelSizes((prev) => ({
                ...prev,
                [post.postId]: Number(size) / (1024 * 1024),
              }));
            }
          })
          .catch(() => {
            // ignore errors silently
          });
      }
    });
  }, [posts, modelSizes]);

  return (
    <div className="main-layout">
      <div className="sidebar">
        <ul className="menu-section">
          <li className="menu-item">Home</li>
          <li className="menu-item">Trending</li>
          <li className="menu-item">Discover</li>
          <li className="menu-item">Communities</li>
        </ul>

        <hr className="menu-divider" />
        <h3 className="menu-heading">Recent</h3>
        <hr className="menu-divider" />
        <h3 className="menu-heading">Info</h3>

        <ul className="menu-section">
          <li className="menu-item">Home</li>
          <li className="menu-item">Trending</li>
          <li className="menu-item">Discover</li>
          <li className="menu-item">Communities</li>
          <li
            className="menu-item"
            onClick={() => navigate("/mentor-application")}
          >
            Mentor Application
          </li>
        </ul>
      </div>

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
                  {post.imagePath && post.imagePath.endsWith(".glb") ? (
                    <div style={{ position: "relative", height: 300 }}>
                      {!loadedModels[post.postId] && (
                        <div
                          style={{
                            filter: "blur(8px)",
                            height: "100%",
                            background: "#ccc",
                          }}
                        >
                          {/* empty blurred placeholder */}
                        </div>
                      )}
                      {!loadedModels[post.postId] && (
                        <button
                          onClick={() => handleLoadModel(post.postId)}
                          title={`Model size: ${
                            modelSizes[post.postId]
                              ? modelSizes[post.postId].toFixed(2)
                              : "Loading..."
                          } MB`}
                          style={{
                            position: "absolute",
                            top: "50%",
                            left: "50%",
                            transform: "translate(-50%, -50%)",
                            zIndex: 10,
                            padding: "12px 24px",
                            cursor: "pointer",
                            backgroundColor: "#1f2937",
                            color: "#f9fafb",
                            border: "none",
                            borderRadius: "8px",
                            fontWeight: "600",
                            fontSize: "1rem",
                            boxShadow: "0 4px 12px rgba(0, 0, 0, 0.15)",
                            transition: "background-color 0.3s ease",
                          }}
                          onMouseEnter={(e) =>
                            (e.currentTarget.style.backgroundColor = "#374151")
                          }
                          onMouseLeave={(e) =>
                            (e.currentTarget.style.backgroundColor = "#1f2937")
                          }
                        >
                          Load Model [
                          {modelSizes[post.postId]
                            ? modelSizes[post.postId].toFixed(2) + " MB"
                            : "..."}
                          ]
                        </button>
                      )}
                      {loadedModels[post.postId] && (
                        <ModelViewer
                          url={`http://localhost:8080${post.imagePath}`}
                          blurred={false}
                        />
                      )}
                    </div>
                  ) : (
                    <div className="post-image-container">
                      <img
                        src={`http://localhost:8080${post.imagePath}`}
                        alt="Post Background"
                        className="post-image-background"
                      />
                      <img
                        src={`http://localhost:8080${post.imagePath}`}
                        alt="Post"
                        className="post-image-foreground"
                      />
                    </div>
                  )}

                  <div className="post-info">
                    <span className="post-visibility">
                      🔒 {post.visibility}
                    </span>
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
    </div>
  );
};

export default MainPage;
