import { useState, useEffect } from 'react';
import './friendProfile.scss'
import { useParams } from 'react-router-dom';

const FriendProfile = () => {

    const { id } = useParams();

    const [friend, setFriend] = useState(null);


    useEffect(() => {
        fetch(`http://localhost:3000/api/user/stats/${id}`, {
            method: "GET"
        })
            .then((response) => response.json())
            .then((data) => {
                setFriend(data);
            })
            .catch((error) => console.log(error));
    }, []);

    return (
        
        <div className='friendProfile'>
            <div className='friendStatistics'>
            {friend && <>
                <h1>{friend.Username}'s statistics</h1>
                <div className='statistics'>
                    <p>Books read so far: 200</p>
                    <p>Books read this year: {friend.BookReadedLastYear}</p>
                    <p>Books read this month: {friend.BookReadedLastMonth}</p>
                    <p>Pages read: {friend.PagesRead}</p>
                    <p>Time spend on reading: {friend.Time}</p>
                    <p>Duels won: 20</p>
                    <p>Duels lost: 15</p>
                    <p> Your nemesis: Darek</p>

                </div>
                </>
            }
            </div>
        </div>

    )

}

export default FriendProfile