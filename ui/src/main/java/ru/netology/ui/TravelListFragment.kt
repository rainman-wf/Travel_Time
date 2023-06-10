package ru.netology.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.ui.databinding.FragmentTravelListBinding

@AndroidEntryPoint
class TravelListFragment : Fragment(R.layout.fragment_travel_list) {

    private val viewModel: TravelListViewModel by viewModels()
    private lateinit var binding: FragmentTravelListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTravelListBinding.bind(view)

        val host = requireActivity().supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        val controller = host.navController

        val adapter = FlightListAdapter(object : OnFlightItemClickListener {
            override fun onItemClicked(flightId: String) {
                controller.navigate(TravelListFragmentDirections.actionTravelListFragmentToTravelDetailsFragment(flightId))
            }

            override fun onLikeClicked(flightId: String) {
                viewModel.like(flightId)
            }
        })

        binding.flightList.adapter = adapter

        viewModel.flights.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {

            binding.loadingText.isVisible = it == LoadingState.Load
            binding.loadingProgress.isVisible = it == LoadingState.Load
            binding.flightList.isVisible = !binding.loadingText.isVisible

            if (it is LoadingState.Error) snack(it.message)

        }

    }
}