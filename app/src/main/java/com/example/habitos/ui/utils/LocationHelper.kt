package com.example.habitos.ui.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationHelper(private val context: Context) {

    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    //Para el manejo de permisos
    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 123
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(): Location? {
        // Verificar si se tienen los permisos de ubicación
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Si no se tienen los permisos requerirlos
            return null
        }

        try {
            // Obtener la ubicación del proveedor de ubicación activo
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            return location
        } catch (e: SecurityException) {
            e.printStackTrace()
            return null
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            return null
        }
    }

    fun isLocationEnabled(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

}