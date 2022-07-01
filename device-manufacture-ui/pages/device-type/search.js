
import { useEffect, useState } from 'react';
import { Box, Collapse, LinearProgress, Stack, TextField, Typography } from '@mui/material';
import axios from 'axios';
import DevicesTable from '../../components/device-type/DevicesTable';
import DeviceCreationForm from '../../components/device-type/DeviceCreationForm';
import Head from 'next/head';

export default function Search() {

  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [refresh, setRefresh] = useState(0);
  const [pageParams, setParams] = useState({
    pageNumber: 0,
    pageSize: 8,
    search: "",
    totalPages: 0,
    totalElements: 0,
  });

  function handlePageParamsChanged(params) {
    setLoading(true);
    setParams(params);
    setRefresh(refresh + 1);
  }

  function handleSearchChanged(search) {
    setParams({
      ...pageParams,
      search: search,
      pageNumber: 0,
    });
  }

  useEffect(() => {
    const delayDebounceFn = setTimeout(() => {
      setLoading(true);
      requestData();
    }, 400);
    return () => clearTimeout(delayDebounceFn)
  }, [pageParams.search]);

  useEffect(() => {
    requestData();
  }, [refresh]);

  return (
    <>
      <Head>
        <title>Device Types</title>
      </Head>
      <>
        <Box m="auto" >
          <Stack spacing={1.5} minWidth={500} color="primary.main" >
            <Typography variant="h5">Device types</Typography>
            <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)' }}>
              <Box sx={{ display: "flex" }}>
                <TextField
                  sx={{ width: "100%" }}
                  id="search"
                  label="Search"
                  value={pageParams.search}
                  onChange={(e) => {
                    handleSearchChanged(e.target.value);
                  }}
                  variant="outlined" />
              </Box>
              <Box />
              <Box sx={{ display: "flex" }}>
                <DeviceCreationForm sx={{ width: "100%" }} />
              </Box>
            </Box>
            <Collapse in={loading}>
              <LinearProgress />
            </Collapse>
            <Collapse in={!loading}>
              <Box sx={{ display: 'flex' }}>
                <DevicesTable data={data} onRefeshRequired={setRefresh} onPageParamsChanged={handlePageParamsChanged} pageParams={pageParams} />
              </Box>
            </Collapse>
          </Stack>
        </Box>
      </>
    </>
  );

  function requestData() {
    axios.get(process.env.deviceManufactureApi + `/device-type?pageNumber=${pageParams.pageNumber}&pageSize=${pageParams.pageSize}&name=${pageParams.search}`).then(response => {
      setData(response.data.content);
      const newPageParams = { ...pageParams }
      setLoading(false);

      newPageParams.totalPages = response.data.totalPages;
      newPageParams.totalElements = response.data.totalElements;

      setParams(newPageParams);

    }
    ).catch(error => {
      console.log(error);
    });
  }

}

