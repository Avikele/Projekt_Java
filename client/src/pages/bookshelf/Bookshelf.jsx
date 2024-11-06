import Books from '../../components/books/Books'

const Bookshelf = () => {
    return(
        <div className='home'>
            <Books path='book/'/>
            <Books path='book/'/>
            <Books path='book/'/>
            <Books path='book/'/>
            
        </div>
    )
}

export default Bookshelf