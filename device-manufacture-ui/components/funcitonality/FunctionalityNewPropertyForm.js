import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { MenuItem, Stack, TextField } from '@mui/material';

const types =
    [
        {
            value: 'YES_NO',
            label: 'Yes/No'
        },
        {
            value: 'NUMBER',
            label: 'Number',
        },
        {
            value: 'TEXT',
            label: 'Text',
        }

    ]


export default function FunctionalityNewPropertyForm(props) {

    const [open, setOpen] = React.useState(false);

    const [name, setName] = React.useState("");
    const [type, setType] = React.useState("");

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setName("");
        setOpen(false);
    };

    const handleNameChange = (event) => {
        setName(event.target.value);
    };

    const handleTypeChange = (event) => {
        setType(event.target.value);
    }


    const handleSave = () => {
        props.handleAddProperty({
            name: name,
            type: type
        });
        handleClose()
    }

    return (
        <>
            <Button variant="contained" color="primary" onClick={handleClickOpen}>
                Add property
            </Button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Add property</DialogTitle>
                <DialogContent>
                    <Stack spacing={2}>
                        <TextField
                            key={1}
                            autoFocus
                            margin="dense"
                            id="name"
                            label="property name"
                            type="text"
                            value={name}
                            onChange={handleNameChange}
                            fullWidth
                            variant="standard"
                        />
                        <TextField
                            key={2}
                            id="outlined-select-type"
                            select
                            label="Select"
                            value={type}
                            onChange={handleTypeChange}
                            helperText="Please select the type of the property"
                        >{
                                types.map((option) => (
                                    <MenuItem key={option.value} value={option.value}>
                                        {option.label}
                                    </MenuItem>
                                ))
                            }
                        </TextField>
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
