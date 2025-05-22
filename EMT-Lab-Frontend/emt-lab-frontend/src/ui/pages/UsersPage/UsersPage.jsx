import {Box, CircularProgress} from "@mui/material";
import UsersGrid from "../../components/users/UsersGrid/UsersGrid.jsx";
import useUsers from "../../../hooks/useUsers.js";

const UsersPage = () => {
    const {users, loading, onEdit, onDelete} = useUsers();

    return (
        <>
            <Box className="users-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <UsersGrid users={users} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
        </>
    );
};

export default UsersPage;