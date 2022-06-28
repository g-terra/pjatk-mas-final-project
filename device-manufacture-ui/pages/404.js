import { useEffect, useState } from 'react';
import { Box, Collapse, LinearProgress, Stack, TextField, Typography } from '@mui/material';
import axios from 'axios';
import Head from 'next/head';
import alertImage from '../public/alert.png';
import Image from 'next/image';

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
                    <Image src={alertImage} alt="alert" />
                    <Typography variant="h5" align='center'>Resource not found</Typography>
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

