import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Box, Chip, IconButton, Tooltip, Typography } from '@mui/material';
import { Close, InfoOutlined } from '@mui/icons-material';

export default function DeviceDetailViewer(props) {
    const [open, setOpen] = React.useState(false);
    const [fullWidth, setFullWidth] = React.useState(true);
    const [data, setData] = React.useState(props.data);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <React.Fragment>
            <Tooltip title={"See more details about the device"} >
                <IconButton onClick={handleClickOpen}>
                    <InfoOutlined />
                </IconButton>
            </Tooltip>
            <Dialog
                fullWidth={fullWidth}
                maxWidth='sm'
                open={open}
                onClose={handleClose}>
              
                {createArea("Teams seeling the device", data.teams, "Currently there are no teams are selling this device")}
                {createArea("Employees seeling the device", data.employees, "Currently there are no employees are selling this device")}
                <DialogActions>
                    <Button variant='' onClick={handleClose}>
                        Close
                    </Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );


    function createArea(title, data, message) {
        return <Box>
            <DialogTitle style={{ color: '#1976d2' }}>{title}</DialogTitle>
            <DialogContent dividers>
                {createChipList(data, message)}
            </DialogContent>
        </Box>;
    }

    function createChipList(data, message) {
        return data.length > 0 ?
            data.map((entry, index) => {
                return createChip(index, entry);
            })
            : <Typography variant="body1">{message}</Typography>;
    }

    function createChip(index, text) {
        return <Chip
            key={index}
            sx={{ margin: .5 }}
            label={text}
            color="success"/>;
    }

}
