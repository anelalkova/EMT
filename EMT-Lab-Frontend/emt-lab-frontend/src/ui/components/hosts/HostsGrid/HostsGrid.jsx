import {Grid} from "@mui/material";
import HostCard from "../HostCard/HostCard.jsx";

const HostsGrid = ({ hosts }) => {
    return (
        <Grid container spacing={3}>
            {hosts.map((host) => (
                <Grid item key={host.id} xs={12} sm={6} md={4} lg={3}>
                    <HostCard host={host} />
                </Grid>
            ))}
        </Grid>
    );
};

export default HostsGrid;