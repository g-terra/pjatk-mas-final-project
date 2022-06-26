import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow } from "@mui/material";
import axios from "axios";
import React from "react";
import { DevicesTableRow } from "./DevicesTableRow";

const rowsPerPageBase = 8

export default function DevicesTable(props) {

    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(rowsPerPageBase);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };


    return (
        <TableContainer component={Paper}>
            <Table aria-label="collapsible table">
                <TableHead>
                    <TableRow>
                        <TableCell key={1} align="center"></TableCell>
                        <TableCell key={2} align="center">Id</TableCell>
                        <TableCell key={3} align="center">Name</TableCell>
                        <TableCell key={4} align="center">Power Comsumption</TableCell>
                        <TableCell key={5} align="center">Status</TableCell>
                        <TableCell key={6} align="center"></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((entry) => (
                        <DevicesTableRow key={entry.deviceTypeId} data={entry} />
                    ))}
                </TableBody>
            </Table>
            <TablePagination
                rowsPerPageOptions={[rowsPerPageBase, rowsPerPageBase * 2, rowsPerPageBase * 4]}
                component="div"
                count={props.data.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </TableContainer>
    );


}
