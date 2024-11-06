import React from 'react';
import './ForgotPasswordModal.scss'; 

const ForgotPasswordModal = ({ isOpen, onClose }) => {
  if (!isOpen) return null; 

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>Forgot Password</h2>
        <p>Enter your email to reset your password.</p>
        <input type="email" placeholder="Email address" />
        <button onClick={onClose}>Close</button>
        <button>Send Reset Link</button>
      </div>
    </div>
  );
};

export default ForgotPasswordModal;
