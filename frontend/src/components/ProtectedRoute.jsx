import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";

function ProtectedRoute({ children }) {
  const { user } = useContext(AuthContext);

  if (!user) {
    return <h2>Please login first</h2>;
  }

  return children;
}

export default ProtectedRoute;