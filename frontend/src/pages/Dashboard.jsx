import { useEffect, useState } from "react";
import API from "../services/api";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from "recharts";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {
  const [stats, setStats] = useState({
    total: 0,
    open: 0,
    closed: 0,
    highPriority: 0
  });

  const navigate = useNavigate();

  useEffect(() => {
    API.get("/crisis/stats")
      .then((res) => {
        setStats(res.data);
      })
      .catch((err) => {
        console.error("Stats Error:", err);
      });
  }, []);

  // 🔥 Chart data
  const chartData = [
    { name: "Total", value: stats.total },
    { name: "Open", value: stats.open },
    { name: "Closed", value: stats.closed },
    { name: "High", value: stats.highPriority }
  ];

  return (
    <div style={{ padding: "20px" }}>
      <h2>Dashboard</h2>

      {/* ✅ KPI CARDS */}
      <div style={{ display: "flex", gap: "20px", marginBottom: "30px" }}>
        <div style={cardStyle}>Total: {stats.total}</div>
        <div style={cardStyle}>Open: {stats.open}</div>
        <div style={cardStyle}>Closed: {stats.closed}</div>
        <div style={cardStyle}>High Priority: {stats.highPriority}</div>
      </div>

      {/* ✅ CHART */}
      <h3>Statistics Chart</h3>

      <div style={{ width: "100%", height: 300 }}>
        <ResponsiveContainer>
          <BarChart data={chartData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="value" />
          </BarChart>
        </ResponsiveContainer>
      </div>

      <br />

      {/* ✅ NAVIGATION BUTTON */}
      <button onClick={() => navigate("/list")}>
        Go to Crisis List
      </button>
    </div>
  );
}

// ✅ Simple card styling
const cardStyle = {
  padding: "20px",
  background: "#2f80ed",
  color: "white",
  borderRadius: "10px",
  minWidth: "120px",
  textAlign: "center",
  fontWeight: "bold"
};