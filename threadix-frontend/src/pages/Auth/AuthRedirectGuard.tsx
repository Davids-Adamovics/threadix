import { Navigate } from "react-router-dom";

const AuthRedirectGuard = ({ children }: { children: JSX.Element }) => {
  const isAuthenticated = !!localStorage.getItem("authToken");
  return isAuthenticated ? <Navigate to="/" replace /> : children;
};

export default AuthRedirectGuard;
