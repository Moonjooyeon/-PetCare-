import { useEffect, useRef } from 'react';


const KakaoMap: React.FC = () => {
    const mapRef = useRef<HTMLDivElement>(null);
    const mapInstance = useRef<any>(null);
    const myPosition = useRef<{ lat: number; lng: number } | null>(null);

    const setCenterToMyPosition = () => {
        if (mapInstance.current && myPosition.current) {
            const { lat, lng } = myPosition.current;
            const moveLatLon = new window.kakao.maps.LatLng(lat, lng);
            mapInstance.current.setCenter(moveLatLon);
        }
    };


    useEffect(() => {
        const loadScript = () => {
            if (document.getElementById('kakao-map-script')) return;
            // 지도 객체와 내 위치를 저장할 ref 추가



            const script = document.createElement('script');
            script.id = 'kakao-map-script';
            script.src = encodeURI(
                'https://dapi.kakao.com/v2/maps/sdk.js?appkey=a132706876ac0642b6cd671a5f11ed9e&autoload=false&libraries=services'
            );
            script.async = true;

            script.onload = () => {
                if (window.kakao?.maps?.load) {
                    window.kakao.maps.load(() => {
                        if (!mapRef.current) {
                            console.warn('mapRef.current is null. DOM 연결 확인 필요');
                            return;
                        }

                        navigator.geolocation.getCurrentPosition(
                            (position) => {
                                const { latitude, longitude } = position.coords;
                                const center = new window.kakao.maps.LatLng(latitude, longitude);

                                const map = new window.kakao.maps.Map(mapRef.current!, {
                                    center,
                                    level: 3, // 확대해서 더 잘 보이게
                                });

                                mapInstance.current = map;


                                // 현재 위치 마커
                                new window.kakao.maps.Marker({
                                    map,
                                    position: center,
                                    image: new window.kakao.maps.MarkerImage(
                                        'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_blue.png', // 파란색 마커 (내 위치)
                                        new window.kakao.maps.Size(24, 35)
                                    ),
                                });



                                const ps = new window.kakao.maps.services.Places();
                                ps.keywordSearch(
                                    '동물병원',
                                    (data: any, status: string) => {
                                        if (status === window.kakao.maps.services.Status.OK) {
                                            data.forEach((place: any) => {
                                                const pos = new window.kakao.maps.LatLng(
                                                    Number(place.y),
                                                    Number(place.x)
                                                );

                                                console.log('🐾 병원 위치:', place.place_name, pos.getLat(), pos.getLng());


                                                const markerImage = new window.kakao.maps.MarkerImage(
                                                    'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png',
                                                    new window.kakao.maps.Size(24, 35)
                                                );

                                                const marker = new window.kakao.maps.Marker({
                                                    map,
                                                    position: pos,
                                                    image: markerImage,
                                                });

                                                // 지도 중심 이동 (테스트용)
                                                map.panTo(pos);

                                                const infowindow = new window.kakao.maps.InfoWindow({ zIndex: 1 });

                                                window.kakao.maps.event.addListener(marker, 'click', () => {
                                                    const content = `
                                                 <div style="min-width:210px;max-width:300px;padding:10px;border-radius:12px;box-shadow:0 2px 8px #bbb;background:#fff;">
                                                  <strong style="font-size:15px;">${place.place_name}</strong>
                                                  <br/>
                                                    <span style="color:#888;">${place.address_name ?? ''}</span>
                              <br/>
                              <span style="color:#db2828;font-weight:bold;">${place.category_name ?? ''}</span>
                              <br/>
                              <span style="color:#666;font-size:12px;">거리: 약 ${place.distance}m</span>
                              <br/><br/>
                              <button style="padding:4px 12px;border-radius:5px;background:#f4f5fa;color:#603cff;border:none;cursor:pointer;">상세보기</button>
                            </div>
                          `;
                                                    infowindow.setContent(content);
                                                    infowindow.open(map, marker);
                                                });
                                            });

                                            console.log(`✅ ${data.length}개 동물병원 마커 생성 완료`);
                                        } else {
                                            console.warn('동물병원 검색 결과 없음');
                                        }
                                    },
                                    {
                                        location: center,
                                        radius: 5000,
                                    }
                                );
                            },
                            (error) => {
                                console.error('❌ 위치 정보 가져오기 실패:', error);

                                const fallbackCenter = new window.kakao.maps.LatLng(37.5665, 126.9780);
                                new window.kakao.maps.Map(mapRef.current!, {
                                    center: fallbackCenter,
                                    level: 5,
                                });
                            }
                        );
                    });
                }
            };

            script.onerror = () => {
                console.error('Kakao Maps SDK 로딩 실패');
            };

            document.head.appendChild(script);
        };

        loadScript();
    }, []);

    return (
    <div style={{ position: 'relative', width: '100%', height: '500px' }}>
        <div ref={mapRef} style={{ width: '100%', height: '100%' }} />
        {/* 현재 위치로 이동 버튼 (원하면 디자인 개선 가능) */}
        <button
            onClick={setCenterToMyPosition}
            style={{
                position: 'absolute',
                bottom: 20,
                right: 20,
                zIndex: 10,
                padding: '10px 16px',
                borderRadius: 8,
                border: 'none',
                background: '#fff',
                boxShadow: '0 1px 8px #aaa',
                fontWeight: 600,
                cursor: 'pointer',
            }}
        >

        </button>
    </div>
);
};

export default KakaoMap;
