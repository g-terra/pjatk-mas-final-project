import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Alert, Box, Collapse } from '@mui/material';
import Link from 'next/link';

export default function FormDialog(props) {
  const [open, setOpen] = React.useState(false);
  const [data, setData] = React.useState(props.definition.initialState);
  const [showError, setShowError] = React.useState(false);
  const [errors, setErrors] = React.useState([]);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSave = () => {
    props.definition.onSave(data)
    .then(
      (response) => {
        console.log("response", response);
        props.definition.onSaveSucess(response.data);
      }
    )
    .catch(
      (error) => {
        console.log("error", error);
        if (error.response.data.type ===  "validation-error") {
          setErrors(error.response.data.fieldErrors.map((fieldError) => { return fieldError.message}));
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
      <Button sx={props.sx} variant="contained" color="primary" onClick={handleClickOpen} startIcon={props.definition.buttonIcon}>
        {props.definition.buttonText}

      </Button>
      <Dialog open={open} onClose={handleClose}>
        <Collapse in={showError} >
          {
            errors.map((message) => {
              return (
                <Alert severity="error">{message}</Alert>
              )
            })
          }
        </Collapse>
        <DialogTitle>{props.definition.title}</DialogTitle>
        <DialogContent>
          {
            props.definition.fields.map((field, index) => {
              return field.component(data, handleChange, index);
            })
          }
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleSave}>Save</Button>
        </DialogActions>
      </Dialog>
    </>


  );
}