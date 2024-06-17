"use client";

import React, { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker } from "react-leaflet";
import * as L from "leaflet";

const SVG_ICON = L.divIcon({
  html: '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="red" class="w-6 h-6"><path fill-rule="evenodd" d="m11.54 22.351.07.04.028.016a.76.76 0 0 0 .723 0l.028-.015.071-.041a16.975 16.975 0 0 0 1.144-.742 19.58 19.58 0 0 0 2.683-2.282c1.944-1.99 3.963-4.98 3.963-8.827a8.25 8.25 0 0 0-16.5 0c0 3.846 2.02 6.837 3.963 8.827a19.58 19.58 0 0 0 2.682 2.282 16.975 16.975 0 0 0 1.145.742ZM12 13.5a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z" clip-rule="evenodd" /></svg>',
  className: "custom-svg-icon",
  iconSize: [32, 32],
});

const MapComponent = () => {
  return (
    <MapContainer
      center={[48.23640674879304, 16.369530639155258]}
      zoom={17}
      style={{ height: "500px" }}
    >
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
      <Marker
        key="marker"
        position={[48.23640674879304, 16.369530639155258]}
        icon={SVG_ICON}
      />
    </MapContainer>
  );
};

export default MapComponent;
