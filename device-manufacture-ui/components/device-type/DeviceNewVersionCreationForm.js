import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import axios from 'axios';
import { Box } from '@mui/system';
import { Alert, Collapse, MenuItem, Stack, TextField } from '@mui/material';
import { useRouter } from 'next/router'


const initialFormSetup = {
    functionalityIds: [],
    requiredProperties: []
}

export default function DeviceNewVersionCreationForm(props) {
    const [deviceId, setDeviceId] = React.useState(-1);
    const [open, setOpen] = React.useState(false);
    const [formSetup, setFormSetup] = React.useState(initialFormSetup);
    const [formFieldState, setFormFieldState] = React.useState({});
    const [showError, setShowError] = React.useState(false);
    const [errors, setErrors] = React.useState([]);


    const resetState = () => {
        setFormSetup(initialFormSetup);
    }

    const router = useRouter()

    const cleanErrors = () => {
        setErrors([]);
        setShowError(false);
    }


    const handleClickOpen = () => {
        setOpen(true);
        setDeviceId(props.deviceId)
        getrequiredProperties()
    };

    const handleClose = () => {
        resetState()
        cleanErrors()
        setOpen(false);
    };

    const handleSave = () => {
        console.log("formFieldState", formFieldState);
        createNewVersionRequest(router)
    }

    const handleChange = (event) => {
        console.log(event.target, event.target.value)
        setFormFieldState({ ...formFieldState, [event.target.id]: event.target.value });
    }

    return (
        <>
            <Button sx={props.sx} variant="contained" color="primary" onClick={handleClickOpen} disabled={props.properties.length <= 0} >
                Next
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
                <DialogTitle>Property values</DialogTitle>
                <DialogContent>
                    <Stack margin={2} spacing={2}>
                        {formSetup.requiredProperties.map((property, index) => {
                            return getField(index, property)
                        }
                        )}
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSave}>Create</Button>
                </DialogActions>
            </Dialog>
        </>
    );

    function getField(index, property) {

        if (property.type === 'NUMBER') {
            return (
                <Box key={index}>
                    <TextField
                        id={property.name}
                        label={property.name}
                        value={formFieldState[property.name]}
                        onChange={handleChange}
                        type="number"
                        margin="normal"
                        variant="outlined"
                        fullWidth
                    />
                </Box>
            )
        } else if (property.type === 'YES_NO') {
            return (
                <Box key={index}>
                    <TextField
                        id={property.name}
                        label={property.name}
                        value={formFieldState[property.name]}
                        onChange={(event) => {
                            setFormFieldState({ ...formFieldState, [property.name]: event.target.value })
                        }}
                        select
                        fullWidth
                    >{
                            [{
                                value: 'yes',
                                label: 'Yes'
                            }, {
                                value: 'no',
                                label: 'No'
                            }].map((option) => (
                                <MenuItem key={option.value} value={option.value}>
                                    {option.label}
                                </MenuItem>
                            ))
                        }
                    </TextField>
                </Box >
            )
        } else {
            throw new Error("Unknown property type")
        }

    }

    function getInitialFormFieldsState(properties) {
        const initialFieldState = {};
        properties.forEach(property => {
            initialFieldState[property.name] = "";
        });
        return initialFieldState;
    }

    function getrequiredProperties() {

        const request = {
            functionalityIds: [...props.properties]
        }

        console.log(request)

        axios.post(process.env.deviceManufactureApi + '/functionality/required-properties', request).then((response) => {
            console.log("response", response);
            setFormSetup(response.data);
            setFormFieldState(getInitialFormFieldsState(response.data.requiredProperties))
        }
        ).catch(
            (error) => {
                console.log("error", error);
            }
        )
    }

    function createNewVersionRequest(router) {

        const propertyValues = [];
        Object.entries(formFieldState).forEach(([key, value]) => {
            propertyValues.push({
                name: key,
                value: value
            })
        })


        const request = {
            deviceId: deviceId,
            functionalityIds: [...formSetup.functionalityIds],
            propertyValues: [...propertyValues]
        }

        console.log(request)

        axios.post(process.env.deviceManufactureApi + '/device-type-version', request).then((response) => {
            console.log("response", response);
            handleClose()
            router.push('/device-type/search')

        }).catch(
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
}
