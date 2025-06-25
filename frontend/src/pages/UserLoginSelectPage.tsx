import '../styles/UserLoginSelectPage.css';

const UserLoginSelectPage = () => {
    const handleGoogleLogin = () => {
        window.location.href = 'http://localhost:8080/oauth2/authorization/google';
    };

    const handleSignUp = () => {
        alert('회원가입은 구글 로그인 후 자동 진행됩니다.');
        // 또는 따로 회원가입 폼 페이지로 이동
    };

    return (
        <div className="login-choice-container">
            <h2>로그인처리</h2>
            <div className="login-box">

                <button onClick={handleGoogleLogin}>구글로그인</button>
                <button onClick={handleSignUp}>회원가입</button>
            </div>
        </div>
    );
};

export default UserLoginSelectPage;
