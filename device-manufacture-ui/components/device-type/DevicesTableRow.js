import { Add, InfoOutlined } from "@mui/icons-material";
import { Box, Button, Chip, Collapse, IconButton, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Typography } from "@mui/material";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import React from "react";
import Link from "next/link";
import DeviceDetailViewer from "./DeviceDetailViewer";
import DeleteVersionDialog from "./versions/DeleteVersionDialog";

export function DevicesTableRow(props) {
    const [open, setOpen] = React.useState(false);


    return (
        <React.Fragment>
            <TableRow key={props.data.deviceTypeId} sx={{ '& > *': { borderBottom: 'unset' } }}>
                <TableCell>
                    <IconButton
                        aria-label="expand row"
                        size="small"
                        onClick={() => setOpen(!open)}
                    >
                        {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
                    </IconButton>
                </TableCell>
                <TableCell width={200} align="center">{props.data.deviceTypeId}</TableCell>
                <TableCell width={200} align="center">{props.data.deviceTypeName}</TableCell>
                <TableCell width={200} align="center">{props.data.powerConsumption}</TableCell>
                <TableCell width={200} align="center">
                    <Chip
                        label={props.data.deviceTypeStatus}
                        color={props.data.deviceTypeStatus === "VERSIONED" ? "primary" : "secondary"}
                    />
                </TableCell>
                <TableCell width={200} align="center">
                    <Link href={`/device-type/versions/new?id=${props.data.deviceTypeId}`}>
                        <Tooltip title="Create new version">
                            <Button>
                                <Add></Add>
                            </Button>
                        </Tooltip>
                    </Link>
                </TableCell>
            </TableRow>
            <TableRow key={props.data.deviceTypeId + "-SUB"} bgcolor='#f2f7fa' >
                <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
                    <Collapse in={open} timeout="auto" unmountOnExit>
                        <Box sx={{ margin: 1 }}>
                            <Typography variant="h6" gutterBottom component="div">
                                versions
                            </Typography>
                            <Table size="small" aria-label="purchases">
                                <TableHead>
                                    <TableRow>
                                        <TableCell >version</TableCell>
                                        <TableCell >status</TableCell>
                                        <TableCell >created at</TableCell>
                                        <TableCell padding="checkbox"></TableCell>
                                        <TableCell padding="checkbox"></TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {props.data.versions.map((version, index) => (
                                        <TableRow key={index}>
                                            <TableCell >{version.versionNumber}</TableCell>
                                            <TableCell>
                                                {getDeviceVersionStatusChip(version)}
                                            </TableCell>
                                            <TableCell>{version.createDateTime}</TableCell>
                                            <TableCell padding="checkbox">
                                                <DeviceDetailViewer data={version} />
                                            </TableCell>
                                            <TableCell padding="checkbox">
                                                {
                                                    version.status === "AVAILABLE" ?
                                                        <DeleteVersionDialog
                                                            version={version}
                                                            onRefeshRequired={props.onRefeshRequired} />
                                                        : null
                                                }
                                            </TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
        </React.Fragment>
    );
}

function getDeviceVersionStatusChip(version) {

    if (version.status === "AVAILABLE") {
        return (
            <Chip
                label={version.status}
                color="primary"
            />
        );
    } else if (version.status === "DEPRECATED") {
        return (
            <Chip
                label={version.status}
                color="warning"
            />
        );
    }
    return (
        <Chip
            label={version.status}
            color="default"
        />
    );
}




