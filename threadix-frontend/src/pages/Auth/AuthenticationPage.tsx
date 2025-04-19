import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../../styles/Auth/LoginRegister.scss";

function Authentication() {
  const [isLogin, setIsLogin] = useState(true); // State to toggle between Login and Register
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [displayName, setDisplayName] = useState("");
  const navigate = useNavigate();

const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      const res = await axios.post("http://77.37.54.78:8080/api/v1/users/login", {
        email,
        password,
      });

      // Check if login was successful
      if (res.data.message === "Login Success") {
        // Store the JWT token in localStorage
        localStorage.setItem("authToken", res.data.token); // Store actual token here
        navigate("/posts"); // Navigate to posts page after successful login
      } else {
        alert("Incorrect Email or Password");
      }
    } catch (err) {
      alert("Login Failed");
    }
};


  const handleRegister = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      await axios.post("http://77.37.54.78:8080/api/v1/users/save", {
        username,
        displayName,
        email,
        password,
      });
      alert("User Registered Successfully");
    } catch (err) {
      if (axios.isAxiosError(err)) {
        alert("Registration Failed: " + (err.response?.data || err.message));
      } else {
        alert("Registration Failed: An unexpected error occurred.");
      }
    }
  };

  return (
    <div className="auth-form">
      <div className="card">
        <h1>{isLogin ? "Login" : "Register"}</h1>
        <form onSubmit={isLogin ? handleLogin : handleRegister}>
          {!isLogin && (
            <>
              <div className="form-group">
                <label>Username</label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter Username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </div>

              <div className="form-group">
                <label>Display Name</label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter Display Name"
                  value={displayName}
                  onChange={(e) => setDisplayName(e.target.value)}
                  required
                />
              </div>
            </>
          )}

          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              className="form-control"
              placeholder="Enter Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              className="form-control"
              placeholder="Enter Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="btn btn-primary mt-4">
            {isLogin ? "Login" : "Register"}
          </button>
        </form>

        <div className="toggle">
          <p onClick={() => setIsLogin(!isLogin)}>
            {isLogin ? "Don't have an account? Register" : "Already have an account? Login"}
          </p>
        </div>
      </div>
    </div>
  );
}

export default Authentication;
