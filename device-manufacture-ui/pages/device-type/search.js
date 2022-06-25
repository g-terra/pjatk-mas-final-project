import { Add } from '@mui/icons-material';
import { Button, LinearProgress, TextField } from '@mui/material';
import { Box } from '@mui/system';
import axios from 'axios';
import { Router } from 'next/router';
import * as React from 'react';
import Dataviewer from '../../comps/Dataviewer';
import { DeviceTypeCreateFormDefinition } from '../../comps/device-type/DeviceTypeCreateFormDefinition';
import { DeviceTypeDataViewerDefinition } from '../../comps/device-type/DeviceTypeDataViewerDefinition';
import FormDialog from '../../comps/FormDialog';

const DeviceTypeSearch = () => {

  const [data, setData] = React.useState([]);
  const [loading, setLoading] = React.useState(true);
  const [searched, setSearched] = React.useState("");
  const [displayData, setDisplayData] = React.useState(data);

  React.useEffect(() => {
    return getData();
  }, []);



  

  return (loading ? loadingBar() : getPopulatedDataViewer());

  function loadingBar() {
    return (<Box sx={{ width: '100%' }}>
      <LinearProgress />
    </Box>);
  }

  function getPopulatedDataViewer() {

    const requestSearch = (searchTerm) => search(searchTerm);

    return <Box>
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
            requestSearch(e.target.value);
          }}
          variant="outlined" />
        <FormDialog sx={{ gridArea: 'create', minWidth: 350 }} definition={DeviceTypeCreateFormDefinition()}></FormDialog>
      </Box>
      <Dataviewer sx={{ minWidth: 700 }} definition={DeviceTypeDataViewerDefinition()} data={displayData} />
    </Box>;
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
    setDisplayData(data.filter(row => row.deviceName.toLowerCase().includes(searchTerm.toLowerCase())));
  }

  function getData() {
    const fetchAllItems = async () => {
      try {
        const response = await axios.get(process.env.deviceManufactureApi + '/device-type-version');
        if (response.data !== "") {
          let objects = response.data.map(JSON.stringify);
          let uniqueSet = new Set(objects);
          let uniqueArray = Array.from(uniqueSet).map(JSON.parse);
          setData(uniqueArray);
          setDisplayData(uniqueArray);
          setLoading(false);
        } else {
        }
      } catch (err) {
        console.log(err);
      }
    };
    fetchAllItems();
    return () => {
      setDisplayData([]);
      setData([]);
    };
  }
}

export default DeviceTypeSearch;


