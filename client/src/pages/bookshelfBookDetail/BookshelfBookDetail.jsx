import './bookshelfBookDetail.scss'
import { Link, useParams } from 'react-router-dom';

const BookshelfBookDetail = () => {

    const { id } = useParams();

    const books = [
        {
            id:1,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630"
        },
        {
            id:2,
            name: "Soul",
            img: "https://marketplace.canva.com/EAFaQMYuZbo/1/0/1003w/canva-brown-rusty-mystery-novel-book-cover-hG1QhA7BiBU.jpg"
        },
        {
            id:3,
            name: "Hungry for her Wolves",
            img: "https://miblart.com/wp-content/uploads/2024/01/main-3-1-scaled.jpg"
        },
        {
            id:4,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630"
        },
        {
            id:5,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630"
        },
    ]

    const book = books.find(b=> b.id === parseInt(id))

    return (
        <div className='bookshelf-book-detail-card'>
            <div className='book-cover'>
                <img src={book.img} alt=''/>
            </div>
            <div className='book-info'>
                <h1>Book Info</h1>
                <p> Title </p>
                <p>Author</p>
                <p>Publisher</p>
                <Link to="reading" className='link'>
                <button> Start reading</button>
                </Link>
                <button>Chalange a friend</button>
            </div>
            <div className='user-review'>
                <div className='review'>
                <h1>Your review</h1>
                <p>Goodb Book</p>
                </div>
                <button>Edit Review</button>
            </div>
            <div className='reading-statistics'>
                <h1>Reading statistics</h1>
                <p>Pages read: 10/200</p>
                <p>Time Reading</p>
                <p>Reading Sesions: 1</p>
            </div>
        </div>
        
    )
}

export default BookshelfBookDetail