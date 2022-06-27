import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Stack, Alert, Collapse, TextField } from '@mui/material';
import AddPropertyDialog from './FunctionalityNewPropertyForm';
import { DataGrid } from '@mui/x-data-grid';
import axios from 'axios';

const columns = [
    { field: 'id', headerName: '', width: 1 },
    { field: 'name', headerName: 'Name', width: 150 },
    {
        field: 'type',
        headerName: 'Type',
        width: 150,
    }
];

const initialState = {
    name: "",
    properties: []
}

export default function FunctionalityCreationForm(props) {
    const [open, setOpen] = React.useState(false);
    const [showError, setShowError] = React.useState(false);
    const [errors, setErrors] = React.useState([]);
    const [data, setData] = React.useState(initialState);

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

    const handleSave = () => {
        axios.post(process.env.deviceManufactureApi + '/functionality', data)
            .then((response) => {
                console.log("response", response);
                handleClose()
                props.onFuncitonalityCreatedSuccessfully()
            })
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

    const handleAddProperty = (property) => {
        const newData = { ...data };
        newData.properties = [...newData.properties];

        if (newData.properties.length > 0) {
            newData.properties.push({ name: property.name, type: property.type, id: newData.properties.length + 1 });
        } else {
            newData.properties.push({ name: property.name, type: property.type, id: 1 });
        }

        setData(newData);
    }

    return (
        <>
            <Button sx={props.sx} variant="contained" color="primary" onClick={handleClickOpen} >
                Create new Functionality
            </Button>
            <Dialog open={open} onClose={handleClose} maxWidth={'xs'} fullWidth={true} >
                <Collapse in={showError} >
                    {
                        errors.map((message, index) => {
                            return (
                                <Alert key={index} severity="error">{message}</Alert>
                            )
                        })
                    }
                </Collapse>
                <DialogTitle>Create New Functionality</DialogTitle>
                <DialogContent>
                    <TextField
                        key={data.name}
                        autoFocus
                        margin="dense"
                        id="name"
                        label="Functionality name"
                        type="text"
                        value={data.name}
                        onChange={handleChange}
                        fullWidth
                        variant="standard"
                    />
                    <Stack sx={{ marginTop: 2, height: 400, width: '100%' }}>
                        <AddPropertyDialog handleAddProperty={handleAddProperty} />
                        <DataGrid
                            rows={data.properties}
                            columns={columns}
                            pageSize={5}
                            rowsPerPageOptions={[5]}
                            disableSelectionOnClick
                        />
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Save</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}
