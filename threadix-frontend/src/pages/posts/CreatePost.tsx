import React, { useState } from "react";
import axios from "axios";
import "../../styles/posts/create-post.scss";
import PostService from "../../config/axiosConfig";

const CreatePost = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [image, setImage] = useState<File | null>(null);
  const [post, setPost] = useState<any>(null);

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setImage(e.target.files[0]);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      let imageUrl = "";
      if (image) {
        const uploadResponse = await PostService.uploadImage(image);
        imageUrl = uploadResponse.data;
      }

      const webhookUrl =
        "https://discord.com/api/webhooks/1364723325565145219/dzvIgD-ngFxTNDMcuVN1rkfrqNPLsT0847c0ehx7JYKyjGqmF7T2kterwxpaC7BgTI_Z";
      await axios.post(webhookUrl, {
        content: `📢 A new post Has been made!\n\n**Title:** ${title}\n\n**Content:** ${content}\n\n------------------------------------------------\n\n`,
      });

      const postData = {
        title,
        content,
        imagePath: imageUrl,
        timestamp: new Date(),
        visibility: "PUBLIC",
        likesCount: 0,
      };

      const response = await PostService.createPost(postData);
      setPost(response.data);
    } catch (error) {
      console.error("Error creating post", error);
    }
  };

  return (
    <div className="create-post-container">
      <h2>Create Post</h2>
      <form onSubmit={handleSubmit} className="create-post-form">
        <div className="form-group">
          <label>Title:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Content:</label>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Upload Image:</label>
          <input type="file" onChange={handleFileChange} />
        </div>
        <button type="submit" className="submit-button">
          Create Post
        </button>
      </form>

      {post && <div className="post-success">Post Created Successfully!</div>}
    </div>
  );
};

export default CreatePost;
