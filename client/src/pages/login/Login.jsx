import { useState } from 'react';
import { Link, useNavigate } from "react-router-dom"
import "./login.scss"
import ForgotPasswordModal from '../../components/forgotPassword/ForgotPasswordModal';

const Login = () => {

    const [isModalOpen, setModalOpen] = useState(false);

    const openModal = () => setModalOpen(true);
    const closeModal = () => setModalOpen(false);

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('')
    const [loading, setLoading] = useState(false)

    const navigate = useNavigate()


    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        if (!email || !password) {
            setError('Please enter both email and password.')
            setLoading(false);
            return;
        }



        const loginData = {
            Email: email,
            Password: password,
        };

        try {

            const response = await fetch(`http://localhost:3000/api/login`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData),
                credentials: 'include'
            })
            const data = await response.json();

            if (response.ok) {
                localStorage.setItem('user', JSON.stringify(data.User));
                localStorage.setItem('token', JSON.stringify(data.Token));
                navigate('/')

            } else {
                setError(data.error || 'Failed to log in. Please check your credential.')
            }

        }catch(error){
            console.error('Error:', error);
            setError('Something went wrong. Please try again later.')
        }finally{
            setLoading(false);
        }
        
    };


    return (
        <div className="login">
            <div className="card">
                <h1> Log in </h1>
                {error && <p className='error-message'>{error}</p>}
                <form onSubmit={handleSubmit}>
                    <input 
                        type="text" 
                        placeholder="Email" 
                        value={email} onChange={(e) => setEmail(e.target.value)} 
                        disabled = {loading} />
                    <input 
                        type="password" 
                        placeholder="Password" 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)}
                        disabled = {loading} />
                    <button type='submit' disabled={loading}>
                        {loading ? 'logging in' : 'Log in'}</button>
                </form>
                <Link to="/register" className="link">
                    <h2>Sign up</h2>
                </Link>
                <p onClick={openModal} className="forgot-password">Forgot password?</p>

            </div>
            <ForgotPasswordModal isOpen={isModalOpen} onClose={closeModal} />
        </div>




    )
}

export default Login