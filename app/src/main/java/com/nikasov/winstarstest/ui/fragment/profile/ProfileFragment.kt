package com.nikasov.winstarstest.ui.fragment.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.local.model.ActionModel
import com.nikasov.winstarstest.ui.adapter.ActionsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val profileViewModel : ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exitTransition = MaterialFadeThrough()

        setUpActionsList()
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
            resources.getString(R.string.time_tracking) -> findNavController().navigate(R.id.toTimeTracking)
            resources.getString(R.string.tasks) -> findNavController().navigate(R.id.toTaskFragment)
            resources.getString(R.string.day_off) -> findNavController().navigate(R.id.toDayOffFragment)
            resources.getString(R.string.job_offer) -> findNavController().navigate(R.id.toJobOfferFragment)
            resources.getString(R.string.bonuses) -> findNavController().navigate(R.id.toBonusesFragment)
//            resources.getString(R.string.winstars_open) -> findNavController().navigate(R.id.)
            resources.getString(R.string.feedback) -> findNavController().navigate(R.id.toFeedbackFragment)
        }
    }

}