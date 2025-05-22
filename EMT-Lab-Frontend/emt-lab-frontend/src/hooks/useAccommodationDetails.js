import {useEffect, useState} from "react";
import accommodationRepository from "../repository/accommodationRepository.js";
import userRepository from "../repository/userRepository.js";

const useAccommodationDetails = (id) => {
    const [state, setState] = useState({
        "accommodation": null,
        "category": null,
        "hostUsername": null,
    });

    useEffect(() => {
        accommodationRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "accommodation": response.data}));
                userRepository
                    .findById(response.data.username)
                    .then((response) => {
                        setState(prevState => ({...prevState, "hostUsername": response.data}));
                    })
                    .catch((error) => console.log(error));
            /*    manufacturerRepository
                    .findById(response.data.manufacturerId)
                    .then((response) => {
                        setState(prevState => ({...prevState, "manufacturer": response.data}));
                    })
                    .catch((error) => console.log(error));*/
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default useAccommodationDetails;