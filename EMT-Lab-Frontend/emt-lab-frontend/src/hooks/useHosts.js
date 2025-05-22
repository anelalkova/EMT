import {useCallback, useEffect, useState} from "react";
import hostRepository from "../repository/hostRepository.js";
import accommodationRepository from "../repository/accommodationRepository.js";

const initialState = {
    "hosts": [],
    "loading": true,
};

const useHosts = () => {
    const [state, setState] = useState(initialState);

    const fetchHosts = useCallback(() => {
        setState(initialState);
        hostRepository
            .findAll()
            .then((response) => {
                setState({
                    "hosts": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    useEffect(() => {
        fetchHosts();
    }, [fetchHosts]);

    return {...state,};
};

export default useHosts;