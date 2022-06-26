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
import FunctionalityDetailViewer from './FunctionalityDetailViewer';



export default function FunctionalitiesTable(props) {

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

        props.onSelectionChanged(newSelected);

        setSelected(newSelected)
    };


    return (

        <TableContainer component={Paper}>
            <Table stickyHeader aria-label="sticky table" size="small">
                <TableHead>
                    <TableRow>
                        <TableCell key="checkbox" padding="checkbox" />
                        <TableCell key={2} align="center">Id</TableCell>
                        <TableCell key={3} align="center">Name</TableCell>
                        <TableCell key={5} align="center" />
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.data
                        .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                        .map((functionality, index) => {
                            return (
                                <TableRow key={index} hover role="checkbox" tabIndex={-1} >
                                    {getRowCells(functionality)}
                                </TableRow>
                            );
                        })}
                </TableBody>
            </Table>
            <TablePagination
                rowsPerPageOptions={[10, 25, 100]}
                component="div"
                count={props.data.length}
                rowsPerPage={rowsPerPage}
                page={page}
                onPageChange={handleChangePage}
                onRowsPerPageChange={handleChangeRowsPerPage}
            />
        </TableContainer>


    );

    function getRowCells(functionality) {

        return (<>

            <TableCell key="checkbox">
                <Checkbox
                    checked={isSelected(functionality.id)}
                    onClick={(event) => handleCheckboxClick(event, functionality.id)}
                />
            </TableCell>
            <TableCell align="center">
                {functionality.id}
            </TableCell>
            <TableCell align="center">
                {functionality.name}
            </TableCell>
            <TableCell align="center">
                <FunctionalityDetailViewer data={functionality} />
            </TableCell>
        </>);


    }
}
