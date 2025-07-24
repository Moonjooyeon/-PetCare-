import styles from './HospitalDetailPage.module.css'; // 그대로 재사용
import ReactDOM from 'react-dom';
import { useEffect, useState } from 'react';
import axios from '../api/axios.ts';
import PetInfoModal from './PetInfoModal';

interface Props {
    hospital: any;
    onClose: () => void;
}

interface TimelineItem {
    date: string;
    content: string;
}

const mockData: TimelineItem[] = [
    {
        date: "2025-07-01",
        content: "병원이 새롭게 리뉴얼되었습니다! 반려동물 전용 수술실 추가 🔧",
    },
    {
        date: "2025-06-25",
        content: "주말 진료시간이 9시 → 10시로 변경되었습니다 ⏰",
    },
];

const HospitalDetailModal: React.FC<Props> = ({ hospital, onClose }) => {
    const [timelineList, setTimelineList] = useState<TimelineItem[]>([]);
    const [showModal, setShowModal] = useState(false);
    const [isMock, setIsMock] = useState(false);
    const [apiSuccess, setApiSuccess] = useState<boolean | null>(null);

    useEffect(() => {
        if (!hospital?.id) return;

        axios.get(`/api/hospitals/${hospital.id}/timeline`)
            .then(res => {
                const result = res.data.result ?? res.data;
                if (Array.isArray(result) && result.length > 0) {
                    setTimelineList(result);
                    setIsMock(false);
                    setApiSuccess(true);
                } else {
                    setTimelineList(mockData);
                    setIsMock(true);
                    setApiSuccess(true);
                }
            })
            .catch(() => {
                setTimelineList(mockData);
                setIsMock(true);
                setApiSuccess(false);
            });
    }, [hospital]);

    return ReactDOM.createPortal(
        <div style={{
        position: 'fixed',
            top: 0,
            left: 0,
            width: '100vw',
            height: '100vh',
            backgroundColor: 'rgba(0,0,0,0.3)',
            zIndex: 9999,
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
    }}>
    <div className={styles.pageContainer} style={{ maxHeight: '90vh', overflowY: 'auto' }}>
    <div className={styles.card}>
    <button onClick={onClose} style={{
        position: 'absolute', top: 20, right: 20, fontSize: 20, border: 'none', background: 'none'
    }}>✕</button>

    <div className={styles.imageBox}>
        {hospital.imageUrl ? (
                <img src={hospital.imageUrl} alt="병원 이미지" />
) : (
        <div className={styles.imagePlaceholder}>병원 이미지</div>
)}
    <button
        className={styles.followBtn}
    onClick={async () => {
        const token = localStorage.getItem("access_token");
        try {
            await axios.post('/api/follow', { hospitalId: hospital.id }, {
                headers: { Authorization: `Bearer ${token}` }
            });
            alert("팔로우 완료!");
        } catch {
            alert("팔로우 실패 😢");
        }
    }}
>
♥ 팔로우
    </button>
    </div>

    <div className={styles.pinkBox}>
        {hospital.address || "주소 정보 없음"} &nbsp; | &nbsp; ☎ {hospital.phone || "전화번호 없음"}
    </div>

    <div className={styles.yellowBox}>
        {hospital.description || "동물병원 전문 병원입니다."}
        </div>

    {/* 상태 메시지 */}
    <div style={{ margin: "12px 0" }}>
    {apiSuccess === true && !isMock && (
        <p style={{ color: "green", fontSize: "0.9rem" }}>✅ 타임라인을 불러왔습니다.</p>
    )}
    {apiSuccess === true && isMock && (
        <p style={{ color: "#888", fontSize: "0.9rem" }}>
    ✅ 예시 데이터입니다.
    </p>
    )}
    {apiSuccess === false && (
        <p style={{ color: "red", fontSize: "0.9rem" }}>❌ 호출 실패 — 예시 사용 중</p>
    )}
    </div>

    <div className={styles.timelineBox}>
    <h4 className={styles.timelineTitle}>🗓 병원 타임라인</h4>
    {timelineList.length === 0 ? (
        <p>등록된 기록이 없습니다.</p>
    ) : (
        <ul className={styles.timelineList}>
            {timelineList.map((item, index) => (
                    <li key={index} className={styles.timelineItem}>
                <strong>{item.date}</strong> — {item.content}
                </li>
    ))}
        </ul>
    )}
    </div>

    <div className={styles.footer}>
    <div className={styles.footerButtons}>
    <button className={styles.footerBtn} onClick={() => setShowModal(true)}>
✏️ 이 병원으로 기록 추가하기
    </button>
    <span style={{ margin: "0 16px" }} />
    <button className={styles.footerBtn} onClick={onClose}>
        닫기
        </button>
        </div>
        </div>
        </div>

    {showModal && <PetInfoModal onClose={() => setShowModal(false)} hospitals={[hospital]} />}
    </div>
    </div>,
        document.body
    );
    };

    export default HospitalDetailModal2;
