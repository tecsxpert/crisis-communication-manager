import { useEffect, useState } from "react";
import API from "../services/api";

function ListPage() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Pagination (basic)
  const [page, setPage] = useState(0);
  const [size] = useState(5);

  useEffect(() => {
    fetchData();
  }, [page]);

  const fetchData = () => {
    setLoading(true);

    API.get("/crisis")
      .then((res) => {
        // if backend returns plain list
        setData(res.data);

        // if backend returns Page object (Spring Boot)
        // setData(res.data.content);

        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching data:", err);
        setError("Failed to load data");
        setLoading(false);
      });
  };

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p style={{ color: "red" }}>{error}</p>;
  }

  return (
    <div>
      <h2>Crisis List</h2>

      {data.length === 0 ? (
        <p>No data available</p>
      ) : (
        <>
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

          {/* Pagination buttons */}
          <div style={{ marginTop: "10px" }}>
            <button onClick={() => setPage(page - 1)} disabled={page === 0}>
              Prev
            </button>

            <span style={{ margin: "0 10px" }}>Page: {page}</span>

            <button onClick={() => setPage(page + 1)}>
              Next
            </button>
          </div>
        </>
      )}
    </div>
  );
}

export default ListPage;