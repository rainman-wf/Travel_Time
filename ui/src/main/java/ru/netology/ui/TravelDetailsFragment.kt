package ru.netology.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.ui.databinding.FragmentTravelDetailsBinding

@AndroidEntryPoint
class TravelDetailsFragment : Fragment(R.layout.fragment_travel_details) {

    private lateinit var binding: FragmentTravelDetailsBinding
    private val viewModel: TravelDetailsViewModel by viewModels()
    private val args: TravelDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTravelDetailsBinding.bind(view)
        binding.root.transitionName = args.transitionName

        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.item_shared_element_transition)
        viewModel.getFlightById(args.filghtId)

        val host = requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        val controller = host.navController

        binding.topToolbar.setNavigationOnClickListener {
            controller.navigateUp()
        }

        viewModel.flight.observe(viewLifecycleOwner) {
            binding.topToolbar.subtitle = "${it.startCity} - ${it.endCity}"
        }
    }
}