import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./index.css";
import MainPage from "./pages/posts/page-post.tsx";
import Header from "./pages/common/Header.tsx";
import CreatePost from "./pages/posts/CreatePost.tsx";
import PrivateRoute from "./pages/Auth/AuthGuard.tsx";
import AuthGuard from "./pages/Auth/AuthGuard.tsx"; // this is your PrivateRoute
import AuthRedirectGuard from "./pages/Auth/AuthRedirectGuard.tsx"; // you had this wrong
import AuthenticationPage from "./pages/Auth/AuthenticationPage.tsx";
import Profile from "./pages/profile/profile.tsx";
import MentorApplication from "./pages/applications/MentoreApplication.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Router>
      <Header />
      <Routes>
        <Route
          path="/auth"
          element={
            <AuthRedirectGuard>
              <AuthenticationPage />
            </AuthRedirectGuard>
          }
        />

        <Route element={<AuthGuard />}>
          <Route path="/" element={<MainPage />} />
          <Route path="/post/create" element={<CreatePost />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/mentor-application" element={<MentorApplication />} />
        </Route>
      </Routes>
    </Router>
  </StrictMode>
);
