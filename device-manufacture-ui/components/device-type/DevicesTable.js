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
                    {props.data.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((entry) => (
                        <DevicesTableRow key={entry.deviceTypeId} data={entry} onRefeshRequired={props.onRefeshRequired} />
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
