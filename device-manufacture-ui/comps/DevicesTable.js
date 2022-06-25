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
                        <TableCell key={1} align="right"></TableCell>
                        <TableCell key={2} align="right">Id</TableCell>
                        <TableCell key={3} align="right">Name</TableCell>
                        <TableCell key={4} align="right">Power Comsumption</TableCell>
                        <TableCell key={5} align="right">Status</TableCell>
                        <TableCell key={6} align="right"></TableCell>
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
