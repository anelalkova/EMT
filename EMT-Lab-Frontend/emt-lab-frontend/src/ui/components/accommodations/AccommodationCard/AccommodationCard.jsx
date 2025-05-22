import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import {useState} from "react";
import {useNavigate} from "react-router";
import EditAccommodationDialog from "../EditAccommodationDialog/EditAccommodationDialog.jsx";
import DeleteAccommodationDialog from "../DeleteAccommodationDialog/DeleteAccommodationDialog.jsx";
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

const AccommodationCard = ({ accommodation, onEdit, onDelete }) => {
    const navigate = useNavigate();
    const [editAccommodationDialogOpen, setEditAccommodationDialogOpen] = useState(false);
    const [deleteAccommodationDialogOpen, setDeleteAccommodationDialogOpen] = useState(false);

    return (
        <>
        <Card sx={{ boxShadow: 3, borderRadius: 2, p: 1 }}>
            <CardContent>
                <Typography variant="h5">{accommodation.name}</Typography>

                <Typography variant="subtitle2" sx={{ mt: 1, mb: 1 }}>
                    {accommodation.host.username || "No host details available."}
                </Typography>

                <Typography variant="body1" fontWeight="bold" sx={{ textAlign: "right", fontSize: "1.25rem" }}>
                    {accommodation.category}
                </Typography>

                <Typography variant="body2" sx={{ textAlign: "right" }}>
                    {accommodation.isAvailable
                        ? `${accommodation.numRooms} available`
                        : 'Accommodation not available.'}
                </Typography>
            </CardContent>
            <CardActions sx={{justifyContent: "space-between"}}>
                <Button
                    size="small"
                    color="info"
                    startIcon={<InfoIcon/>}
                    onClick={() => navigate(`/accommodations/${accommodation.id}`)}
                >
                    Info
                </Button>
                <Box>
                    <Button
                        size="small"
                        color="warning"
                        startIcon={<EditIcon/>}
                        sx={{mr: "0.25rem"}}
                        onClick={() => setEditAccommodationDialogOpen(true)}
                    >
                        Edit
                    </Button>
                    <Button
                        size="small"
                        color="error"
                        startIcon={<DeleteIcon/>}
                        onClick={() => setDeleteAccommodationDialogOpen(true)}
                    >
                        Delete
                    </Button>
                </Box>
            </CardActions>
        </Card>
            <EditAccommodationDialog
                open={editAccommodationDialogOpen}
                onClose={() => setEditAccommodationDialogOpen(false)}
                accommodation={accommodation}
                onEdit={onEdit}
            />
            <DeleteAccommodationDialog
                open={deleteAccommodationDialogOpen}
                onClose={() => setDeleteAccommodationDialogOpen(false)}
                accommodation={accommodation}
                onDelete={onDelete}
            />
        </>
    );
};

export default AccommodationCard;
