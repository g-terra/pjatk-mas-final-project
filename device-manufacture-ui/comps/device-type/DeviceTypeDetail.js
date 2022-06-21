import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import FormControl from '@mui/material/FormControl';
import FormControlLabel from '@mui/material/FormControlLabel';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import Switch from '@mui/material/Switch';
import { Chip, Typography } from '@mui/material';
import { InfoOutlined } from '@mui/icons-material';

export default function DeviceTypeDetail(props) {
    const [open, setOpen] = React.useState(false);
    const [fullWidth, setFullWidth] = React.useState(true);
    const [maxWidth, setMaxWidth] = React.useState('sm');

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const handleMaxWidthChange = (event) => {
        setMaxWidth(
            // @ts-expect-error autofill of arbitrary value is not handled.
            event.target.value,
        );
    };

    const handleFullWidthChange = (event) => {
        setFullWidth(event.target.checked);
    };

    return (
        <React.Fragment>
            <Button onClick={handleClickOpen}>
                <InfoOutlined />
            </Button>
            <Dialog
                fullWidth={fullWidth}
                maxWidth='xs'
                open={open}
                onClose={handleClose}
            >
                <DialogTitle> Functionalities</DialogTitle>

                <DialogContent dividers>
                    {
                        props.data.functionalities.map((functionality, index) => {
                            return (
                                <Chip
                                    key={index}
                                    sx={{ margin: .5 }}
                                    label={functionality}
                                    color="success"
                                />
                            )
                        }
                        )
                    }

                </DialogContent>
                <DialogTitle> Properties</DialogTitle>
                <DialogContent dividers>

                    {


                        Object.entries(props.data.propertyValues).map(([key, value] , index) => {
                            return (
                                <Typography key={index} variant="body1" gutterBottom>
                                    {key} : {value}
                                </Typography>

                            )
                        }
                        )
                    }
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Close</Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );
}
