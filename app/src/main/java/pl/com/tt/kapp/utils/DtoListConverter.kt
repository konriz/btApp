package pl.com.tt.kapp.utils

import android.bluetooth.BluetoothDevice
import android.net.wifi.ScanResult
import pl.com.tt.kapp.modules.model.bluetooth.BluetoothDeviceDTO
import pl.com.tt.kapp.modules.model.bluetooth.BluetoothDTO
import pl.com.tt.kapp.modules.model.location.FusedLocationProvider
import pl.com.tt.kapp.modules.model.wifi.WifiNetworkDTO
import pl.com.tt.kapp.modules.model.wifi.WifiDTO

object DtoListConverter {

    fun wifiResultsToDto(results : List<ScanResult>) : WifiDTO {
        val dtos = mutableListOf<WifiNetworkDTO>()
        for(network in results){
            dtos.add(WifiNetworkDTO(network))
        }

        return WifiDTO(dtos.toList(), FusedLocationProvider.lastLocation)
    }

    fun bluetoothResultsToDto(results : List<BluetoothDevice>) : BluetoothDTO {
        val devicesDtos = mutableListOf<BluetoothDeviceDTO>()
        for(device in results){
            devicesDtos.add(BluetoothDeviceDTO(device))
        }
        return BluetoothDTO(
            devicesDtos,
            FusedLocationProvider.lastLocation
        )
    }

}