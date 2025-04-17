import { Navigate, Outlet } from "react-router-dom";

const AuthGuard = () => {
    const isAuthenticated = !!localStorage.getItem("authToken"); // Check if token exists

    return isAuthenticated ? <Outlet /> : <Navigate to="/" />;
};

export default AuthGuard;
