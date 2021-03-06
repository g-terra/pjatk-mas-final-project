import { Add, InfoOutlined } from "@mui/icons-material";
import { Box, Button, Chip, Collapse, IconButton, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Typography } from "@mui/material";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import React from "react";
import Link from "next/link";
import DeviceVersionDetailViewer from "./versions/DeviceVersionDetailViewer";
import DeleteVersionDialog from "./versions/DeleteVersionDialog";
import DeleteDeviceTypeDialog from "./DeleteDeviceTypeDialog";
import DeviceDetailViewer from "./DeviceDetailViewer";

export function DevicesTableRow(props) {
    const [open, setOpen] = React.useState(false);


    return (
        <React.Fragment>
            {getMainRow()}
            {getSubRows()}
        </React.Fragment>
    );

    function getMainRow() {
        return <TableRow key={props.data.deviceTypeId} sx={{ '& > *': { borderBottom: 'unset' } }}>
            <TableCell>
               <Tooltip title="Click to see all versions of this device">
               <IconButton
                    aria-label="expand row"
                    size="small"
                    onClick={() => setOpen(!open)}>
                    {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                </IconButton>
                </Tooltip>
            </TableCell>
            <TableCell sx align="center">{props.data.deviceTypeId}</TableCell>
            <TableCell align="center">{props.data.deviceTypeName}</TableCell>
            <TableCell align="center">{props.data.powerConsumption}</TableCell>
            <TableCell align="center" padding="checkbox">{getStatusChip(props.data.deviceTypeStatus)}</TableCell>
            <TableCell align="center" padding="checkbox"><DeviceDetailViewer data={props.data}></DeviceDetailViewer></TableCell>
            <TableCell align="center" padding="checkbox">{getCreateVersionButton()}</TableCell>
            <TableCell padding="checkbox">{getDeprecateDeviceButton()}</TableCell>
        </TableRow>;
    }

    function getDeprecateDeviceButton() {
        return props.data.deviceTypeStatus !== "DEPRECATED" ?
            <DeleteDeviceTypeDialog
                deviceId={props.data.deviceTypeId}
                onRefeshRequired={props.onRefeshRequired} />
            : null;
    }

    function getCreateVersionButton() {
        return props.data.deviceTypeStatus !== "DEPRECATED" ?
            <Link href={`/device-type/versions/new?id=${props.data.deviceTypeId}`}>
                <Tooltip title="Click to create new version for this device">
                    <IconButton size="small">
                        <Add></Add>
                    </IconButton>
                </Tooltip>
            </Link>
            : null;
    }

    function getSubRows() {
        return <TableRow key={props.data.deviceTypeId + "-SUB"} bgcolor='#f2f7fa'>
            <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <Box sx={{ margin: 1 }}>
                        <Typography variant="h6" gutterBottom component="div">
                            versions
                        </Typography>
                        <Table size="small" aria-label="purchases">
                            <TableHead>
                                <TableRow>
                                    <TableCell>version</TableCell>
                                    <TableCell>status</TableCell>
                                    <TableCell>created at</TableCell>
                                    <TableCell padding="checkbox"></TableCell>
                                    <TableCell padding="checkbox"></TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {props.data.versions.map((version, index) => (
                                    <TableRow key={index}>
                                        <TableCell>{version.versionNumber}</TableCell>
                                        <TableCell>
                                            {getStatusChip(version.status)}
                                        </TableCell>
                                        <TableCell>{version.createDateTime}</TableCell>
                                        <TableCell padding="checkbox">
                                            <DeviceVersionDetailViewer data={version} />
                                        </TableCell>
                                        <TableCell padding="checkbox">
                                            {version.status === "AVAILABLE" ?
                                                <DeleteVersionDialog
                                                    version={version}
                                                    onRefeshRequired={props.onRefeshRequired} />
                                                : null}
                                        </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </Box>
                </Collapse>
            </TableCell>
        </TableRow>;
    }
}

function getStatusChip(status) {

    if (status === "AVAILABLE" || status === "VERSIONED") {
        return (
            <Chip
                label={status}
                color="primary"
            />
        );
    } else if (status === "DEPRECATED") {
        return (
            <Chip
                label={status}
                color="warning"
            />
        );
    }
    return (
        <Chip
            label={status}
            color="default"
        />
    );
}




