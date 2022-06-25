import { Box, Button, LinearProgress, Paper, TextField, Typography } from "@mui/material";
import axios from "axios";
import * as React from "react";
import Dataviewer from "../../../comps/Dataviewer";

import { FunctionalitiesDataViewDefinition } from "../../../comps/functionality/FunctionalitiesDataViewDefinition";

import { useRouter } from 'next/router'
import CreateFunctionalityDialog from "../../../comps/functionality/CreateFunctionalityDialog";


const NewDeviceVersion = () => {
    const [data, setData] = React.useState([]);
    const [loading, setLoading] = React.useState(true);
    const [searched, setSearched] = React.useState("");
    const [displayData, setDisplayData] = React.useState(data);
    const [selected, setSelected] = React.useState([]);

    const router = useRouter()

    const { id } = router.query

    const handleSelectionChanged = (selected) => {
        setSelected(selected);
    }


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
            <Typography variant="h6" color={'primary.main'} marginBottom={3}>
                Create New Device Version
            </Typography>
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
                <CreateFunctionalityDialog sx={{ gridArea: 'create' }} />
            </Box>
            <Dataviewer sx={{ minWidth: 200, maxWidth: 700 }} definition={FunctionalitiesDataViewDefinition()} data={displayData} onSelectionChange={handleSelectionChanged} />
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
        setDisplayData(data.filter(row => row.name.toLowerCase().includes(searchTerm.toLowerCase())));
    }


}

export default NewDeviceVersion