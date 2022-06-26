import { Box, Collapse, LinearProgress, Stack, TextField, Typography } from '@mui/material';
import axios from "axios";
import * as React from "react";
import { useRouter } from 'next/router'
import FunctionalitiesTable from "../../../components/funcitonality/FunctionalitiesTable";
import DeviceCreationForm from '../../../components/device-type/DeviceCreationForm';
import FunctionalityCreationForm from '../../../components/funcitonality/FunctionalityCreationForm';
import DeviceNewVersionCreationForm from '../../../components/device-type/DeviceNewVersionCreationForm';
import Head from 'next/head';


const NewDeviceVersion = () => {
  const [selected, setSelected] = React.useState([]);
  const [data, setData] = React.useState([]);
  const [loading, setLoading] = React.useState(true);
  const [searched, setSearched] = React.useState("");
  const [displayData, setDisplayData] = React.useState(data);

  const router = useRouter()

  const { id } = router.query;

  React.useEffect(() => {
    axios.get(process.env.deviceManufactureApi + '/device-type/' + id)
      .catch
      (error => {
        console.log(error);
        router.push('/device-type/search');
      }
      )
  }, [id]);



  const handleSelectionChanged = (selected) => {
    setSelected(selected);
  }

  const [refresh, setRefresh] = React.useState(0);

  React.useEffect(() => {
    axios.get(process.env.deviceManufactureApi + '/functionality').then(response => {
      setData(response.data);
      setDisplayData(response.data);
      setLoading(false);
    }
    ).catch(error => {
      console.log(error);
    }
    );
  }, [refresh]);

  const handleCreatedFunctionalitySuccessfully = () => {
    setRefresh(oldKey => oldKey + 1)
  };

  const requestSearch = (searchTerm) => search(searchTerm)

  return (
    <>
      <Head>
        <title>New Version </title>
      </Head>
      <>
        <Box m="auto" >
          <Collapse in={loading}>
            <LinearProgress />
          </Collapse>
          <Collapse in={!loading}>
            <Stack spacing={3}>
              <Typography variant="h5">Pick functionalites for the new version</Typography>
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
                  <FunctionalityCreationForm sx={{ width: "100%" }} onFuncitonalityCreatedSuccessfully={handleCreatedFunctionalitySuccessfully} />
                </Box>
              </Box>
              <Box sx={{ display: 'flex' }}>
                <FunctionalitiesTable data={displayData} onSelectionChanged={handleSelectionChanged} />
              </Box>
              <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)' }}>
                <Box />
                <Box />
                <Box sx={{ display: "flex" }} >
                  <DeviceNewVersionCreationForm sx={{ width: "100%" }} properties={selected} deviceId={id} ></DeviceNewVersionCreationForm>
                </Box>
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
    setDisplayData(data.filter(row => row.name.toLowerCase().includes(searchTerm.toLowerCase())));
  }

}

export default NewDeviceVersion