import React, { useState, useEffect } from 'react';
import {
    Box, TextField, Button, Typography, Container, Paper,
    InputLabel, Select, MenuItem, FormControl
} from '@mui/material';
import userRepository from "../../../../repository/userRepository.js";
import { useNavigate } from "react-router";
import countryRepository from "../../../../repository/countryRepository.js";

const initialFormData = {
    username: "",
    password: "",
    repeatPassword: "",
    role: "",
    countryId: ""
};

const Register = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState(initialFormData);
    const [countries, setCountries] = useState([]);

    useEffect(() => {
        countryRepository.findAll()
            .then(response => setCountries(response.data))
            .catch(error => console.error("Failed to load countries", error));
    }, []);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = () => {
        if (formData.password !== formData.repeatPassword) {
            alert("Passwords do not match.");
            return;
        }

        const payload = {
            username: formData.username,
            password: formData.password,
            role: formData.role,
            countryId: formData.countryId
        };

        userRepository
            .register(payload)
            .then(() => {
                alert("User registered successfully.");
                setFormData(initialFormData);
                navigate("/login");
            })
            .catch((error) => {
                console.error(error);
                alert("Registration failed.");
            });
    };

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} sx={{ padding: 4, mt: 4 }}>
                <Typography variant="h5" align="center" gutterBottom>Register</Typography>
                <Box>
                    <TextField
                        fullWidth label="Username"
                        name="username"
                        margin="normal"
                        required
                        value={formData.username}
                        onChange={handleChange}
                    />
                    <TextField
                        fullWidth label="Password"
                        name="password"
                        type="password"
                        margin="normal"
                        required
                        value={formData.password}
                        onChange={handleChange}
                    />
                    <TextField
                        fullWidth label="Repeat Password"
                        name="repeatPassword"
                        type="password"
                        margin="normal"
                        required
                        value={formData.repeatPassword}
                        onChange={handleChange}
                    />
                    <FormControl fullWidth margin="normal" required>
                        <InputLabel>Role</InputLabel>
                        <Select
                            name="role"
                            label="Role"
                            value={formData.role}
                            onChange={handleChange}
                        >
                            <MenuItem value="ROLE_USER">User</MenuItem>
                            <MenuItem value="ROLE_ADMIN">Administrator</MenuItem>
                        </Select>
                    </FormControl>
                    <FormControl fullWidth margin="normal" required>
                        <InputLabel>Country</InputLabel>
                        <Select
                            name="countryId"
                            label="Country"
                            value={formData.countryId}
                            onChange={handleChange}
                        >
                            {countries.map(country => (
                                <MenuItem key={country.id} value={country.id}>
                                    {country.name}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <Button fullWidth variant="contained" sx={{ mt: 2 }} onClick={handleSubmit}>
                        Register
                    </Button>
                </Box>
            </Paper>
        </Container>
    );
};

export default Register;
