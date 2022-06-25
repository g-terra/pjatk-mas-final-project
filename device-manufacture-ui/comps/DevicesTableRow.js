import { Add, InfoOutlined } from "@mui/icons-material";
import { Box, Button, Collapse, IconButton, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from "@mui/material";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import React from "react";
import DetailViewer from "./DetailViewer";
import { DeviceTypeDetailsViewerDefinition } from "./device-type/DeviceTypeDetailsViewerDefinition";
import Link from "next/link";

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
                <TableCell align="right">{props.data.deviceTypeId}</TableCell>
                <TableCell align="right">{props.data.deviceTypeName}</TableCell>
                <TableCell align="right">{props.data.powerConsumption}</TableCell>
                <TableCell align="right">{props.data.deviceTypeStatus}</TableCell>
                <TableCell align="right">
                    <Link href={`/device-type/versions/new?id=${props.data.deviceTypeId}`}>
                        <Button>
                            <Add></Add>
                        </Button>
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
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {props.data.versions.map((version, index) => (
                                        <TableRow key={index}>
                                            <TableCell >{version.versionNumber}</TableCell>
                                            <TableCell>{version.status}</TableCell>
                                            <TableCell>{version.createDateTime}</TableCell>
                                            <TableCell padding="checkbox">
                                                <DetailViewer definition={DeviceTypeDetailsViewerDefinition()} data={version} />
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