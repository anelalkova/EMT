import {useNavigate} from "react-router";
import {useState} from "react";
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import EditUserDialog from "../EditUserDialog/EditUserDialog.jsx";
import DeleteUserDialog from "../DeleteUserDialog/DeleteUserDialog.jsx";

const UserCard = ({ user, onEdit, onDelete }) => {
    const navigate = useNavigate();
    const [editUserDialogOpen, setEditUserDialogOpen] = useState(false);
    const [deleteUserDialogOpen, setDeleteUserDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{ boxShadow: 3, borderRadius: 2, p: 1 }}>
                <CardContent>
                    <Typography variant="h5">{user.username}</Typography>
                    <Typography variant="subtitle2" sx={{ mt: 1, mb: 1 }}>
                        {user.country?.name || "No country available."}
                    </Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Button
                        size="small"
                        color="info"
                        onClick={() => navigate(`/users/${user.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            onClick={() => setEditUserDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            onClick={() => setDeleteUserDialogOpen(true)}>
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditUserDialog
                open={editUserDialogOpen}
                onClose={() => setEditUserDialogOpen(false)}
                user={user}
                onEdit={onEdit}
            />
            <DeleteUserDialog
                open={deleteUserDialogOpen}
                onClose={() => setDeleteUserDialogOpen(false)}
                user={user}
                onDelete={onDelete}
            />
        </>
    );
};

export default UserCard;