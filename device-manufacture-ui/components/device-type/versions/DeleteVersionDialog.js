import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Stack, Alert, Collapse, Typography, IconButton, Tooltip } from '@mui/material';
import axios from 'axios';
import { Cancel, Delete } from '@mui/icons-material';


export default function DeleteVersionDialog(props) {
    const [open, setOpen] = React.useState(false);
    const [showError, setShowError] = React.useState(false);
    const [errors, setErrors] = React.useState([]);
    const [onRefesh, setOnRefesh] = React.useState(props.onRefresh);


    const cleanErrors = () => {
        setErrors([]);
        setShowError(false);
    }


    const handleClose = () => {
        cleanErrors()
        setOpen(false);
    };



    const handleDeprecate = () => {
        axios.delete(process.env.deviceManufactureApi + '/device-type-version/' + props.version.versionId)
            .then((response) => {
                console.log("response", response);
                props.onRefeshRequired(OldKey => OldKey + 1);
                handleClose()
            })
            .catch(
                (error) => {
                    console.log("error", error);
                    setErrors([error.response.data.message]);
                    setShowError(true);
                }
            );
    }

    return (
        <>
            <Tooltip title="Deprecate version">
                <IconButton sx={props.sx} color="warning"
                    aria-label="expand row"
                    size="small"
                    onClick={() => setOpen(!open)}>
                    <Cancel />
                </IconButton>
            </Tooltip>
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
                <DialogTitle style={{ color: '#ed8c02' }}>You are deprecatint a device type version!</DialogTitle>
                <DialogContent>
                    <Stack sx={{ marginTop: 2, width: '100%' }} spacing={2}>
                        <Typography variant="body1">
                            Are you sure you want to set this version to deprecated?
                        </Typography>
                        <Typography variant="body2">
                            This version will no longer be available for usage however it will still be available in the device type history.
                        </Typography>
                        <Typography variant="body2">
                            Please also be aware that this action is irreversible.
                        </Typography>
                    </Stack>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>No</Button>
                    <Button onClick={handleDeprecate}>Yes</Button>
                </DialogActions>
            </Dialog>
        </>
    );
}
