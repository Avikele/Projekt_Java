import './home.scss'
import Books from '../../components/books/Books'

const Home = () => {
    return(
        <div className='home'>
            <Books path='book/'/>
            <Books path='book/'/>
            
            
        </div>
    )
}

export default Home