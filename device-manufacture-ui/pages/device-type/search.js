import { useEffect, useState } from 'react';
import { Box, Collapse, LinearProgress, Stack, TextField, Typography } from '@mui/material';
import axios from 'axios';
import DevicesTable from '../../components/device-type/DevicesTable';
import DeviceCreationForm from '../../components/device-type/DeviceCreationForm';
import Head from 'next/head';

export default function Search() {

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searched, setSearched] = useState("");
  const [displayData, setDisplayData] = useState(data);

  useEffect(() => {
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
    <>
      <Head>
        <title>Device Types</title>
      </Head>
      <>
        <Box m="auto" >
          <Collapse in={loading}>
            <LinearProgress />
          </Collapse>
          <Collapse in={!loading}>
            <Stack spacing={3} minWidth={1000}>
              <Typography variant="h5">Device types</Typography>
              <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)' }}>
                <Box sx={{ display: "flex" }}>
                  <TextField
                    sx={{ width: "100%" }}
                    id="search"
                    label="Search"
                    value={searched}
                    onChange={(e) => {
                      setSearched(e.target.value);
                      search(e.target.value);
                    }}
                    variant="outlined" />
                </Box>
                <Box />
                <Box sx={{ display: "flex" }}>
                  <DeviceCreationForm sx={{ width: "100%" }} />
                </Box>
              </Box>
              <Box sx={{ display: 'flex' }}>
                <DevicesTable data={displayData} />
              </Box>
            </Stack>
          </Collapse>
        </Box>
      </>
    </>
  );

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

