import { Box, Typography } from '@mui/material';
import Head from 'next/head';
import alertImage from '../public/alert.png';
import Image from 'next/image';

export default function Search() {
    return (
        <>
            <Head>
                <title>Not found</title>
            </Head>
            <>
                <Box m="auto" >
                    <Image src={alertImage} alt="alert" />
                    <Typography variant="h5" align='center'>Resource not found</Typography>
                </Box>
            </>
        </>
    );



}

