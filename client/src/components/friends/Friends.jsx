import { useState, useEffect } from 'react';
import './friends.scss'
import { Link } from 'react-router-dom'


const Friends = ({ userID, username }) => {


    const [friendsRequests, setFriendsRequests] = useState([]);
    const [friends, setFriends] = useState([]);
    const [loading, setLoading] = useState(false);
    const [hasMore, setHasMore] = useState(true);
    const limit = 15;
    const [offset, setOffset] = useState(0);



    const fetchFriendRequests = async () => {
        try {
            const response = await fetch(`http://localhost:3000/api/friend/request?limit=${limit}&offset=${offset}&userID=${userID}&search=`, {
                method: "GET",
            });
    
            const newFriends = await response.json();
    
            if(response.ok) {
                setFriendsRequests(newFriends); 
            }
            else {
                console.log('Error')
                setFriendsRequests([]);
            }
        } catch (error) {
            console.error("Błąd podczas pobierania danych:", error);
        }
    };

    const fetchFriend = async () => {
        if (loading || !hasMore) return;
        setLoading(true);
    
        try {
            const response = await fetch(`http://localhost:3000/api/friend?limit=${limit}&offset=${offset}&userID=${userID}&status=1&search=`, {
                method: "GET",
            });
    
            const newFriends = await response.json();
            console.log(newFriends)
            if (newFriends.length < limit) {
                setHasMore(false);
            }
    
            setFriends((prevFriends) => [...prevFriends, ...newFriends]); 
            setOffset((prevOffset) => prevOffset + limit);
        } catch (error) {
            console.error("Błąd podczas pobierania danych:", error);
        } finally {
            setLoading(false);
        }
    };

    const acceptFriendRequest = async (friendID) => {
        try {
            const response = await fetch(`http://localhost:3000/api/friend/${friendID}`, {
                method: "PUT",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({UserID: userID, Username: username}),
                credentials: 'include'
            });

            if (response.ok) {
                alert('Friend request accepted!');
            }
        } catch (error) {
            console.error("Error:", error);
        }
    }

    const removeFriend = async (friendID) => {
        try {
            const response = await fetch(`http://localhost:3000/api/friend/${friendID}`, {
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({UserID: userID}),
                credentials: 'include'
            });

            if (response.ok) {
                alert('Friend removed!');
            }
        } catch (error) {
            console.error("Error:", error);
        }
    }
    

    useEffect(() => {
        fetchFriendRequests()
        fetchFriend()
    }, []);

    return (
        <div className='friendscard'>
            <div className='friends'>
                <input type='text' placeholder='Search'/>
                {friendsRequests && friendsRequests.map(friend => (
                    <div className='friend' key={friend.UserID}>
                        <p>{friend.Username}</p>
                        <div className='buttons'>
                            <button className='show' onClick={() => acceptFriendRequest(friend.UserID)}>Accept</button>
                            <button className='delete' onClick={() => removeFriend(friend.UserID)}>Delete</button>
                        </div>
                    </div>
                ))}

                {friends && friends.map(friend => (
                    <div className='friend' key={friend.UserID}>
                        <p>{friend.Username}</p>
                        <div className='buttons'>
                            <Link to={`/friend/${friend.UserID}`} className='link'>
                            <button className='show'>Show profile </button>
                            </Link>
                            <button className='delete' onClick={() => removeFriend(friend.UserID)}>Delete</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}


export default Friends