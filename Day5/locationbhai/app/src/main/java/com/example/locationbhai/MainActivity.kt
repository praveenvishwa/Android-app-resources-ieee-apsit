package com.example.locationbhai

import android.os.Bundle
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*

import java.util.jar.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.provider.Settings
import android.widget.TextView
import android.os.ProxyFileDescriptorCallback
import com.google.android.gms.tasks.Task
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location

class MainActivity : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback

    val REQUEST_CODE=1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION))
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
        else
        {
            buildLocationRequest()
            buildLocationCallBack()
        }

        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

        btn_start_updates.setOnClickListener(View.OnClickListener {

            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,
                Looper.myLooper())


            //change state

            btn_start_updates.isEnabled=!btn_start_updates.isEnabled
            btn_stop_updates.isEnabled=!btn_stop_updates.isEnabled
        })


        btn_stop_updates.setOnClickListener(View.OnClickListener {

            fusedLocationProviderClient.removeLocationUpdates(locationCallback)

            //change state
            btn_start_updates.isEnabled=!btn_start_updates.isEnabled
            btn_stop_updates.isEnabled=!btn_stop_updates.isEnabled


        })

    }

    private fun buildLocationCallBack() {

        locationCallback=object :LocationCallback(){

            override fun onLocationResult(p0: LocationResult?) {
                var location=p0!!.locations.get(p0!!.locations.size-1)
                txt_location.text=location.latitude.toString()+"/"+location.longitude.toString()
            }
        }
    }

    private fun buildLocationRequest() {
        locationRequest=LocationRequest()
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval=5000
        locationRequest.fastestInterval=3000
        locationRequest.smallestDisplacement=10f

    }
}
