import * as React from 'react';
import { Box, Collapse, LinearProgress, TextField } from '@mui/material';
import axios from 'axios';
import DevicesTable from '../../comps/DevicesTable';
import { DeviceTypeCreateFormDefinition } from '../../comps/device-type/DeviceTypeCreateFormDefinition';
import FormDialog from '../../comps/FormDialog';

const products = () => {

  const [data, setData] = React.useState([]);
  const [loading, setLoading] = React.useState(true);
  const [searched, setSearched] = React.useState("");
  const [displayData, setDisplayData] = React.useState(data);

  React.useEffect(() => {
    axios.get(process.env.deviceManufactureApi + "/device-type").then(response => {
      setData(response.data);
      setDisplayData(response.data);
      setLoading(false);
    }
    ).catch(error => {
      console.log(error);
    });
  }, []);


  return (
    <Box>
      <Collapse in={loading}>
        <LinearProgress />
      </Collapse>
      <Collapse in={!loading}>
        <Box
          component="form" sx={{
            display: 'grid',
            minWidth: 700,
            marginBottom: 3,
            gridTemplateColumns: 'repeat(3, 1fr)',
            gridTemplateRows: 'auto',
            gridTemplateAreas: `
      "search .  create"
      `,
          }}>
          <TextField
            sx={{ gridArea: 'search', minWidth: 250 }}
            id="search"
            label="Search"
            value={searched}
            onChange={(e) => {
              setSearched(e.target.value);
              search(e.target.value);
            }}
            variant="outlined" />
          <FormDialog sx={{ gridArea: 'create', minWidth: 350 }} definition={DeviceTypeCreateFormDefinition()}></FormDialog>
        </Box>
        <DevicesTable sx={{ minWidth: 700 }} data={displayData} />
      </Collapse>
    </Box>
  );

 

  function getDataViewer() {

    return;
  }

  function search(searchTerm) {
    if (searchTerm.length > 0) {
      filterDisplayedData(searchTerm);
    }
    else {
      resetDisplayedData();
    }
  }

  function resetDisplayedData() {
    setSearched("");
    setDisplayData(data);
  }

  function filterDisplayedData(searchTerm) {
    setSearched(searchTerm);
    setDisplayData(data.filter(row => row.deviceTypeName.toLowerCase().includes(searchTerm.toLowerCase())));
  }

}

export default products;


