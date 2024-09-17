package com.digivalsolutions.bluetoothblepoc.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digivalsolutions.bluetoothblepoc.bluetoothmanager.Constants.ALL_BLE_PERMISSIONS
import com.digivalsolutions.bluetoothblepoc.ui.theme.BluetoothBlePocTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BluetoothBlePocTheme {
                BluetoothBlePoc()
            }
        }
    }
}

@Composable
fun BluetoothBlePoc() {
    val permissions = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { result ->
        if(result.values.all { it }){
            Log.e("DigiBle","All Permissions Granted")
        } else {
            Log.e("DigiBle","Not Granted Permissions - ${result.filter { !it.value }}")
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.padding(20.dp))
        TextButton(onClick = {
            permissions.launch(ALL_BLE_PERMISSIONS)
        }) {
            Text(text = "Request Permission")
        }
        TextButton(onClick = {

        }) {
            Text(text = "Bluetooth Scan")
        }
        TextButton(onClick = {

        }) {
            Text(text = "Bluetooth Advertise")
        }
        TextButton(onClick = {

        }) {
            Text(text = "Bluetooth Connect")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Devices : ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            IconButton(onClick = {

            }) {
                Icon(
                    Icons.Default.Refresh,
                    contentDescription = "Refresh"
                )
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(100){
                val isConnecting by remember { mutableStateOf(false) }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Device 01"
                    )

                    AnimatedContent(
                        targetState = isConnecting,
                        label = "isConnecting"
                    ) { isConnect ->
                        if(isConnect){
                            Text(text = "Connecting")
                        } else {
                            Button(onClick = {
                                isConnecting != isConnecting
                            }) {
                                Text(text = "Connect")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BluetoothBlePocTheme {
        BluetoothBlePoc()
    }
}