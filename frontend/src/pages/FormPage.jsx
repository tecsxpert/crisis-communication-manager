import { useState } from "react";
import API from "../services/api";

export default function FormPage() {
  const [form, setForm] = useState({
    title: "",
    status: "",
    priority: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!form.title) {
      alert("Title is required");
      return;
    }

    API.post("/crisis", form)
      .then(() => alert("Created successfully"))
      .catch(() => alert("Error"));
  };

  return (
    <form onSubmit={handleSubmit}>
      <input name="title" placeholder="Title" onChange={handleChange} />
      <input name="status" placeholder="Status" onChange={handleChange} />
      <input name="priority" placeholder="Priority" onChange={handleChange} />
      <button type="submit">Submit</button>
    </form>
  );
}