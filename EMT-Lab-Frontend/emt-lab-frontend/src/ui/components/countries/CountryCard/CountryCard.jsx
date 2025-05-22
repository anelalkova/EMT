import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import {useNavigate} from "react-router";
import {useState} from "react";
import EditCountryDialog from "../EditCountryDialog/EditCountryDialog.jsx";
import DeleteCountryDialog from "../DeleteCountryDialog/DeleteCountryDialog.jsx";

const CountryCard = ({ country, onEdit, onDelete }) => {
    const navigate = useNavigate();
    const [editCountryDialogOpen, setEditCountryDialogOpen] = useState(false);
    const [deleteCountryDialogOpen, setDeleteCountryDialogOpen] = useState(false);

    return (
        <>
        <Card sx={{ boxShadow: 3, borderRadius: 2, p: 1 }}>
            <CardContent>
                <Typography variant="h5">{country.name}</Typography>
                <Typography variant="subtitle2" sx={{ mt: 1, mb: 1 }}>
                    {country.continent || "No continent available."}
                </Typography>
            </CardContent>
            <CardActions sx={{justifyContent: "space-between"}}>
                <Button
                size="small"
                color="info"
                onClick={() => navigate(`/countries/${country.id}`)}
                >
                    Info
                </Button>
                <Box>
                    <Button
                    size="small"
                    color="warning"
                    onClick={() => setEditCountryDialogOpen(true)}
                    >
                        Edit
                    </Button>
                    <Button
                    size="small"
                    color="error"
                    onClick={() => setDeleteCountryDialogOpen(true)}>
                        Delete
                    </Button>
                </Box>
            </CardActions>
        </Card>
            <EditCountryDialog
                open={editCountryDialogOpen}
                onClose={() => setEditCountryDialogOpen(false)}
                country={country}
                onEdit={onEdit}
            />
            <DeleteCountryDialog
                open={deleteCountryDialogOpen}
                onClose={() => setDeleteCountryDialogOpen(false)}
                country={country}
                onDelete={onDelete}
            />
        </>
    );
};

export default CountryCard;