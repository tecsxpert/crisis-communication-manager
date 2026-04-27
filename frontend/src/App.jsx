import { useState } from "react";
import FormPage from "./pages/FormPage";
import ListPage from "./pages/ListPage";

function App() {
  const [refresh, setRefresh] = useState(false);

  const handleRefresh = () => {
    setRefresh(!refresh);
  };

  return (
    <>
      <FormPage onSuccess={handleRefresh} />
      <ListPage refresh={refresh} />
    </>
  );
}

export default App;