import { useState } from "react";
import API from "../services/api";

export default function FormPage({ onSuccess }) {
  const [form, setForm] = useState({
    title: "",
    description: "",   // ✅ ADDED
    status: "",
    priority: ""
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.title.trim()) {
      alert("Title is required");
      return;
    }

    try {
      setLoading(true);

      const response = await API.post("/crisis", form);

      console.log("Created:", response.data);

      alert("Created successfully ✅");

      // ✅ Clear form
      setForm({
        title: "",
        description: "",   // ✅ RESET
        status: "",
        priority: ""
      });

      // ✅ Refresh list WITHOUT reload
      if (onSuccess) {
        onSuccess();
      }

    } catch (error) {
      console.error("POST ERROR:", error.response || error.message);

      if (error.response) {
        alert(`Error: ${error.response.status}`);
      } else {
        alert("Server not reachable");
      }

    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ marginBottom: "20px" }}>
      
      <input
        name="title"
        placeholder="Title"
        value={form.title}
        onChange={handleChange}
      />

      {/* ✅ NEW FIELD */}
      <input
        name="description"
        placeholder="Description"
        value={form.description}
        onChange={handleChange}
      />

      <input
        name="status"
        placeholder="Status"
        value={form.status}
        onChange={handleChange}
      />

      <input
        name="priority"
        placeholder="Priority"
        value={form.priority}
        onChange={handleChange}
      />

      <button type="submit" disabled={loading}>
        {loading ? "Submitting..." : "Submit"}
      </button>
    </form>
  );
}