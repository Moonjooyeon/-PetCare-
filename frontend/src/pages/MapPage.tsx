// src/pages/MapPage.tsx
import React from 'react';
import KakaoMap from '../components/KakaoMap';

const MapPage: React.FC = () => {
    return (
        <div style={{ padding: '24px' }}>
            <h2>내 근처 병원 찾기</h2>
            <KakaoMap />

            <div style={{ marginTop: '20px' }}>
                <button>[선택한 병원 목록]</button>
                <button>[이전으로]</button>
            </div>
        </div>
    );
};


export default MapPage;
