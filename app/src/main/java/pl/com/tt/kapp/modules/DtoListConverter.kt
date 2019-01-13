package pl.com.tt.kapp.modules

import android.bluetooth.BluetoothDevice
import android.net.wifi.ScanResult
import pl.com.tt.kapp.modules.bluetooth.model.BluetoothDeviceDTO
import pl.com.tt.kapp.modules.bluetooth.model.BluetoothResultsList
import pl.com.tt.kapp.modules.location.model.LocationDriver
import pl.com.tt.kapp.modules.wifi.model.WifiNetworkDTO
import pl.com.tt.kapp.modules.wifi.model.WifiResultsList

object DtoListConverter {

    fun wifiResultsToDto(results : List<ScanResult>) : WifiResultsList{
        val dtos = mutableListOf<WifiNetworkDTO>()
        for(network in results){
            dtos.add(WifiNetworkDTO(network))
        }

        return WifiResultsList(dtos.toList(), LocationDriver.lastLocation)
    }

    fun bluetoothResultsToDto(results : List<BluetoothDevice>) : BluetoothResultsList{
        val devicesDtos = mutableListOf<BluetoothDeviceDTO>()
        for(device in results){
            devicesDtos.add(BluetoothDeviceDTO(device))
        }
        return BluetoothResultsList(devicesDtos, LocationDriver.lastLocation)
    }

}