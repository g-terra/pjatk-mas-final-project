import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow } from "@mui/material";
import axios from "axios";
import React from "react";
import { DevicesTableRow } from "./DevicesTableRow";



export default function DevicesTable(props) {


    const handleChangePage = (event, newPage) => {
        const newPageParams = { ...props.pageParams, pageNumber: newPage };
        props.onPageParamsChanged(newPageParams);
    };

    const handlePageSizeChange = (event) => {
        const newPageParams = { ...props.pageParams, pageSize: +event.target.value };
        props.onPageParamsChanged(newPageParams);
    };


    return (
        <TableContainer component={Paper}>
            <Table aria-label="collapsible table">
                <TableHead>
                    <TableRow>
                        <TableCell sx={{ minWidth: 10, width: 50 }} align="center"></TableCell>
                        <TableCell sx={{ minWidth: 35, width: 150 }} align="center">Id</TableCell>
                        <TableCell sx={{ minWidth: 100, width: 250 }} align="center">Name</TableCell>
                        <TableCell sx={{ minWidth: 85, width: 200 }} align="center">Power Comsumption (W)</TableCell>
                        <TableCell sx={{ minWidth: 100, width: 200 }} align="center">Status</TableCell>
                        <TableCell sx={{ minWidth: 45 }} align="center" ></TableCell>
                        <TableCell sx={{ minWidth: 45 }} align="center" ></TableCell>
                        <TableCell sx={{ minWidth: 45 }} align="center" ></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data.map((entry) => (
                        <DevicesTableRow key={entry.deviceTypeId} data={entry} onRefeshRequired={props.onRefeshRequired} />
                    ))}
                </TableBody>
            </Table>
            <TablePagination
                rowsPerPageOptions={[8, 16, 32]}
                component="div"
                count={props.pageParams.totalElements}
                rowsPerPage={props.pageParams.pageSize}
                page={props.pageParams.pageNumber}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handlePageSizeChange}
            />
        </TableContainer>
    );


}
