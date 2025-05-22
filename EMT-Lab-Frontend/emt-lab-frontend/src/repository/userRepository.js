import axiosInstance from "../axios/axiosInstance.js";

const userRepository = {
    findAll: async () => {
        return await axiosInstance.get("/auth/users");
    },
    register: async (data) => {
        return await axiosInstance.post("/auth/register", data);
    },
    login: async (data) => {
        return await axiosInstance.post("/auth/login", data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/auth/update/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/auth/delete/${id}`);
    },
};

export default userRepository;