import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./index.css";
import MainPage from "./pages/posts/page-post.tsx";
import Header from "./pages/common/Header.tsx";
import CreatePost from "./pages/posts/CreatePost.tsx";
import PrivateRoute from "./pages/Auth/AuthGuard.tsx";
import AuthenticationPage from "./pages/Auth/AuthenticationPage.tsx";
import Profile from "./pages/profile/profile.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<AuthenticationPage />} />

        {/* Private Route to protect /posts and /post/create */}
        <Route element={<PrivateRoute />}>
          <Route path="/posts" element={<MainPage />} />
          <Route path="/post/create" element={<CreatePost />} />
          <Route path="/profile" element={<Profile />} />
        </Route>
      </Routes>
    </Router>
  </StrictMode>
);
