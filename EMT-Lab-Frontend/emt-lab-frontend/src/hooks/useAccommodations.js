import {useCallback, useEffect, useState} from "react";
import accommodationRepository from "../repository/accommodationRepository.js";

const initialState = {
    "accommodations": [],
    "loading": true,
};

const useAccommodations = () => {
    const [state, setState] = useState(initialState);

    const fetchAccommodations = useCallback(() => {
        setState(initialState);
        accommodationRepository
            .findAll()
            .then((response) => {
                setState({
                    "accommodations": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        accommodationRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new accommodation.");
                fetchAccommodations();
            })
            .catch((error) => console.log(error));
    }, [fetchAccommodations]);

    const onEdit = useCallback((id, data) => {
        accommodationRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited the accommodation with ID ${id}.`);
                fetchAccommodations();
            })
            .catch((error) => console.log(error));
    }, [fetchAccommodations]);

    const onDelete = useCallback((id) => {
        accommodationRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the accommodation with ID ${id}.`);
                fetchAccommodations();
            })
            .catch((error) => console.log(error));
    }, [fetchAccommodations]);

    useEffect(() => {
        fetchAccommodations();
    }, [fetchAccommodations]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
};

export default useAccommodations;