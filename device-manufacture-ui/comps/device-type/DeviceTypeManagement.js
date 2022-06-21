import * as React from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import { Box, Button, Chip, TextField } from '@mui/material';
import { Add } from '@mui/icons-material';
import DeviceTypeDetails from './DeviceTypeDetail';
import axios from 'axios';



export default function Dataviewer(props) {


  const [originalData, setOriginalData] = React.useState([]);

  React.useEffect(() => {
    const fetchAllItems = async () => {
      try {
        const response = await axios.get(process.env.deviceManufactureApi + '/device-type-version');
        if (response.data !== "") {
          console.log(response.data); //Prints out my three objects in an array in my console. works great
          let objects = response.data.map(JSON.stringify);
          let uniqueSet = new Set(objects);
          let uniqueArray = Array.from(uniqueSet).map(JSON.parse);
          setOriginalData(uniqueArray);
          setData(uniqueArray);
        } else {
        }
      } catch (err) {
        console.log(err);
      }
    };
    fetchAllItems();
    return () => {
      setOriginalData([]);
      setData([]);
    };
  }, []);

  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(10);
  const [data, setData] = React.useState(originalData);

  console.log(data);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const [searched, setSearched] = React.useState("");;

  const requestSearch = (searchedVal) => {
    if (searchedVal.length > 0) {
      setSearched(searchedVal);
      setData(originalData.filter(row => row.deviceName.toLowerCase().includes(searchedVal.toLowerCase())));
    }
    else {
      setSearched("");
      setData(originalData);
    }
  };

  return (
    //search v
    <Box >

      <Box component="form" sx={{
        m:3,
        display: 'grid',
        gridTemplateColumns: 'repeat(4, 1fr)',
        gap: 1,
        gridTemplateRows: 'auto',
        gridTemplateAreas: `"search . . create"`,
      }} >
        <TextField
          sx={{ gridArea: 'search' }}
          id="search"
          label="Search"
          value={searched}
          onChange={(e) => {
            setSearched(e.target.value);
            requestSearch(e.target.value);
          }
          }
          variant="outlined"
        />

        <Button startIcon={<Add/>} sx={{ gridArea: 'create' }} variant="contained" color="primary" >
          create new Device Type
        </Button>

      </Box>


      <Paper sx={{ width: '100%', overflow: 'hidden' }}>
        <TableContainer sx={{ maxHeight: 600 }}>
          <Table stickyHeader aria-label="sticky table">
            <TableHead>
              <TableRow>
                {props.columns.map((column) => (
                  <TableCell
                    key={column.id}
                    align={column.align}
                    style={{ minWidth: column.minWidth }}
                  >
                    {column.label}
                  </TableCell>
                ))}
              </TableRow>
            </TableHead>
            <TableBody>
              {data
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((entry) => {
                  return (
                    <TableRow hover role="checkbox" tabIndex={-1} key={entry.versionId}>
                      {props.columns.map((column) => {
                        const value = entry[column.id];
                        return (
                          <TableCell key={column.id} align={column.align}>
                     {
                              (function () {
                                switch (column.id) {
                                  case "info":
                                    return <DeviceTypeDetails data={entry} />
                                  case "status":
                                    if (value === "AVAILABLE") {
                                      return  <Chip
                                      sx={{ margin: .5 }}
                                      label={value}
                                      color="success"
                                  />
                                    }
                                    else {
                                      return  <Chip
                                      sx={{ margin: .5 }}
                                      label={value}
                                      color="warning"
                                  />
                                    }
                                  default:
                                    return value;
                                }
                              })()

                            }
                          </TableCell>
                        );
                      })}

                    </TableRow>
                  );
                })}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[10, 25, 100]}
          component="div"
          count={data.length}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </Paper>
    </Box>


  );
}
