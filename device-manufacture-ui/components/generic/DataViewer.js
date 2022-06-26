import * as React from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import { Checkbox } from '@mui/material';



export default function DataViewer(props) {

    const [page, setPage] = React.useState(0);
    const [rowsPerPage, setRowsPerPage] = React.useState(10);

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    const [selected, setSelected] = React.useState([]);

    const isSelected = (id) => selected.includes(id);

    const handleCheckboxClick = (event, id) => {

        let newSelected = [];

        if (event.target.checked) {
            newSelected = [...selected, id];
        } else {
            newSelected = selected.filter(x => x !== id);
        }

        if (props.onSelectionChange) {
            props.onSelectionChange(newSelected);
        }

        setSelected(newSelected)
    };


    return (
        <>
            <TableContainer component={Paper}>
                <Table stickyHeader sx={{ minWidth: props.minWidth ?  props.minWidth : 800}} aria-label="sticky table" size="small">
                    <TableHead>
                        <TableRow>
                            {getColumns(props.definition.checkbox)}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {props.data
                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                            .map((entry, index) => {
                                return (
                                    <TableRow key={index} hover role="checkbox" tabIndex={-1} >
                                        {getRowCells(props.definition.checkbox, entry)}
                                    </TableRow>
                                );
                            })}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={props.data.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </>
    );

    function getRowCells(checkbox, entry) {
        let rowCells = props.definition.columns.map((column) => {
            const value = entry[column.id];
            return (
                <TableCell key={column.id} align={column.align}>
                    {column.wrapping == null ? value : column.wrapping(entry, value)}
                </TableCell>
            );
        });

        if (checkbox) {
            rowCells.unshift(
                <TableCell key="checkbox">
                    <Checkbox
                        checked={isSelected(entry[props.definition.idColumn])}
                        onClick={(event) => handleCheckboxClick(event, entry[props.definition.idColumn])}
                    />
                </TableCell>);
        }

        return rowCells;
    }

    function getColumns(checkbox) {

        let columns = props.definition.columns.map((column) => (
            <TableCell
                key={column.id}
                align={column.align}

            >
                {column.label}
            </TableCell>
        ));

        if (checkbox) {
            columns.unshift(<TableCell key="checkbox" padding="checkbox" />);
        }

        return columns;

    }
}
