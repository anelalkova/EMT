import AuthContext from "../contexts/authContext.js";
import {useContext} from "react";

const useAuth = () => useContext(AuthContext);

export default useAuth;
