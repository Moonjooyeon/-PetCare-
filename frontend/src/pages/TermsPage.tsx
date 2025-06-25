import '../styles/TermsPage.css';
import { useNavigate } from 'react-router-dom';

const TermsPage = () => {
    const navigate = useNavigate();

    return (
        <div className="terms-container">

            <div className="terms-box">
                <button onClick={() => navigate('/map')}>내 근처병원 찾기</button>
                <button onClick={() => navigate('/register-hospital')}>내 병원 등록하기</button>
                <button onClick={() => navigate('/add-record')}>진료기록 등록</button>
            </div>
        </div>
    );
};

export default TermsPage;
