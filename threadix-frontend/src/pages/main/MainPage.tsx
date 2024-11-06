import React, { useEffect, useState } from 'react';
import PostConfig from '../../config/PostConfig';


interface Post {
  postId: number;
  title: string;
  content: string;
  timestamp: string;
  visibility: string;
  likesCount: number;
}

const MainPage: React.FC = () => {
  const [posts, setPosts] = useState<Post[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    PostConfig.getAllPreces()
      .then((response: { data: React.SetStateAction<Post[]>; }) => {
        setPosts(response.data);
      })
      .catch((error: any) => {
        console.error("Error fetching posts:", error);
        setError("Failed to load posts.");
      });
  }, []);

  return (
    <div>
      <h1>All Posts</h1>
      {error ? (
        <p>{error}</p>
      ) : (
        <ul>
          {posts.map((post) => (
            <li key={post.postId} style={{ marginBottom: '20px', padding: '10px', border: '1px solid #ccc' }}>
              <h3>{post.title}</h3>
              <p>{post.content}</p>
              <small>Visibility: {post.visibility}</small><br />
              <small>Likes: {post.likesCount}</small><br />
              <small>Posted on: {new Date(post.timestamp).toLocaleString()}</small>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default MainPage;
