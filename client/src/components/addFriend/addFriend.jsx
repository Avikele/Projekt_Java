import React, { useState, useEffect } from 'react';
import "./addFriend.scss";

const AddFriend = ({ isOpen, onClose, userID, username }) => {

    const [search, setSearch] = useState('');
    const [friends, setFriends] = useState([]);
    const [loading, setLoading] = useState(false);
    const [hasMore, setHasMore] = useState(true);
    const limit = 15;
    const [offset, setOffset] = useState(0);

    useEffect(() => {
        setSearch('');
    }, [isOpen]);

    const fetchFriend = async () => {
        if (loading || !hasMore) return;
        setLoading(true);
    
        try {
            const response = await fetch(`http://localhost:3000/api/user?limit=${limit}&offset=${offset}&userID=${userID}&search=${search}`, {
                method: "GET",
            });
    
            const newFriends = await response.json();
            console.log(newFriends)
            if (newFriends.length < limit) {
                setHasMore(false);
            }
            console.log(newFriends)
    
            setFriends((prevFriends) => [...prevFriends, ...newFriends]); 
            setOffset((prevOffset) => prevOffset + limit);
        } catch (error) {
            console.error("Error:", error);
        } finally {
            setLoading(false);
        }
    };

    const sendFriendRequest = async (friendID) => {
        try {
            const response = await fetch(`http://localhost:3000/api/friend/${friendID}`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({UserID: userID, Username: username}),
                credentials: 'include'
            });

            if (response.ok) {
                alert('Friend request sended!');
            }
        } catch (error) {
            console.error("Error:", error);
        }
        
    };

    useEffect(() => {
        const timeoutId = setTimeout(() => {
            if (search) {
                setFriends([]);
                setOffset(0);
                setHasMore(true);
                fetchFriend();
            }
        }, 500);

        return () => clearTimeout(timeoutId);
    }, [search]);

    if (!isOpen) return null;

    return (
        <div className='modal-overlay-newfriend' onClick={onClose}>
            <div className='modal-add-friend-content' onClick={(e) => e.stopPropagation()}>
                <h2>Send a friend request</h2>
                <div className='friendscard-newfriend'>
                    <div className='friends-newfriend'>
                        <input 
                            type='text' 
                            placeholder='Search'
                            value={search} 
                            onChange={(e) => setSearch(e.target.value)}
                        />
                        {friends.map((friend, index) => (
                            <div className='friend-newfriend' key={friend.UserID}>
                            <p>{friend.Username}</p>
                            <div className='buttons'>
                                <button className='show' onClick={ () => sendFriendRequest(friend.UserID)}>Add</button>
                            </div>
                        </div>
                        ))}
                        {loading && <p>Loading...</p>}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddFriend;
