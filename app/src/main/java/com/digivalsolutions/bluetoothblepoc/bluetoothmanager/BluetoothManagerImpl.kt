package com.digivalsolutions.bluetoothblepoc.bluetoothmanager

import android.Manifest
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat

class BluetoothManagerImpl(
    private val context: Context
) : com.digivalsolutions.bluetoothblepoc.bluetoothmanager.BluetoothManager {

    private val bluetooth = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager

    private val scanner : BluetoothLeScanner = bluetooth.adapter.bluetoothLeScanner

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
        }
    }

    private fun checkRelevantPermissions() : Boolean{
        return Constants.ALL_BLE_PERMISSIONS.all { context.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    override fun startScan() {
        scanner.startScan(scanCallback)
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    override fun stopScan() {
        scanner.startScan(scanCallback)
    }

    override fun isMatchingAllRequirements():Boolean {
        return bluetooth.adapter.isEnabled && checkRelevantPermissions()
    }
}