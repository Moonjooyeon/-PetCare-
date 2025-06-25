// src/App.tsx
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import UserLoginSelectPage from './pages/UserLoginSelectPage';
import AccountTypeSelectPage from "./pages/AccountTypeSelectPage.tsx";
import TermsPage from "./pages/TermsPage.tsx";

function App() {
    return (
        <Router>
            <Routes>
                {/* 로그인/회원가입 */}
                <Route path="/" element={<AccountTypeSelectPage />} />
                <Route path="/login-user" element={<UserLoginSelectPage />} />

                <Route path="/welcome" element={<TermsPage />} />

                <Route path="/terms" element={<UserLoginSelectPage />} />


            </Routes>
        </Router>
    );
}

export default App;
