import {Card, CardContent, Typography} from "@mui/material";

const HostCard = ({ host }) => {
    return (
        <Card sx={{ boxShadow: 3, borderRadius: 2, p: 1 }}>
            <CardContent>
                <Typography variant="h5">{host.username}</Typography>
                <Typography variant="subtitle2" sx={{ mt: 1, mb: 1 }}>
                    {host.country.name || "No country available."}
                </Typography>
            </CardContent>
        </Card>
    );
};

export default HostCard;