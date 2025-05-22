import {Box, CircularProgress} from "@mui/material";
import useHosts from "../../../hooks/useHosts.js";
import HostsGrid from "../../components/hosts/HostsGrid/HostsGrid.jsx";

const HostsPage = () => {
    const { hosts, loading } = useHosts();

    return (
        <Box className="hosts-box" sx={{ p: 2 }}>
            {loading && (
                <Box className="progress-box" sx={{ display: 'flex', justifyContent: 'center' }}>
                    <CircularProgress />
                </Box>
            )}

            {!loading && (
                <HostsGrid hosts={hosts} />
            )}
        </Box>
    );
};

export default HostsPage;