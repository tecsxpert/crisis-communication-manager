import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

export default function ListPage({ refresh }) {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  // ✅ FETCH DATA
  const fetchData = async () => {
    try {
      setLoading(true);

      const res = await API.get(`/crisis?page=${page}&size=5`);

      setData(res.data.content || []);
      setTotalPages(res.data.totalPages || 0);

    } catch (err) {
      console.error("Fetch Error:", err.response || err.message);
      alert("Failed to load data");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, [page, refresh]);

  // ✅ DELETE
  const handleDelete = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete?");
    if (!confirmDelete) return;

    try {
      await API.delete(`/crisis/${id}`);
      alert("Deleted successfully ✅");
      fetchData(); // refresh after delete
    } catch (err) {
      console.error("Delete Error:", err.response || err.message);
      alert("Delete failed ❌");
    }
  };

  // ✅ VIEW DETAIL
  const handleView = (id) => {
    navigate(`/crisis/${id}`);
  };

  return (
    <div>
      <h2>Crisis List</h2>

      {loading ? (
        <p>Loading...</p>
      ) : data.length === 0 ? (
        <p>No data available</p>
      ) : (
        <table border="1" cellPadding="8" style={{ width: "100%" }}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Title</th>
              <th>Status</th>
              <th>Priority</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {data.map((item) => (
              <tr key={item.id}>
                <td>{item.id}</td>
                <td>{item.title}</td>
                <td>{item.status}</td>
                <td>{item.priority}</td>

                <td>
                  <button onClick={() => handleView(item.id)}>
                    View
                  </button>

                  <button
                    onClick={() => handleDelete(item.id)}
                    style={{ marginLeft: "8px", color: "red" }}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      <br />

      {/* ✅ PAGINATION */}
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