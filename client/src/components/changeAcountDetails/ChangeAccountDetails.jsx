import React, { useState, useEffect } from 'react';
import "./changeAccountDetail.scss";

const ChangeAccountDetails = ({ isOpen, onClose, detail, setUserData }) => {

    const [data, setData] = useState('');
    const [oldPassword, setOldPassword] = useState('');
    const editData = () => {
        if (!data) {
            alert(`Please enter a new ${detail}.`);
            return;
        }
        setUserData((prevData) => ({
            ...prevData,
            [detail]: data,
            OldPassword: oldPassword
        }))
        setOldPassword('')
        setData('')
        onClose()
    }

    useEffect(() => {
        if (isOpen) {
            setData('');
            setOldPassword('');
        }
    }, [isOpen]);

    let type;
    if(detail == "Password"){
        type = 'password'
    }
    else {
        type = 'text'
    }

    if (!isOpen) return null;

    return (
        <div className='modal-overlay' onClick={onClose}>
            <div className='modal-content' onClick={(e) => e.stopPropagation()}>
                <h2>Change {detail}</h2>
                { detail == "Password" &&  <input type={type} placeholder={`Previews Password`} value={oldPassword} onChange={(e) => setOldPassword(e.target.value)} />}
                <input type={type} placeholder={`New ${detail}`} value={data} onChange={(e) => setData(e.target.value)} />
                <button onClick={editData}>Change {detail}</button>
                <button onClick={onClose}>Close</button>
            </div>
        </div>
    );
};

export default ChangeAccountDetails;
