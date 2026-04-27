import { useEffect, useState } from "react";
import API from "../services/api";

export default function ListPage({ refresh }) {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  const fetchData = async () => {
    try {
      setLoading(true);

      const res = await API.get(`/crisis?page=${page}&size=5`);

      console.log("API RESPONSE:", res.data);

      setData(res.data.content);
      setTotalPages(res.data.totalPages);

    } catch (err) {
      console.error("Fetch Error:", err.response || err.message);
    } finally {
      setLoading(false);
    }
  };

  // ✅ Reload when page changes OR new data added
  useEffect(() => {
    fetchData();
  }, [page, refresh]);

  return (
    <div>
      <h2>Crisis List</h2>

      {loading ? (
        <p>Loading...</p>
      ) : data.length === 0 ? (
        <p>No data available</p>
      ) : (
        <table border="1" cellPadding="5">
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Status</th>
              <th>Priority</th>
            </tr>
          </thead>
          <tbody>
            {data.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.title}</td>
                <td>{item.status}</td>
                <td>{item.priority}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      <br />

      {/* ✅ Pagination Controls */}
      <button
        onClick={() => setPage(page - 1)}
        disabled={page === 0}
      >
        Prev
      </button>

      <span style={{ margin: "0 10px" }}>
        Page {page + 1} of {totalPages}
      </span>

      <button
        onClick={() => setPage(page + 1)}
        disabled={page + 1 >= totalPages}
      >
        Next
      </button>
    </div>
  );
}