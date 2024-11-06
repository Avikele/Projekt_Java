import { useState } from 'react'
import Login from './pages/login/Login'
import Register from './pages/register/Register'
import Navbar from './components/navbar/Navbar'
import Home from './pages/home/Home'
import Profile from './pages/profile/Profile'
import ProfileDetails from './pages/profiledetails/ProfileDetails'
import FriendProfile from './pages/friendProfile/FriendProfile'
import Bookshlef from './pages/bookshelf/Bookshelf'
import BookshelfBookDetail from './pages/bookshelfBookDetail/BookshelfBookDetail'
import BookDetail from './pages/bookDetail/BookDetail'
import ReadingScreen from './pages/readingScreen/ReadingScreen'
import AddBook from './pages/addBook/AddBook'

import {
  createBrowserRouter,
  Navigate,
  Outlet,
  RouterProvider,
} from "react-router-dom"






function App() {
  const [user, setUser] = useState(() => {
    const savedUser = localStorage.getItem('user');
    return savedUser ? JSON.parse(savedUser) : null;
  });
  const [token, setToken] = useState(() => {
    const savedToken = localStorage.getItem('token');
    return savedToken ? savedToken :  null;
});

  const Layout = () => {
    return (
      <div>
        <Navbar />
        <div>
          <Outlet />
        </div>
      </div>
    )
  }

  const ProtectedRoute = ({ children }) => {
    if (!user) {
      return <Navigate to="/Login" />;
    }

    return children
  }


  const router = createBrowserRouter([
    {
      path: "/",
      element:
        <ProtectedRoute>
          <Layout />
        </ProtectedRoute>,
      children: [
        {
          path: "/",
          element: <Home />
        },
        {
          path: "/profile/:id",
          element: <Profile userID={user && user.UserID} username={user && user.Username}/>,
        },
        {
          path: "/profile/details/:id",
          element: <ProfileDetails userID={user && user.UserID}/> 
        },
        {
          path: "/friend/:id",
          element: <FriendProfile />
        },
        {
          path: "/bookshelf",
          element: <Bookshlef />
        },
        {
          path: "/bookshelf/book/:id",
          element: <BookshelfBookDetail />
        },
        {
          path: "/book/:id",
          element: <BookDetail />
        },
        {
          path: "/AddBook",
          element: <AddBook />
        }
      ]
    },
    {
      path: "/bookshelf/book/:id/reading",
      element:
        <ProtectedRoute>
          <ReadingScreen />
        </ProtectedRoute>
    },
    {
      path: "/login",
      element: <Login />
    },
    {
      path: "/register",
      element: <Register />
    },
  ]);


  return (
    <div>
      <RouterProvider router={router} />
    </div>
  )
}

export default App
