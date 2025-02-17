import React, { useState } from 'react';
import axios from 'axios';

const CreatePost = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
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
            // Upload image first
            let imageUrl = '';
            if (image) {
                const formData = new FormData();
                formData.append('file', image);

                const uploadResponse = await axios.post('http://localhost:8080/api/posts/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                    },
                });

                imageUrl = uploadResponse.data; // URL of the uploaded image (relative path)
            }

            // Create the post with title, content, and image URL
            const postData = {
                title,
                content,
                imagePath: imageUrl, // Save the image URL
                timestamp: new Date(),
                visibility: 'PUBLIC', // Replace with the correct visibility if needed
                likesCount: 0,
            };

            const response = await axios.post('http://localhost:8080/api/posts', postData);
            setPost(response.data);
        } catch (error) {
            console.error('Error creating post', error);
        }
    };

    return (
        <div>
            <h2>Create Post</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Title:</label>
                    <input
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Content:</label>
                    <textarea
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Upload Image:</label>
                    <input
                        type="file"
                        onChange={handleFileChange}
                    />
                </div>
                <button type="submit">Create Post</button>
            </form>

            {post && (
                <div>
                    <h3>Post Created Successfully!</h3>
                </div>
            )}
        </div>
    );
};

export default CreatePost;
