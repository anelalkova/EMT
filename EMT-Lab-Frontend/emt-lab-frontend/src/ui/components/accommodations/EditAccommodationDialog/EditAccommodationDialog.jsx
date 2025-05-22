import React, { useState } from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField,
    FormLabel,
    RadioGroup,
    FormControlLabel,
    Radio
} from "@mui/material";
import useHosts from "../../../../hooks/useHosts.js";
import useCountries from "../../../../hooks/useCountries.js";

const EditAccommodationDialog = ({ open, onClose, accommodation, onEdit }) => {
    const [formData, setFormData] = useState({
        name: accommodation.name,
        numRooms: accommodation.numRooms,
        hostId: accommodation.host.id,
        countryId: accommodation.host.country.id,
        category: accommodation.category,
        isAvailable: accommodation.isAvailable,
    });

    const { hosts } = useHosts();
    const { countries } = useCountries();
    const categories = ["ROOM", "HOUSE", "FLAT", "APARTMENT", "HOTEL", "MOTEL"];

    const handleChange = (event) => {
        const { name, value } = event.target;
        const processedValue = name === "isAvailable" ? value === "true" : value;
        setFormData({ ...formData, [name]: processedValue });
    };

    const handleSubmit = () => {
        onEdit(accommodation.id, formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Accommodation</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Number of rooms"
                    name="numRooms"
                    type="number"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host Username</InputLabel>
                    <Select
                        name="hostId"
                        value={formData.hostId}
                        onChange={handleChange}
                        label="Host Username"
                    >
                        {hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>{host.username}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Country</InputLabel>
                    <Select
                        name="countryId"
                        value={formData.countryId}
                        onChange={handleChange}
                        label="Country"
                    >
                        {countries.map((country) => (
                            <MenuItem key={country.id} value={country.id}>{country.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                    >
                        {categories.map((category) => (
                            <MenuItem key={category} value={category}>{category}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl margin="dense">
                    <FormLabel>Select Availability</FormLabel>
                    <RadioGroup
                        row
                        name="isAvailable"
                        value={String(formData.isAvailable)}
                        onChange={handleChange}
                    >
                        <FormControlLabel value="true" control={<Radio />} label="Available" />
                        <FormControlLabel value="false" control={<Radio />} label="Unavailable" />
                    </RadioGroup>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="warning">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditAccommodationDialog;
