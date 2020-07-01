package com.nikasov.winstarstest.ui.fragment.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.data.ProfileActionModel
import com.nikasov.winstarstest.ui.fragment.adapter.ActionsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val profileViewModel : ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpActionsList()
    }

    private fun setUpActionsList() {

        val interaction = object : ActionsAdapter.Interaction {
            override fun onItemSelected(position: Int, item: ProfileActionModel) {
                Log.d("ProfileFragment", "onItemSelected: ${item.title}")
            }
        }

        val actionsAdapter = ActionsAdapter(interaction)

        profileViewModel.getAllActions().observe(viewLifecycleOwner, Observer {list ->
            actionsAdapter.submitList(list)
        })

        profileRecycler.apply {
            adapter = actionsAdapter
        }
    }

}