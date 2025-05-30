import React, { useState } from "react";
import axios from "axios";
import "../../styles/posts/create-post.scss";
import PostService from "../../config/axiosConfig";

const CreatePost = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [image, setImage] = useState<File | null>(null);
  const [post, setPost] = useState<any>(null);
  const [needMentor, setNeedMentor] = useState(false);
  const [category, setCategory] = useState("");

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
        "https://discord.com/api/webhooks/1377996313047732225/ofmq4H78zT0HNx_uycEEEkWWyTXamJXc0wlY_GogL0jVqwwcjREb2iwoWrzWNK8XGn0s";
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
          <label>Category:</label>
          <select
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            required
          >
            <option value="">Select Category</option>
            <option value="frontend">Frontend</option>
            <option value="backend">Backend</option>
            <option value="devops">DevOps</option>
            <option value="other">Other</option>
          </select>
        </div>

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

        <div className="form-group">
          <label>
            <input
              type="checkbox"
              checked={needMentor}
              onChange={(e) => setNeedMentor(e.target.checked)}
            />
            Need Mentor
          </label>
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
