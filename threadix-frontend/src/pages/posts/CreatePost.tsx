import React, { useState } from "react";
import axios from "axios";
import "../../styles/posts/create-post.scss";

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
                const formData = new FormData();
                formData.append("file", image);

                const uploadResponse = await axios.post(
                    "http://localhost:8080/api/posts/upload",
                    formData,
                    {
                        headers: { "Content-Type": "multipart/form-data" },
                    }
                );
                imageUrl = uploadResponse.data;
            }

            const postData = {
                title,
                content,
                imagePath: imageUrl,
                timestamp: new Date(),
                visibility: "PUBLIC",
                likesCount: 0,
            };

            const response = await axios.post(
                "http://localhost:8080/api/posts",
                postData
            );
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
