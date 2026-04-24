import { useState } from "react";
import API from "../services/api";

export default function FormPage() {
  const [form, setForm] = useState({
    title: "",
    status: "",
    priority: ""
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.title) {
      alert("Title is required");
      return;
    }

    try {
      setLoading(true);

      await API.post("/crisis", form);

      alert("Created successfully");

      // ✅ Clear form
      setForm({
        title: "",
        status: "",
        priority: ""
      });

      // ✅ Reload list (simple fix)
      window.location.reload();

    } catch (error) {
      console.error(error);
      alert("Error while creating data");
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