package ru.netology.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.common.utils.log
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
            override fun onItemClicked(flight: LikableFlight) {
                controller.navigate(TravelListFragmentDirections.actionTravelListFragmentToTravelDetailsFragment(flight.flight.searchToken))
            }

            override fun onLikeClicked(flight: LikableFlight) {
                viewModel.like(flight.flight.searchToken)
            }

        })

        binding.flightList.adapter = adapter

        viewModel.likableFlights.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {
            when (it) {
                is LoadingState.Load -> snack("LOADING...")
                is LoadingState.Success -> snack("SUCCESS!")
                is LoadingState.Error -> {
                    log(it.message)
                    snack(it.message)
                }
                else -> {}
            }
        }

    }
}