package com.digivalsolutions.bluetoothblepoc.bluetoothmanager

interface BluetoothManager {

    fun startScan()

    fun stopScan()

    fun isMatchingAllRequirements():Boolean

}