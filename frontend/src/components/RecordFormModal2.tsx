import styles from './RecordFormModal.module.css';

const RecordFormModal = ({ onClose }) => {
    return (
        <div className={styles.overlay}>
        <div className={styles.modal}>
        <button className={styles.closeBtn} onClick={onClose}>×</button>

    <form className={styles.form}>
        <label>진료 날짜<input type="date" /></label>
        <label>병원명<input type="text" /></label>
        <label>반려동물 이름<input type="text" /></label>
        <label>증상<input type="text" /></label>
        <label>진단내용<input type="text" /></label>
        <label>다음일정 등록<input type="date" /></label>
        <label>메모<textarea /></label>
        <label className={styles.imageUpload}>사진 업로드<input type="file" /></label>
        <button type="submit" className={styles.submitBtn}>저장하기</button>
        </form>
        </div>
        </div>
);
};
