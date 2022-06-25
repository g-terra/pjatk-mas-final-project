import { Add } from "@mui/icons-material"
import { Box, Button, Stack, TextField } from "@mui/material"
import { DataGrid } from "@mui/x-data-grid"
import * as React from "react"
import { PropertyDataviewerDefinition } from "./properties/PropertyDataViewDefinition"


export function FunctionalityCreateFormDefinition(data) {


    const onSave = (data) => { return axios.post(process.env.deviceManufactureApi + '/functionality', data) }


    return ({
        title: "Create new Functionality",
        buttonText: "Create new Functionality",
        buttonIcon: <Add />,
        initialState: {
            name: "",
            properties: {
                "name": "",
                "type": "NUMERIC",
            }
        },
        onSave: (form) => { return onSave(form) },
        fields: [{
            component: (data, handleChange, key) => {
                return <TextField
                    key={key}
                    autoFocus
                    margin="dense"
                    id="name"
                    label="Functionality name"
                    type="text"
                    value={data.name}
                    onChange={handleChange}
                    fullWidth
                    variant="standard"
                />
            }
        },
        {
            component: (data, handleChange, key) => {
                return (
                    <Stack sx={{ marginTop:2, height: 200, width: '100%' }}>
                        <Button>
                            <Add /> Add Property
                        </Button>

                        <DataGrid
                            rows={data}
                            columns={PropertyDataviewerDefinition()}
                            pageSize={5}
                            rowsPerPageOptions={[5]}
                            disableSelectionOnClick
                        />
                    </Stack>
                );

            }
        }
        ]

    });

}

