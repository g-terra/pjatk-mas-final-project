import { Add } from "@mui/icons-material"
import { Chip, Typography } from "@mui/material"
import * as React from "react"


export function FuntionalityDetailsViewerDefinition() {

    return ({
        areas: [
            {
                title: "Properties",
                wrapping: (data) => {
                    return Object.entries(data.requiredProperties).map(([key, value], index) => {
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
