import {useCallback, useEffect, useState} from "react";
import accommodationRepository from "../repository/accommodationRepository.js";
import userRepository from "../repository/userRepository.js";

const initialState = {
    "users": [],
    "loading": true,
};

const useUsers = () => {
    const [state, setState] = useState(initialState);

    const fetchUsers = useCallback(() => {
        setState(initialState);
        userRepository
            .findAll()
            .then((response) => {
                setState({
                    "users": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onEdit = useCallback((id, data) => {
        userRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited the user with ID ${id}.`);
                fetchUsers();
            })
            .catch((error) => console.log(error));
    }, [fetchUsers]);

    const onDelete = useCallback((id) => {
        userRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the user with ID ${id}.`);
                fetchUsers();
            })
            .catch((error) => console.log(error));
    }, [fetchUsers]);

    useEffect(() => {
        fetchUsers();
    }, [fetchUsers]);

    return {...state, onEdit: onEdit, onDelete: onDelete};
};

export default useUsers;