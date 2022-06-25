import { Add, South } from "@mui/icons-material"
import { TextField } from "@mui/material"
import axios from "axios"
import { useRouter } from 'next/router'
import * as React from "react"

const buildFormData = (name, powerConsumption) => {
    return {
        name,
        powerConsumption
    }
}

export function DeviceTypeCreateFormDefinition() {

    const router = useRouter()

    const onSave = (data) => { return axios.post(process.env.deviceManufactureApi + '/device-type', data) }

    const redirect = (data) => { return router.push('/device-type/versions/new?id=' + data.id) }


    return ({
        title: "Create Device Type",
        buttonText: "Create New Device Type",
        buttonIcon: <Add />,
        initialState: {
            name: "",
            powerConsumption: 0
        },
        onSave: (form) => { return onSave(form) },
        onSaveSucess: redirect,
        fields: [{
            component: (data, handleChange, key) => {
                return <TextField
                    key={key}
                    autoFocus
                    margin="dense"
                    id="name"
                    label="Device name"
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