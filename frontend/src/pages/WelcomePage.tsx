// src/pages/WelcomePage.tsx

import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const WelcomePage = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const query = new URLSearchParams(window.location.search);
        const token = query.get('token');
        const userName = query.get('userName');
        const onboardingFinished = query.get('onboardingFinished');

        if (token && userName) {
            // 로컬 스토리지 저장
            localStorage.setItem('access_token', token);
            localStorage.setItem('user_name', userName);

            // 이후 페이지로 이동
            if (onboardingFinished === 'true') {
                navigate('/terms'); //여긴 추후 변경 메인 홈으로 가게
            } else {
                navigate('/terms'); // 또는 초기 설정 페이지
            }
        } else {
            alert('로그인 정보가 없습니다.');
            navigate('/');
        }
    }, []);

    return <div>로그인 중입니다...</div>;
};

export default WelcomePage;
