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
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class TravelDetailsFragment : Fragment(R.layout.fragment_travel_details) {

    private lateinit var binding: FragmentTravelDetailsBinding
    private val viewModel: TravelDetailsViewModel by viewModels()
    private val args: TravelDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTravelDetailsBinding.bind(view)
        binding.travelInfo.root.transitionName = args.transitionName

        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.item_shared_element_transition)

        val flight = args.flight

        binding.travelInfo.apply {
            startCode.text = flight.startLocationCode
            startCity.text = flight.startCity
            startDate.text = flight.startDate.format(DateTimeFormatter.ofPattern("E dd.MM"))
            endCode.text = flight.endLocationCode
            endCity.text = flight.endCity
            endDate.text = flight.endDate.format(DateTimeFormatter.ofPattern("E dd.MM"))
            favorite.isChecked = flight.isLiked
            favorite.setOnClickListener {

            }

            price.text = flight.price.toAmount(binding.root.context.getString(R.string.currency))
        }


    }
}