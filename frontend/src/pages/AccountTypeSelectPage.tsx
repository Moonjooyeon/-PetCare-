import { useNavigate } from 'react-router-dom';
import '../styles/AccountTypeSelectPage.css';

const AccountTypeSelectPage = () => {
    const navigate = useNavigate();

    const handleSelect = (type: 'user' | 'hospital') => {
        if (type === 'user') {
            // ✅ 이제 구글 로그인 주소로 바로 이동하지 않고 중간 단계로
            navigate('/login-user');
        } else {
            navigate('/admin/login');
        }
    };

    return (
        <div className="login-choice-container">
            <h2>로그인처리</h2>
            <div className="login-box">
                <button onClick={() => handleSelect('user')}>개인</button>
                <button onClick={() => handleSelect('hospital')}>병원용 계정</button>
            </div>
        </div>
    );
};

export default AccountTypeSelectPage;
