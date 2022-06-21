import axios from 'axios';
import * as React from 'react';
import DeviceTypeManagement from "../../comps/device-type/DeviceTypeManagement";

const columns = [
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
    id: 'versionId',
    label: 'Version',
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
  },
  {
    id: 'info',
    label: '',
    minWidth: 170,
    align: 'center',
  },
];

function createData(id, name, deviceTypeVersionId, powerConsumption, status, functionalities, properties) {
  return { id, name, deviceTypeVersionId, powerConsumption, status, functionalities, properties };

}

const DeviceTypeSearch = () => {

  



  return (
    <>
      <DeviceTypeManagement columns={columns} ></DeviceTypeManagement>
    </>
  );
}

export default DeviceTypeSearch;