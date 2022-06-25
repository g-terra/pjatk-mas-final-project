import { Add } from "@mui/icons-material"
import { TextField } from "@mui/material"
import * as React from "react"


export function FunctionalityCreateFormDefinition(data) {

    const onSave = (form) => {
        console.log(form);
    }


    return ({
        // redirectTo: `/device-type/newVersion&=${newDeviceId}`
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
        }, {
            component: (data, handleChange, key) => {
                return <TextField
                    key={key}
                    autoFocus
                    margin="dense"
                    id="powerConsumption"
                    label="Power Consumption (W)"
                    type="number"
                    value={data.powerConsumption}
                    onChange={handleChange}
                    fullWidth
                    variant="standard"
                />
            }
        }]

    });

}