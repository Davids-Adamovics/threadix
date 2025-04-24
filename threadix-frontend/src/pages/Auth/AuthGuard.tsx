import { Navigate, Outlet } from "react-router-dom";

const AuthGuard = () => {
  const isAuthenticated = !!localStorage.getItem("authToken");
  return isAuthenticated ? <Outlet /> : <Navigate to="/auth" replace />;
};

export default AuthGuard;
