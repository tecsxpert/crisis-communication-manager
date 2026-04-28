import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from "react";

import FormPage from "./pages/FormPage";
import ListPage from "./pages/ListPage";
import DetailPage from "./pages/DetailPage";
import Dashboard from "./pages/Dashboard";
import AIPanel from "./components/AIPanel"; // ✅ ADD THIS

function App() {
  const [refresh, setRefresh] = useState(false);

  const handleRefresh = () => {
    setRefresh(!refresh);
  };

  return (
    <BrowserRouter>
      <Routes>

        {/* ✅ Dashboard */}
        <Route path="/" element={<Dashboard />} />

        {/* ✅ List + Form + AI Panel */}
        <Route
          path="/list"
          element={
            <>
              <FormPage onSuccess={handleRefresh} />
              <ListPage refresh={refresh} />

              {/* 🔥 ADD AI PANEL HERE */}
              <AIPanel />
            </>
          }
        />

        {/* ✅ Detail Page */}
        <Route path="/crisis/:id" element={<DetailPage />} />

      </Routes>
    </BrowserRouter>
  );
}

export default App;