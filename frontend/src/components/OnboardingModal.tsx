// src/components/OnboardingModal.tsx
import React from 'react';
import '../styles/OnboardingModal.css';

interface OnboardingModalProps {
    isOpen: boolean;
    onClose: () => void;
    onNext?: () => void;
    showNext?: boolean;
    children: React.ReactNode;
}

const OnboardingModal: React.FC<OnboardingModalProps> = ({
                                                             isOpen,
                                                             onClose,
                                                             onNext,
                                                             showNext = true,
                                                             children,
                                                         }) => {
    if (!isOpen) return null;

    return (
        <div className="onboarding-modal-overlay">
            <div className="onboarding-modal-content">
                <button className="close-button" onClick={onClose}>✕</button>
                <div className="onboarding-modal-body">
                    {children}
                </div>
                {showNext && (
                    <button className="next-button" onClick={onNext}>다음</button>
                )}
            </div>
        </div>
    );
};

export default OnboardingModal;
