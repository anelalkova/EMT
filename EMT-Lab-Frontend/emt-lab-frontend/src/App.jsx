import './App.css'
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import {BrowserRouter,Routes, Route} from "react-router";
import React from 'react';
import AccommodationsPage from "./ui/pages/AccomodationsPage/AccommodationsPage.jsx";
import ProtectedRoute from "./ui/components/routing/ProtectedRoute/ProtectedRoute.jsx";
import Register from "./ui/components/auth/Register/Register.jsx";
import Login from "./ui/components/auth/Login/Login.jsx";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import CountriesPage from "./ui/pages/CountriesPage/CountriesPage.jsx";
import AccommodationDetails from "./ui/components/accommodations/AccommodationDetails/AccommodationDetails.jsx";
import UsersPage from "./ui/pages/UsersPage/UsersPage.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
                <Route path="/" element={<Layout/>}>
                    <Route path="/" element={<HomePage />} />
                    <Route element={<ProtectedRoute />}>
                        <Route path="/accommodations" element={<AccommodationsPage />} />
                        <Route path="/countries" element={<CountriesPage />} />
                        <Route path="/users" element={<UsersPage />} />
                        <Route path="accommodations/:id" element={<AccommodationDetails/>}/>
                    </Route>
                </Route>
            </Routes>
        </BrowserRouter>
    );
};
export default App;
