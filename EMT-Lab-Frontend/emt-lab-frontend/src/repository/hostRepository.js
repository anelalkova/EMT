import axiosInstance from "../axios/axiosInstance.js";

const hostRepository = {
    findAll: () => axiosInstance.get("/hosts")
};

export default hostRepository;