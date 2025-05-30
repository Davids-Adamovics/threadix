import React, { useState } from "react";

const MentorApplication = () => {
  const [form, setForm] = useState({
    name: "",
    email: "",
    skills: "",
    experience: "",
    github: "",
    projects: "",
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const DISCORD_WEBHOOK_URL =
    "https://discord.com/api/webhooks/1377996313047732225/ofmq4H78zT0HNx_uycEEEkWWyTXamJXc0wlY_GogL0jVqwwcjREb2iwoWrzWNK8XGn0s";

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const content = `
**New Mentor Application**
Name: ${form.name}
Email: ${form.email}
Skills / Expertise: ${form.skills}
Years of Experience: ${form.experience}
GitHub: ${form.github}
Best Projects: ${form.projects}
`;

    try {
      const res = await fetch(DISCORD_WEBHOOK_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ content }),
      });

      if (!res.ok) throw new Error("Failed to send to Discord");

      alert("Application submitted!");
      setForm({
        name: "",
        email: "",
        skills: "",
        experience: "",
        github: "",
        projects: "",
      });
    } catch (error) {
      alert("Failed to submit application");
      console.error(error);
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      style={{
        maxWidth: 500,
        margin: "auto",
        padding: 20,
        borderRadius: 8,
        boxShadow: "0 2px 10px rgba(0,0,0,0.1)",
        fontFamily: "Arial, sans-serif",
        display: "flex",
        flexDirection: "column",
        gap: 15,
      }}
    >
      <h2 style={{ textAlign: "center" }}>Mentor Application</h2>

      <label>
        Full Name:
        <input
          name="name"
          value={form.name}
          onChange={handleChange}
          required
          style={{ width: "100%", padding: 8, marginTop: 4, borderRadius: 4, border: "1px solid #ccc" }}
        />
      </label>

      <label>
        Email:
        <input
          name="email"
          type="email"
          value={form.email}
          onChange={handleChange}
          required
          style={{ width: "100%", padding: 8, marginTop: 4, borderRadius: 4, border: "1px solid #ccc" }}
        />
      </label>

      <label>
        Skills / Expertise:
        <textarea
          name="skills"
          value={form.skills}
          onChange={handleChange}
          required
          rows={4}
          style={{ width: "100%", padding: 8, marginTop: 4, borderRadius: 4, border: "1px solid #ccc", resize: "vertical" }}
        />
      </label>

      <label>
        Years of Experience:
        <input
          name="experience"
          type="number"
          min={0}
          value={form.experience}
          onChange={handleChange}
          required
          style={{ width: "100%", padding: 8, marginTop: 4, borderRadius: 4, border: "1px solid #ccc" }}
        />
      </label>

      <label>
        GitHub URL:
        <input
          name="github"
          type="url"
          value={form.github}
          onChange={handleChange}
          placeholder="https://github.com/yourusername"
          style={{ width: "100%", padding: 8, marginTop: 4, borderRadius: 4, border: "1px solid #ccc" }}
        />
      </label>

      <label>
        Best Projects (links or description):
        <textarea
          name="projects"
          value={form.projects}
          onChange={handleChange}
          rows={3}
          style={{ width: "100%", padding: 8, marginTop: 4, borderRadius: 4, border: "1px solid #ccc", resize: "vertical" }}
        />
      </label>

      <button
        type="submit"
        style={{
          padding: 12,
          backgroundColor: "#2563eb",
          color: "#fff",
          border: "none",
          borderRadius: 6,
          fontWeight: "bold",
          cursor: "pointer",
          marginTop: 10,
        }}
      >
        Submit
      </button>
    </form>
  );
};

export default MentorApplication;
