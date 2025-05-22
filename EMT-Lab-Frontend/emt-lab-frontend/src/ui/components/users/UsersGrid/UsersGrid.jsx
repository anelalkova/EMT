import {Grid} from "@mui/material";
import UserCard from "../UserCard/UserCard.jsx";

const UsersGrid = ({ users,onEdit, onDelete }) => {
    return (
        <Grid container spacing={3}>
            {users.map((user) => (
                <Grid item key={user.id} xs={12} sm={6} md={4} lg={3}>
                    <UserCard user={user} onEdit={onEdit} onDelete={onDelete} />
                </Grid>
            ))}
        </Grid>
    );
};

export default UsersGrid;
