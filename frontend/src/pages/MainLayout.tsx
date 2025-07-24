// src/pages/MainLayout.tsx
import React from 'react';
import styles from './MainLayout.module.css';

const MainLayout = () => {
    return (
        <div className={styles.mainContainer}>
            <aside className={styles.sidebar}>
                <div className={styles.menu}>홈 (이자 타임라인)</div>
                <div className={styles.menu}>병원찾기</div>
                <div className={styles.menu}>병원 등록</div>
                <div className={styles.menu}>알림</div>
                <div className={styles.menu}>내 기록</div>
                <div className={styles.menu}>마이페이지</div>
            </aside>

            <main className={styles.content}>
                <header className={styles.topHeader}>메인 홈 - 개인</header>
                <div className={styles.mainContent}>내용 자리</div>
                <button className={styles.aiButton}>ai 상담</button>
            </main>

            <aside className={styles.rightPanel}>
                <div className={styles.calendar}>캘린더 자리</div>
                <div className={styles.nextSchedule}>일정 (다음 일정)</div>
            </aside>
        </div>
    );
};

export default MainLayout;
