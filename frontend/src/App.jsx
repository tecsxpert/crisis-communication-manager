import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useState } from "react";

import FormPage from "./pages/FormPage";
import ListPage from "./pages/ListPage";
import DetailPage from "./pages/DetailPage";
import Dashboard from "./pages/Dashboard";
import Analytics from "./pages/Analytics";   // 🔥 Day 10
import AIPanel from "./components/AIPanel";

function App() {
  const [refresh, setRefresh] = useState(false);

  const handleRefresh = () => {
    setRefresh(!refresh);
  };

  return (
    <BrowserRouter>
      <Routes>

        {/* ✅ Dashboard (Home Page) */}
        <Route path="/" element={<Dashboard />} />

        {/* ✅ List + Form + AI Panel */}
        <Route
          path="/list"
          element={
            <>
              <FormPage onSuccess={handleRefresh} />
              <ListPage refresh={refresh} />
              <AIPanel /> {/* AI Panel */}
            </>
          }
        />

        {/* ✅ Detail Page */}
        <Route path="/crisis/:id" element={<DetailPage />} />

        {/* 🔥 Day 10: Analytics Page */}
        <Route path="/analytics" element={<Analytics />} />

      </Routes>
    </BrowserRouter>
  );
}

export default App;