import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Box, Chip, Tooltip, Typography } from '@mui/material';
import { InfoOutlined } from '@mui/icons-material';

export default function DeviceVersionDetailViewer(props) {
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
                <Button onClick={handleClickOpen}>
                    <InfoOutlined />
                </Button>
            </Tooltip>
            <Dialog
                fullWidth={fullWidth}
                maxWidth='xs'
                open={open}
                onClose={handleClose}
            >
                {createFunctionalitiesArea()}
                {createPropertiesArea()}
                <DialogActions>
                    <Button onClick={handleClose}>Close</Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );

    function createPropertiesArea() {
        return <Box>
            <DialogTitle>Properties</DialogTitle>
            <DialogContent dividers>
                {Object.entries(data.propertyValues).map(([key, value], index) => {
                    return (
                        <Typography key={index} variant="body1" gutterBottom>
                            {key}: {value}
                        </Typography>

                    );
                })}
            </DialogContent>
        </Box>;
    }

    function createFunctionalitiesArea() {
        return <Box>
            <DialogTitle>Functionalities</DialogTitle>
            <DialogContent dividers>
                {data.functionalities.map((functionality, index) => {
                    return (
                        <Chip
                            key={index}
                            sx={{ margin: .5 }}
                            label={functionality}
                            color="success" />
                    );
                })}
            </DialogContent>
        </Box>;
    }
}
