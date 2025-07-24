import React from "react";
import { useNavigate } from "react-router-dom";

interface Props {
    hospital: any;
    onClose: () => void;
}

const HospitalDetailModal: React.FC<Props> = ({ hospital, onClose }) => {
    const navigate = useNavigate();

    const goToDetail = () => {
        navigate(`/hospital/${hospital.id || encodeURIComponent(hospital.place_name)}`, {
            state: {
                hospital: {
                    ...hospital,
                    imageUrl: hospital.image || "/images/default-hospital.jpg",
                    description: `${hospital.category_name || ""} 전문 병원입니다.`,
                    extraInfo: `📍 ${hospital.address_name} ｜ ☎ ${hospital.phone || "정보 없음"} ｜ 거리: ${hospital.distance || "-"}m`
                }
            }
        });
    };

    return (
        <div style={{
            position: "fixed",
            top: 0, left: 0, right: 0, bottom: 0,
            backgroundColor: "rgba(0, 0, 0, 0.4)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            zIndex: 1000
        }}>
            <div style={{
                background: "#fff",
                padding: 24,
                borderRadius: 10,
                width: 400,
                maxWidth: "80%",
                textAlign: "center"
            }}>
                <h3 style={{ marginBottom: 12 }}>{hospital.place_name}</h3>
                <p><b>주소:</b> {hospital.address_name}</p>
                {hospital.phone && <p><b>전화번호:</b> {hospital.phone}</p>}

                <p style={{ marginTop: 24 }}>상세 페이지를 확인하시겠습니까?</p>

                <div style={{ display: "flex", justifyContent: "center", gap: 16, marginTop: 20 }}>
                    <button
                        onClick={goToDetail}
                        style={{
                            background: "#9D7B77",
                            color: "#fff",
                            padding: "8px 16px",
                            border: "none",
                            borderRadius: 6,
                            cursor: "pointer"
                        }}
                    >
                        예
                    </button>
                    <button
                        onClick={onClose}
                        style={{
                            background: "#ccc",
                            color: "#333",
                            padding: "8px 16px",
                            border: "none",
                            borderRadius: 6,
                            cursor: "pointer"
                        }}
                    >
                        닫기
                    </button>
                </div>
            </div>
        </div>
    );
};

export default HospitalDetailModal;
