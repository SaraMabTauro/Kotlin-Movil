package com.example.habitos.ui.habitdetail

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.habitos.databinding.FragmentHabitDetailBinding
import com.example.habitos.ui.utils.LocationHelper
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HabitDetailFragment : Fragment() {

    private var _binding: FragmentHabitDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HabitDetailViewModel by viewModels()
    private lateinit var locationHelper: LocationHelper

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        locationHelper = LocationHelper(requireContext())

        binding.getLocationButton.setOnClickListener {
            getLocation()
        }

        // Observar el estado del ViewModel y actualizar la UI (ejemplo)
        viewModel.habitDetailState.observe(viewLifecycleOwner) { state ->
            // Actualizar la UI según el estado
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permisos si no están concedidos
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Obtener la ubicación si los permisos están concedidos
            if (locationHelper.isLocationEnabled()) {
                val location = locationHelper.getCurrentLocation()
                if (location != null) {
                    // Utilizar la ubicación (por ejemplo, mostrarla en la UI)
                    binding.locationTextView.text = "Lat: ${location.latitude}, Lon: ${location.longitude}"

                    // También puedes guardar la ubicación en el ViewModel
                    viewModel.setLocation(location)
                } else {
                    showError("No se pudo obtener la ubicación. Intenta de nuevo.")
                }
            } else {
                showError("Por favor, activa los servicios de ubicación.")
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido, volver a intentar obtener la ubicación
                    getLocation()
                } else {
                    showError("Permiso de ubicación denegado.")
                }
            }
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}