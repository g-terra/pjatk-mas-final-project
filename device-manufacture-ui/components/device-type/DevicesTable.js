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
                        <TableCell align="center"></TableCell>
                        <TableCell align="center">Id</TableCell>
                        <TableCell align="center">Name</TableCell>
                        <TableCell align="center">Power Comsumption</TableCell>
                        <TableCell width={150} align="center">Status</TableCell>
                        <TableCell align="center" ></TableCell>
                        <TableCell align="center" ></TableCell>
                        <TableCell align="center" ></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data.map((entry) => (
                        <DevicesTableRow key={entry.deviceTypeId} data={entry} onRefeshRequired={props.onRefeshRequired} />
                    ))}
                </TableBody>
            </Table>
            <TablePagination
                rowsPerPageOptions={[8,16,32]}
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
