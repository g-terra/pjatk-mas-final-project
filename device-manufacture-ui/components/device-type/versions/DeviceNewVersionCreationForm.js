import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import axios from 'axios';
import { Box } from '@mui/system';
import { Alert, Collapse, MenuItem, Paper, Stack, Switch, TextField } from '@mui/material';
import { useRouter } from 'next/router'
import { Typography } from '@material-ui/core';

export default function DeviceNewVersionCreationForm(props) {
    const [deviceId, setDeviceId] = React.useState(-1);
    const [open, setOpen] = React.useState(false);
    const [showError, setShowError] = React.useState(false);
    const [errors, setErrors] = React.useState([]);
    const router = useRouter()
    const [formState, setFormState] = React.useState([]);

   

    const cleanErrors = () => {
        setErrors([]);
        setShowError(false);
    }

    const handleClickOpen = () => {
        setOpen(true);
        resetState()
        setDeviceId(props.deviceId)
        getRequiredFunctionalityInformation()
    };

    const resetState = () => {
        setFormState([]);
    }

    const handleClose = () => {
        resetState()
        cleanErrors()
        setOpen(false);
    };


    const handleStateFormCreation = (props) => {
        const formState = props.map(element => {
            return {
                "groupId": element.functionalityId.toString(),
                "groupName": element.functionalityName,
                "fields": element.properties.map(property => {
                    return {
                        "name": property.name,
                        "type": property.type,
                        "value": getDefaultValuesForField(property.type)
                    }
                })

            }
        })

        setFormState(formState)
    }

    const queryFormValue = (groupId, fieldName) => {
        const group = formState.find(element => element.groupId === groupId);
        const field = group.fields.find(element => element.name === fieldName);
        return field.value;
    }

    const handleFieldValueChange = (groupId, fieldName, value) => {

        const newFormState = [...formState];
        const group = newFormState.find(element => element.groupId === groupId);
        const field = group.fields.find(element => element.name === fieldName);
        field.value = value;

        setFormState(newFormState)
    }


    const handleSave = () => {
        createNewVersionRequest(router)
    }


    return (
        <>
            <Button sx={props.sx} variant="contained" color="primary" onClick={handleClickOpen} disabled={props.functionalites.length <= 0} >
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
                <DialogTitle color="primary.main">Property values</DialogTitle>
                <DialogContent>
                    <Stack margin={2} spacing={1}>
                        {formState.map((group, index) => {
                            return createFunctionalityGroup(group, index)
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


    function createFunctionalityGroup(group, index) {
        return (
            <Box key={index} component={Paper} p={2} >
                <Stack spacing={2}>
                    <Typography variant="h6" >{group.groupName}</Typography>
                    {group.fields.map((field, index) => {
                        return createField(group.groupId, field, index)
                    }
                    )}
                </Stack>
            </Box>
        );
    }


    function createField(groupId, field, index) {

        if (field.type === "TEXT") {
            return getTextField(groupId, field, index)
        } else if (field.type === "NUMBER") {
            return getNumberField(groupId, field, index)
        } else if (field.type === "YES_NO") {
            return getYesOrNoField(groupId, field, index)
        } else {
            console.log("Unknown field type: " + field.type)
        }

    }

    function getDefaultValuesForField(type) {
        if (type === "TEXT") {
            return ""
        } else if (type === "NUMBER") {
            return 0
        } else if (type === "YES_NO") {
            return 'yes'
        } else {
            console.log("Unknown field type: " + type)
        }
    }


    function getYesOrNoField(groupId, field, index) {
        return <Box key={index}>
            <TextField
                id={groupId}
                label={field.name}
                value={queryFormValue(groupId, field.name)}
                onChange={(event) => {
                    handleFieldValueChange(groupId, field.name, event.target.value)
                }}
                select
                fullWidth
            >{[{
                value: 'yes',
                label: 'Yes'
            }, {
                value: 'no',
                label: 'No'
            }].map((option) => (
                <MenuItem key={option.value} value={option.value}>
                    {option.label}
                </MenuItem>
            ))}
            </TextField>
        </Box>;
    }

    function getNumberField(groupId, field, index) {
        return <Box key={index}>
            <TextField
                id={groupId}
                label={field.name}
                value={queryFormValue(groupId, field.name)}
                onChange={(event) => {
                    handleFieldValueChange(groupId, field.name, event.target.value)
                }}
                type="number"
                margin="normal"
                variant="outlined"
                fullWidth />
        </Box>;
    }

    function getTextField(groupId, field, index) {
        return <Box key={index}>
            <TextField
                id={groupId}
                label={field.name}
                value={queryFormValue(groupId, field.name)}
                onChange={(event) => {
                    handleFieldValueChange(groupId, field.name, event.target.value)
                }}
                margin="normal"
                variant="outlined"
                fullWidth />
        </Box>;
    }

    function getRequiredFunctionalityInformation() {

        const request = {
            functionalityIds: [...props.functionalites]
        }

        axios.post(process.env.deviceManufactureApi + '/functionality/required-properties', request).then((response) => {
            console.log("required properties", response.data.requiredProperties);

            handleStateFormCreation(response.data.requiredProperties);

        }
        ).catch(
            (error) => {
                console.log("error", error);
            }
        )
    }



    function createNewVersionRequest(router) {

        console.log("form state", formState);

        const values = [].concat.apply([], formState.map(group => {
            return group.fields.map(field => {
                return {
                    parentFunctionalityId: group.groupId,
                    name: field.name,
                    value: field.value
                }
            })
        }));
       

        const request = {
            deviceId: deviceId,
            functionalityIds: [...props.functionalites],
            propertyValues: values
        }

        console.log("request", request);

        axios.post(process.env.deviceManufactureApi + '/device-type-version', request).then((response) => {
            handleClose()
            router.push('/device-type/search')

        }).catch(
            (error) => {
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
