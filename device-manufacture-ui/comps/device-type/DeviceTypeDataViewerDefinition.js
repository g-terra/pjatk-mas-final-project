import { Chip } from '@mui/material';
import * as React from 'react';
import DetailViewer from '../DetailViewer';
import { DeviceTypeDetailsViewerDefinition } from './DeviceTypeDetailsViewerDefinition';

export function DeviceTypeDataViewerDefinition(data) {
  return {
    idColumn: 'deviceTypeId',
    checkbox: false,
    columns:[
      {
        id: 'deviceTypeId',
        label: 'ID',
        minWidth: 170
      },
      {
        id: 'deviceName',
        label: 'Name',
        minWidth: 100
      },
      {
        id: 'versionUniqueId',
        label: 'Version id',
        minWidth: 170,
        align: 'center',
      },
      {
        id: 'powerConsumption',
        label: 'Power Consumption (W)',
        minWidth: 170,
        align: 'center',
      },
      {
        id: 'status',
        label: 'Status',
        minWidth: 170,
        align: 'center',
        wrapping: (entry, value) => {
          if (value === "AVAILABLE") {
            return <Chip
              sx={{ margin: .5 }}
              label={value}
              color="success" />;
          }
          else {
            return <Chip
              sx={{ margin: .5 }}
              label={value}
              color="warning" />;
          }
        }
      },
      {
        id: 'info',
        label: '',
        minWidth: 170,
        align: 'center',
        // wrapping: (entry, value) => {
        //   return <DeviceTypeDetail data={entry} />;
        // } 
        wrapping: (entry, value) => {
          return <DetailViewer definition={DeviceTypeDetailsViewerDefinition()} data={entry} />;
        }
      },
    ]
  }
}
