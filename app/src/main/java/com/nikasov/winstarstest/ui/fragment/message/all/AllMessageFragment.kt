package com.nikasov.winstarstest.ui.fragment.message.all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.MessageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_message.*

@AndroidEntryPoint
class AllMessageFragment : Fragment(R.layout.fragment_all_message) {

    private val allMessageViewModel : AllMessageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()
        setUpUI()
    }

    private fun setUpUI() {
        forwardArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }

        setUpList()
    }

    private fun setUpList() {

        allMessageViewModel.getMessages()

        val messageAdapter = MessageAdapter()
        messagesRecycler.apply {
            adapter = messageAdapter
        }

        allMessageViewModel.messages.observe(viewLifecycleOwner, Observer { list ->
            messageAdapter.submitList(list)
        })

        closedNotifications.setOnClickListener {
            findNavController().navigate(R.id.action_allMessageFragment_to_closedMessageFragment)
        }
    }
}