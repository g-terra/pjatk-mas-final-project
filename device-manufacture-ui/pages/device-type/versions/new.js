import { Box, Collapse, LinearProgress, Stack, TextField, Typography } from '@mui/material';
import axios from "axios";
import { useEffect, useState } from 'react';
import { useRouter } from 'next/router'
import FunctionalitiesTable from "../../../components/funcitonality/FunctionalitiesTable";
import DeviceCreationForm from '../../../components/device-type/DeviceCreationForm';
import FunctionalityCreationForm from '../../../components/funcitonality/FunctionalityCreationForm';
import DeviceNewVersionCreationForm from '../../../components/device-type/DeviceNewVersionCreationForm';
import Head from 'next/head';


export default function NewDeviceVersion() {
  const [selected, setSelected] = useState([]);
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searched, setSearched] = useState("");
  const [displayData, setDisplayData] = useState(data);
  const [device, setDevice] = useState({});

  const router = useRouter();


  useEffect(() => {
    function fetch() {
      if(router.isReady){
        axios.get(process.env.deviceManufactureApi + '/device-type/' + router.query['id'])
        .then((response) => {
          setDevice(response.data);
          console.log("response device", response);
        })
        .catch
        (error => {
          console.log(error);
          router.push('/404');
        }
        )
      }
    }
    fetch();

  }, [router.isReady]);

  const handleSelectionChanged = (selected) => {
    setSelected(selected);
  }

  const [refresh, setRefresh] = useState(0);

  useEffect(() => {
    function fetch() {
      axios.get(process.env.deviceManufactureApi + '/functionality').then(response => {
        setData(response.data);
        setDisplayData(response.data);
        setLoading(false);
      }
      ).catch(error => {
        console.log(error);
      }
      );
    }
    fetch();
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
            <Stack spacing={3} color="primary.main">
              <Typography variant="h5" >Pick functionalites for the new version of {device.name}</Typography>
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
                  <DeviceNewVersionCreationForm sx={{ width: "100%" }} functionalites={selected} deviceId={device.id} ></DeviceNewVersionCreationForm>
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