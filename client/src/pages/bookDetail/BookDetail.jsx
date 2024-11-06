import './bookDetail.scss';
import StarBorderIcon from '@mui/icons-material/StarBorder';
import StarIcon from '@mui/icons-material/Star';
import StarHalfIcon from '@mui/icons-material/StarHalf';
import { useState } from 'react';
import { useParams } from 'react-router-dom';

const BookDetail = () => {
    const { id } = useParams();

    const reviews = [
        {
            id: 1,
            text: "God boooooook",
            user: "daniel"
        },
        {
            id: 2,
            text: "An incredible read! The characters are well-developed, and the plot twists kept me on my toes. Highly recommended for anyone who enjoys a thrilling story.",
            user: "emily"
        },
        {
            id: 3,
            text: "A gripping narrative that draws you in from the first page. The author's writing style is captivating, and I couldn't put it down until I finished!",
            user: "michael"
        },
        {
            id: 4,
            text: "This book changed my perspective on life. The themes of love, loss, and redemption are beautifully woven into the storyline. A must-read for everyone.",
            user: "sarah"
        },
        {
            id: 5,
            text: "Not what I expected, but in a good way! The plot took some unexpected turns that kept me engaged. Will definitely read more from this author.",
            user: "john"
        },
        {
            id: 6,
            text: "A masterpiece! The prose is lyrical, and the emotions felt so real. This book will stay with me for a long time. I highly recommend it!",
            user: "laura"
        },
        {
            id: 7,
            text: "It was a bit slow at first, but once it picked up, I was hooked! The character development is deep, and the ending was satisfying. Worth the read!",
            user: "james"
        },
        {
            id: 8,
            text: "I found the themes relatable and the story heartwarming. A lovely read for anyone who enjoys a bit of romance with their drama.",
            user: "kate"
        },
        {
            id: 9,
            text: "I had high expectations, and this book did not disappoint! The humor was spot-on, and the dialogue felt authentic. A fun read from start to finish.",
            user: "alex"
        },
        {
            id: 10,
            text: "This book is an emotional rollercoaster! I laughed, cried, and felt everything in between. If you're looking for a book that tugs at your heartstrings, pick this up!",
            user: "olivia"
        },
        {
            id: 11,
            text: "A compelling read that addresses important social issues while keeping the story entertaining. The characters are relatable and well-written.",
            user: "liam"
        },
        {
            id: 12,
            text: "I was drawn in by the unique storyline and fascinating world-building. The author's imagination is boundless, and I can't wait for the sequel!",
            user: "sofia"
        },
        {
            id: 13,
            text: "A thoughtful exploration of friendship and loyalty. The story flows well and leaves a lasting impact. Definitely a book I'll recommend to others.",
            user: "ethan"
        },
        {
            id: 14,
            text: "The pacing was perfect, and the tension built beautifully throughout the story. I appreciated the way the author tackled complex themes with nuance.",
            user: "mia"
        },
        {
            id: 15,
            text: "An engaging story with a fantastic setting! The vivid descriptions made me feel like I was right there in the story. I loved every moment of it!",
            user: "noah"
        }
    ];




    const books = [
        {
            id: 1,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630",
            rating: 1
        },
        {
            id: 2,
            name: "Soul",
            img: "https://marketplace.canva.com/EAFaQMYuZbo/1/0/1003w/canva-brown-rusty-mystery-novel-book-cover-hG1QhA7BiBU.jpg",
            rating: 3.5
        },
        {
            id: 3,
            name: "Hungry for her Wolves",
            img: "https://miblart.com/wp-content/uploads/2024/01/main-3-1-scaled.jpg",
            rating: 2.5
        },
        {
            id: 4,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630",
            rating: 5
        },
        {
            id: 5,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630",
            rating: 4.5
        },
    ];

    const book = books.find(b => b.id === parseInt(id));

    if (!book) {
        return <p>Book not found</p>; // Handle case where book is not found
    }

    const [userRating, setUserRating] = useState(0); // User's rating starts at 0

    const handleUserStarClick = (value) => {
        setUserRating(value);
    };

    // Function to render the book's current rating
    const renderBookRating = () => {
        const stars = [];
        const fullStars = Math.floor(book.rating);
        const halfStar = book.rating % 1 >= 0.5;

        // Full stars for book rating
        for (let i = 0; i < fullStars; i++) {
            stars.push(<StarIcon key={`book-star-full-${i}`} className="star" />);
        }

        // Half star for book rating
        if (halfStar) {
            stars.push(<StarHalfIcon key="book-star-half" className="star" />);
        }

        // Empty stars for book rating
        for (let i = stars.length; i < 5; i++) {
            stars.push(<StarBorderIcon key={`book-star-empty-${i}`} className="star" />);
        }

        return stars;
    };

    // Function to render the user's rating
    const renderUserRating = () => {
        const stars = [];
        const fullStars = Math.floor(userRating);
        const halfStar = userRating % 1 >= 0.5;

        // Full stars for user rating
        for (let i = 0; i < fullStars; i++) {
            stars.push(
                <StarIcon key={`user-star-full-${i}`} className="star" onClick={() => handleUserStarClick(i + 1)} />
            );
        }

        // Half star for user rating
        if (halfStar) {
            stars.push(<StarHalfIcon key="user-star-half" className="star" onClick={() => handleUserStarClick(fullStars + 0.5)} />);
        }

        // Empty stars for user rating
        for (let i = stars.length; i < 5; i++) {
            stars.push(
                <StarBorderIcon key={`user-star-empty-${i}`} className="star" onClick={() => handleUserStarClick(i + 1)} />
            );
        }

        return stars;
    };

    return (
        <div className="book-detail">
            <div className="book-and-rating">
                <div className="book-card">
                    <div className='book-cover'>
                        <img src={book.img} alt="" />
                    </div>
                    <div className='book-rating'>
                        <h2>Book info</h2>
                        <p>Title: {book.name}</p>
                        <p>Author</p>
                        <p>Publisher</p>
                        <div className="rating">{renderBookRating()}</div>
                        <button>Add to a bookshelf</button>
                    </div>
                </div>
                <div className='user-ratings'>
                    <h2>Add review</h2>
                    <textarea
                        placeholder="Write your review here..."
                        rows="11" 
                    />
                    <div className='submit'>
                        <div className="rating">{renderUserRating()}</div>
                        <button>Submit</button>
                    </div>
                </div>
            </div>
            <div className='reviews'>
                <h2>User Reviews</h2>
                {reviews.map(review => (
                    <div className='review' key={review.id}>
                        <h3>{review.user}</h3>
                        <p>{review.text}</p>
                    </div>
                ))}


            </div>
        </div>
    );
};

export default BookDetail;
