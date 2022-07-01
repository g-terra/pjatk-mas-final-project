import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Alert, Box, Collapse, TextField } from '@mui/material';
import Link from 'next/link';
import axios from "axios"
import { useRouter } from 'next/router'
import { Add } from '@mui/icons-material';

const initialState = {
    name: "",
    powerConsumption: 0
}

export default function DeviceCreationForm(props) {
    const [open, setOpen] = React.useState(false);
    const [data, setData] = React.useState(initialState);
    const [showError, setShowError] = React.useState(false);
    const [errors, setErrors] = React.useState([]);

    const resetState = () => {
        setData(initialState);
    }

    const cleanErrors = () => {
        setErrors([]);
        setShowError(false);
    }

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        resetState()
        cleanErrors()
        setOpen(false);
    };

    const router = useRouter()

    const handleSave = () => {
        axios.post(process.env.deviceManufactureApi + '/device-type', data)
            .then(
                (response) => {
                    console.log("response", response);
                    router.push({
                        pathname: '/device-type/versions/new',
                        query: { id: response.data.id }
                    })
                }
            )
            .catch(
                (error) => {
                    console.log("error", error);
                    if (error.response.data.type && error.response.data.type === "validation-error") {
                        setErrors(error.response.data.fieldErrors.map((fieldError) => { return fieldError.message }));
                    } else {
                        setErrors([error.response.data.message]);
                    }

                    setShowError(true);
                }
            );
    }

    const handleChange = (event) => {
        setData({ ...data, [event.target.id]: event.target.value });
    }

    return (
        <>
            <Button sx={props.sx} startIcon={<Add />} variant="contained" color="primary" onClick={handleClickOpen} >
                Create New Device
            </Button>
            <Dialog open={open} onClose={handleClose}>
                <Collapse in={showError} >
                    {
                        errors.map((message, index) => {
                            return (
                                <Alert key={index} severity="error">{message}</Alert>
                            )
                        })
                    }
                </Collapse>
                <DialogTitle>Create New Device</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="name"
                        label="Device name"
                        type="text"
                        value={data.name}
                        onChange={handleChange}
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        margin="dense"
                        id="powerConsumption"
                        label="Power Consumption (W)"
                        type="number"
                        value={data.powerConsumption}
                        onChange={handleChange}
                        fullWidth
                        variant="standard"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}