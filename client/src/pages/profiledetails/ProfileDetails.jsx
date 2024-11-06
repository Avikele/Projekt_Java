import { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import './profileDetails.scss';
import ChangeAccountDetails from '../../components/changeAcountDetails/ChangeAccountDetails';

const ProfileDetails = ({ userID }) => {

    const [userData, setUserData] = useState(null)
    const [isModalOpen, setModalOpen] = useState(false);
    const [detail, setDetail] = useState("");

    const openModal = (detailType) => {
        setDetail(detailType);
        setModalOpen(true);
    };

    const closeModal = () => {
        setModalOpen(false)
    };

    useEffect(() => {
        if (!userData) {
            fetch(`http://localhost:3000/api/user/${userID}`, {
                method: "GET"
            })
                .then((response) => response.json())
                .then((data) => {
                    setUserData(data);
                })
                .catch((error) => console.log(error));
        }
    }, []);

    useEffect(() => {
        if (!isModalOpen) {
            if (userData) {
                fetch(`http://localhost:3000/api/user`, {
                    method: "PUT",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(userData)
                })
                    .then((response) => {
                        if (!response.ok) {
                            throw new Error(`HTTP error! status: ${response.status}`);
                        }
                        return response.json();
                    })
                    .then((data) => {
                        console.log(data);
                    })
                    .catch((error) => console.error("Error updating user:", error));
            }
        }
    }, [isModalOpen, userData]);

    return (
        <div className='accountCard'>
            <div className='accountDetails'>
                <h1>Account details</h1>
                <div className='details'>
                    {userData && <>
                        <p>Username: {userData.Username}</p>
                        <button className='change' onClick={() => openModal("Username")}>Change your username</button>

                        <p>Email: {userData.Email}</p>
                        <button className='change' onClick={() => openModal("Email")}>Change your email</button>

                        <p>Name: {userData.Name}</p>
                        <button className='change' onClick={() => openModal("Name")}>Change your name</button>

                        <p>Surname: {userData.Surname}</p>
                        <button className='change' onClick={() => openModal("Surname")}>Change your surname</button>

                        <button className='change password' onClick={() => openModal("Password")}>Change your password</button>
                    </>}
                </div>
                <button className='delete'>Delete account</button>
            </div>


            <ChangeAccountDetails
                isOpen={isModalOpen}
                onClose={closeModal}
                detail={detail}
                setUserData={setUserData}
            />
        </div>
    );
};

export default ProfileDetails;
