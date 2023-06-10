package ru.netology.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.common.utils.log
import ru.netology.ui.databinding.FragmentTravelDetailsBinding

@AndroidEntryPoint
class TravelDetailsFragment : Fragment(R.layout.fragment_travel_details) {

    private lateinit var binding: FragmentTravelDetailsBinding
    private val viewModel: TravelDetailsViewModel by viewModels()
    private val args: TravelDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getFlightById(args.filghtId)

        binding = FragmentTravelDetailsBinding.bind(view)

        val host = requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        val controller = host.navController

        binding.topToolbar.setNavigationOnClickListener {
            controller.navigateUp()
        }

        viewModel.flight.observe(viewLifecycleOwner) {
            it.log()
            binding.topToolbar.subtitle = "${it.flight.startCity} - ${it.flight.endCity}"
        }
    }
}