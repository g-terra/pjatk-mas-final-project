import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Box, Chip, IconButton, Tooltip, Typography } from '@mui/material';
import { InfoOutlined } from '@mui/icons-material';

export default function DeviceDetailViewer(props) {
    const [open, setOpen] = React.useState(false);
    const [fullWidth, setFullWidth] = React.useState(true);
    const [maxWidth, setMaxWidth] = React.useState('sm');
    const [data, setData] = React.useState(props.data);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleMaxWidthChange = (event) => {
        setMaxWidth(
            event.target.value,
        );
    };

    const handleFullWidthChange = (event) => {
        setFullWidth(event.target.checked);
    };

    return (
        <React.Fragment>
            <Tooltip title={"see more info"} >

                <IconButton onClick={handleClickOpen}>
                    <InfoOutlined />
                </IconButton>
            </Tooltip>
            <Dialog
                fullWidth={fullWidth}
                maxWidth='xs'
                open={open}
                onClose={handleClose}>
                {createTeamsArea()}
                {createEmplouyeesArea()}
                <DialogActions>
                    <Button onClick={handleClose}>Close</Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );



    function createEmplouyeesArea() {
        return <Box>
            <DialogTitle>Teams selling this device</DialogTitle>
            <DialogContent dividers>
                {data.employees.map((employee, index) => {
                    return (
                        <Chip
                            key={index}
                            sx={{ margin: .5 }}
                            label={employee}
                            color="success" />
                    );
                })}
            </DialogContent>
        </Box>;
    }

    function createTeamsArea() {
        return <Box>
            <DialogTitle>Employees seeling the device</DialogTitle>
            <DialogContent dividers>
                {data.teams.map((team, index) => {
                    return (
                        <Chip
                            key={index}
                            sx={{ margin: .5 }}
                            label={team}
                            color="success" />
                    );
                })}
            </DialogContent>
        </Box>;
    }
}
