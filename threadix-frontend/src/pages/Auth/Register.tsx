import { useState } from "react";
import axios from "axios";

function Register() {
    const [username, setUsername] = useState("");
    const [displayName, setDisplayName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function save(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/v1/users/save", {
                username: username,
                displayName: displayName,
                email: email,
                password: password,
            });
            alert("User Registered Successfully");
        } catch (err) {
            if (axios.isAxiosError(err)) {
                alert("Registration Failed: " + (err.response?.data || err.message));
            } else {
                alert("Registration Failed: An unexpected error occurred.");
            }
        }
    }
    
    return (
        <div className="container mt-4">
            <div className="card">
                <h1>Register</h1>
                <form onSubmit={save}>
                    <div className="form-group">
                        <label>Username</label>
                        <input type="text" className="form-control" placeholder="Enter Username"
                            value={username} onChange={(e) => setUsername(e.target.value)} required />
                    </div>

                    <div className="form-group">
                        <label>Display Name</label>
                        <input type="text" className="form-control" placeholder="Enter Display Name"
                            value={displayName} onChange={(e) => setDisplayName(e.target.value)} required />
                    </div>

                    <div className="form-group">
                        <label>Email</label>
                        <input type="email" className="form-control" placeholder="Enter Email"
                            value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="Enter Password"
                            value={password} onChange={(e) => setPassword(e.target.value)} required />
                    </div>

                    <button type="submit" className="btn btn-primary mt-4">Register</button>
                </form>
            </div>
        </div>
    );
}

export default Register;
