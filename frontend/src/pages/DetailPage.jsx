import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import API from "../services/api";

export default function DetailPage() {
  const { id } = useParams();
  const [data, setData] = useState(null);

  useEffect(() => {
    API.get(`/crisis/${id}`).then(res => setData(res.data));
  }, [id]);

  if (!data) return <p>Loading...</p>;

  return (
    <div>
      <h2>{data.title}</h2>
      <p>{data.description}</p>
      <p>Status: {data.status}</p>
      <p>Priority: {data.priority}</p>
    </div>
  );
}