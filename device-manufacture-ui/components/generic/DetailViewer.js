import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { Box, Chip, Typography } from '@mui/material';
import { InfoOutlined } from '@mui/icons-material';

export default function DetailViewer(props) {
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
                {
                    props.definition.areas.map((area, index) => {
                        return (
                            <Box key={index}>
                                <DialogTitle>{area.title}</DialogTitle>
                                <DialogContent dividers>
                                    {area.wrapping(props.data)}
                                </DialogContent>
                            </Box>
                        )
                    })
                }
                <DialogActions>
                    <Button onClick={handleClose}>Close</Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );
}
