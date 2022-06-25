import { Add } from "@mui/icons-material"
import { Chip, Typography } from "@mui/material"
import * as React from "react"


export function DeviceTypeDetailsViewerDefinition() {

    return ({
        areas: [
            {
                title: "Functionalities",
                wrapping: (data) => {
                   return data.functionalities.map((functionality, index) => {
                        return (
                            <Chip
                                key={index}
                                sx={{ margin: .5 }}
                                label={functionality}
                                color="success"
                            />
                        )
                    })
                }
            },
            {
                title: "Properties",
                wrapping: (data) => {
                    return Object.entries(data.propertyValues).map(([key, value], index) => {
                        return (
                            <Typography key={index} variant="body1" gutterBottom>
                                {key} : {value}
                            </Typography>

                        )
                    }
                    )
                }
            }
        ]
    });
}
