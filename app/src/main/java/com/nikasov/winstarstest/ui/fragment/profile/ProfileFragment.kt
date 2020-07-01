package com.nikasov.winstarstest.ui.fragment.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.model.ActionModel
import com.nikasov.winstarstest.data.model.StatisticModel
import com.nikasov.winstarstest.ui.adapter.ActionsAdapter
import com.nikasov.winstarstest.ui.adapter.StatisticAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val profileViewModel : ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpActionsList()
        setUpStatisticList()
    }

    private fun setUpStatisticList() {
        val interaction = object : StatisticAdapter.Interaction {
            override fun onItemSelected(position: Int, item: StatisticModel) {
                Log.d("ProfileFragment", "onItemSelected: ${item.title}")
            }
        }

        val statisticAdapter = StatisticAdapter(interaction)

        profileViewModel.getStatistic().observe(viewLifecycleOwner, Observer {list ->
            statisticAdapter.submitList(list)
        })

        statisticRecycler.apply {
            adapter = statisticAdapter
        }
    }

    private fun setUpActionsList() {

        val interaction = object : ActionsAdapter.Interaction {
            override fun onItemSelected(position: Int, item: ActionModel) {
                goToAction(item.title)
            }
        }

        val actionsAdapter = ActionsAdapter(interaction)

        profileViewModel.getAllActions().observe(viewLifecycleOwner, Observer {list ->
            actionsAdapter.submitList(list)
        })

        actionsRecycler.apply {
            adapter = actionsAdapter
        }
    }

    private fun goToAction(title: String) {
        when (title) {
            resources.getString(R.string.time_tracking) -> findNavController().navigate(R.id.from_profile_to_timeTracking)
        }
    }

}