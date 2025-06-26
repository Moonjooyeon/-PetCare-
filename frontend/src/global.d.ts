export {};

declare global {
    interface Window {
        kakao: typeof kakao;
    }
}

/* eslint-disable @typescript-eslint/no-unused-vars */
declare namespace kakao {
    namespace maps {
        class LatLng {
            constructor(lat: number, lng: number);
            getLat(): number;
            getLng(): number;
        }

        class MarkerImage {
            constructor(src: string, size: kakao.maps.Size);
        }

        class Size {
            constructor(width: number, height: number);
        }


        class Map {
            constructor(container: HTMLElement, options: any);
            panTo(latlng: kakao.maps.LatLng): void;
        }



        class Map {
            constructor(container: HTMLElement, options: any);
        }

        class Marker {
            constructor(options: any);
        }

        class InfoWindow {
            constructor(options: { content?: string; zIndex?: number });
            open(map: kakao.maps.Map, marker: kakao.maps.Marker): void;
            close(): void;
            setContent(content: string): void;
        }

        namespace event {
            function addListener(
                target: any,
                type: string,
                callback: (...args: any[]) => void
            ): void;
        }

        namespace services {
            class Places {
                keywordSearch(
                    keyword: string,
                    callback: (data: any[], status: string) => void,
                    options?: {
                        location?: kakao.maps.LatLng;
                        radius?: number;
                    }
                ): void;
            }

            const Status: {
                OK: string;
                ERROR: string;
                ZERO_RESULT: string;
            };
        }

        const load: (callback: () => void) => void;
    }
}
