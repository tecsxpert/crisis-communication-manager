import { useState } from "react";
import API from "../services/api";

export default function AIPanel() {
  const [loading, setLoading] = useState(false);
  const [response, setResponse] = useState("");

  const fetchAI = async () => {
    try {
      setLoading(true);
      setResponse("");

      const res = await API.get("/crisis/ai-summary");

      setResponse(res.data);
    } catch (err) {
      setResponse("Error fetching AI response ❌");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>AI Panel</h3>

      <button onClick={fetchAI}>
        Generate AI Summary
      </button>

      {/* 🔄 Loading */}
      {loading && <p>Loading...</p>}

      {/* 📦 Response Card */}
      {response && (
        <div
          style={{
            border: "1px solid #ccc",
            padding: "10px",
            marginTop: "10px",
            background: "#f5f5f5"
          }}
        >
          {response}
        </div>
      )}
    </div>
  );
}