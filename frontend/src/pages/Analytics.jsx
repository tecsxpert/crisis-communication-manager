import { useEffect, useState } from "react";
import API from "../services/api";
import {
  BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid
} from "recharts";

export default function Analytics() {
  const [stats, setStats] = useState({});
  const [period, setPeriod] = useState("all");

  useEffect(() => {
    fetchStats();
  }, [period]);

  const fetchStats = async () => {
    try {
      const res = await API.get("/crisis/stats");
      setStats(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const data = [
    { name: "Total", value: stats.total || 0 },
    { name: "Open", value: stats.open || 0 },
    { name: "Closed", value: stats.closed || 0 },
    { name: "High Priority", value: stats.highPriority || 0 }
  ];

  return (
    <div>
      <h2>📊 Analytics Dashboard</h2>

      {/* Period Selector */}
      <select onChange={(e) => setPeriod(e.target.value)}>
        <option value="all">All</option>
        <option value="7">Last 7 Days</option>
        <option value="30">Last 30 Days</option>
      </select>

      <br /><br />

      {/* Chart */}
      <BarChart width={500} height={300} data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Bar dataKey="value" />
      </BarChart>
    </div>
  );
}