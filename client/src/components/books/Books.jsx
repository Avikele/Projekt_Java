import "./books.scss"
import React, { useRef, useState } from 'react'
import { Link } from "react-router-dom";


const Books = ({ path }) => {
    const [isDragging, setIsDragging] = useState(false);
    const [startX, setStartX] = useState(0);
    const [scrollLeft, setScrollLeft] = useState(0);
    const booksRef = useRef(null);
    const [initialMouseX, setInitialMouseX] = useState(0);

    const title = "Polecane"

    const books = [
        {
            id: 1,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630"
        },
        {
            id: 2,
            name: "Soul",
            img: "https://marketplace.canva.com/EAFaQMYuZbo/1/0/1003w/canva-brown-rusty-mystery-novel-book-cover-hG1QhA7BiBU.jpg"
        },
        {
            id: 3,
            name: "Hungry for her Wolves",
            img: "https://miblart.com/wp-content/uploads/2024/01/main-3-1-scaled.jpg"
        },
        {
            id: 4,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630"
        },
        {
            id: 5,
            name: "Książka dla psa",
            img: "https://www.agroswiat.pl/media/products/4549753a2fed7ab8c527cbfaf8bd6cdb/images/thumbnail/big_Ksiazka-dla-psa.jpg?lm=1645731630"
        },

    ]


    const handleMouseDown = (e) => {
        e.preventDefault();
        setIsDragging(true);
        setStartX(e.pageX - booksRef.current.offsetLeft);
        setScrollLeft(booksRef.current.scrollLeft);
        setInitialMouseX(e.pageX);
    };

    const handleMouseLeave = () => {

        setIsDragging(false);
    };
    const handleMouseUp = () => {

        if (isDragging) {
            setIsDragging(false);
        } else {

        }
    };

    const handleMouseMove = (e) => {
        if (!isDragging) return;
        e.preventDefault();
        const x = e.pageX - booksRef.current.offsetLeft;
        const walk = (x - startX) * 2;
        booksRef.current.scrollLeft = scrollLeft - walk;
    };

    const handleLinkMouseDown = (e) => {
        if (isDragging) {
            e.preventDefault();
        }
    };

    const handleLinkClick = (e) => {
        const mouseMoved = Math.abs(e.pageX - initialMouseX) > 1;
        if (isDragging || mouseMoved) {
            e.preventDefault();
        }
    };


    return (
        <div className="card">
            <h4>{title}</h4>
            <div className="books"
                ref={booksRef}
                onMouseDown={handleMouseDown}
                onMouseLeave={handleMouseLeave}
                onMouseUp={handleMouseUp}
                onMouseMove={handleMouseMove}>
                {books.map(book => (
                    <Link to={`${path}${book.id}`} onMouseDown={handleLinkMouseDown} onClick={handleLinkClick} key={book.id} className={isDragging ? 'dragging' : 'notdragging'}>
                        <div className="book" key={book.id}>

                            <img src={book.img} alt="" />

                            <p>{book.name}</p>
                        </div>
                    </Link>
                ))}
            </div>
        </div>
    )
}

export default Books