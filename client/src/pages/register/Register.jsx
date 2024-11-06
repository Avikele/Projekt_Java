import { Link, useNavigate } from "react-router-dom";
import "./register.scss";
import { useState } from "react";

const Register = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [repeatPassword, setRepeatPassword] = useState('');
    const [username, setUsername] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();


    const validateEmail = (email) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailRegex.test(email);
    }

    const validateNameOrSurname = (text) => {
        const nameRegex = /^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*$/;
        return nameRegex.test(text);
    };

    const validatePassword = (password) => {
        const lengthRegex = /.{8,}/; // At least 8 characters
        const uppercaseRegex = /[A-Z]/; // At least one uppercase letter
        const specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/; // At least one special character
    
        return lengthRegex.test(password) && 
               uppercaseRegex.test(password) && 
               specialCharRegex.test(password);
    };


    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        
        // Basic validation
        if (!email || !password || !repeatPassword || !username || !name || !surname) {
            setError('Please fill out all fields.');
            return;
        }

        if (password !== repeatPassword) {
            setError('Passwords do not match.');
            return;
        }

        if (!validatePassword(password)) {
            setError('Password must be at least 8 characters long, contain at least one uppercase letter and one special character.');
            return;
        }
        if (!validateEmail(email)) {
            setError('Invalid email format. Please enter a valid email address.');
            return;
        }

        if (!validateNameOrSurname(name)) {
            setError('Name must start with an uppercase letter and contain only letters.');
            return;
        }

        if (!validateNameOrSurname(surname)) {
            setError('Surname must start with an uppercase letter and contain only letters.');
            return;
        }


        const userData = {
            Email: email,
            Password: password,
            Username: username,
            Name: name,
            Surname: surname
        };

        setLoading(true);

        try {
            const response = await fetch(`http://localhost:3000/api/user`, {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            const data = await response.json();

            if (response.ok) {
                // Handle successful registration (e.g., redirect to login)
                alert('Registration successful! Please log in.');
                navigate('/login');
            } else {
                // Handle errors from the backend (e.g., email already exists)
                setError(data.error || 'Registration failed. Please try again.');
            }
        } catch (error) {
            console.error('Error:', error);
            setError('Something went wrong. Please try again later.');
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="register">
            <div className="card">
                <h1>Sign up</h1>
                {error && <p className="error-message">{error}</p>}
                <form onSubmit={handleSubmit}>
                    <div className="card2">
                        <div className="form1">
                            <input 
                                type="email" 
                                placeholder="Email" 
                                value={email} 
                                onChange={(e) => setEmail(e.target.value)}
                                disabled={loading}
                            />
                            <input 
                                type="password" 
                                placeholder="Password" 
                                value={password} 
                                onChange={(e) => setPassword(e.target.value)}
                                disabled={loading}
                            />
                            <input 
                                type="password" 
                                placeholder="Repeat password" 
                                value={repeatPassword} 
                                onChange={(e) => setRepeatPassword(e.target.value)}
                                disabled={loading}
                            />
                        </div>
                        <div className="form2">
                            <input 
                                type="text" 
                                placeholder="Username" 
                                value={username} 
                                onChange={(e) => setUsername(e.target.value)}
                                disabled={loading}
                            />
                            <input 
                                type="text" 
                                placeholder="Name" 
                                value={name} 
                                onChange={(e) => setName(e.target.value)}
                                disabled={loading}
                            />
                            <input 
                                type="text" 
                                placeholder="Surname" 
                                value={surname} 
                                onChange={(e) => setSurname(e.target.value)}
                                disabled={loading}
                            />
                        </div>
                    </div>
                    <button type="submit" disabled={loading}>
                        {loading ? 'Signing up...' : 'Sign up'}
                    </button>
                </form>
                <Link to="/login" className="link">
                    <p>Login</p>
                </Link>
            </div>
        </div>
    );
}

export default Register;
