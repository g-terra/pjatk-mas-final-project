import { Checkbox } from '@mui/material';
import * as React from 'react';
import DetailViewer from '../DetailViewer';
import { FuntionalityDetailsViewerDefinition } from './FuntionalityDetailsViewerDefinition';


export function FunctionalitiesDataViewDefinition() {
  return {
    idColumn:"functionalityId",
    checkbox: true,
    columns:[
    {
      id: 'id',
      label: 'ID',
    },
    {
      id: 'name',
      label: 'Name',
    },
    {
      id: 'info',
      label: '',
      align: 'center',
      wrapping: (entry, value) => {
        return <DetailViewer data={entry} definition={FuntionalityDetailsViewerDefinition()}  />;
      }
    },
  ]};
}
