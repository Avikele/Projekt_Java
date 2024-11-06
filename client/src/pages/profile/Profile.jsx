import { useState, useEffect } from 'react'
import './profile.scss'
import Friends from '../../components/friends/Friends'
import { Link } from 'react-router-dom'
import AddFriend from '../../components/addFriend/addFriend';

const Profile = ({ userID, username }) => {

    const [stats, setStats] = useState(null);
    const [isModalOpen, setModalOpen] = useState(false);

    const openModal = () => {
        setModalOpen(true);
    };

    const closeModal = () => {
        setModalOpen(false)
    };


    useEffect(() => {
        fetch(`http://localhost:3000/api/user/stats/${userID}`, {
            method: "GET"
        })
            .then((response) => response.json())
            .then((data) => {
                setStats(data);
            })
            .catch((error) => console.log(error));
    }, []);

    return (
        <div className='profile'>

            <div className='account'>
                <h1>Hello, {username}</h1>
                <h2>Statistics: </h2>
                {stats &&
                    <div className='statistics'>
                        <p>Books read so far: 200</p>
                        <p>Books read this year: {stats.BookReadedLastYear}</p>
                        <p>Books read this month: {stats.BookReadedLastMonth}</p>
                        <p>Pages read: {stats.PagesRead}</p>
                        <p>Time spend on reading: {stats.Time}</p>
                        <p>Duels won: 20</p>
                        <p>Duels lost: 15</p>
                        <p> Your nemesis: Darek</p>
                    </div>
                }
                <Link className='link' to='/profile/details/1'>
                    <button className='details'>Account details</button>
                </Link>
            </div>
            <div className='friend-list'>
                <h1>Friend list</h1>
                <Friends userID={userID} username={username}/>
                <button className='add' onClick={openModal}> Add a friend</button>
            </div>


            <AddFriend
                isOpen={isModalOpen}
                onClose={closeModal}
                username={username}
                userID={userID}
            />
        </div>

    )
}
export default Profile 