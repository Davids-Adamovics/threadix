import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../../styles/Auth/LoginRegister.scss";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function login(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/api/v1/users/login", {
        email,
        password,
      });

      if (res.data.message === "Login Success") {
        localStorage.setItem("authToken", "true");
        navigate("/posts");
      } else {
        alert("Incorrect Email or Password");
      }
    } catch (err) {
      alert("Login Failed");
    }
  }

  return (
    <div className="auth-form">
      <div className="login-box">
        <h2>Login</h2>
        <hr />
        <form onSubmit={login}>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              className="form-control"
              placeholder="Enter Email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
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
              onChange={(event) => setPassword(event.target.value)}
              required
            />
          </div>

          <button type="submit" className="btn-primary">
            Login
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;
