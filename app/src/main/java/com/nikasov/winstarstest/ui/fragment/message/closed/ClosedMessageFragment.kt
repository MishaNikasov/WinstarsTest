package com.nikasov.winstarstest.ui.fragment.message.closed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.transition.MaterialFadeThrough
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.ui.adapter.MessageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_closed_message.*

@AndroidEntryPoint
class ClosedMessageFragment : Fragment(R.layout.fragment_closed_message) {

    private val closedMessageViewModel : ClosedMessageViewModel by viewModels()

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

        closedMessageViewModel.getMessages()

        val messageAdapter = MessageAdapter()
        messagesRecycler.apply {
            adapter = messageAdapter
        }

        closedMessageViewModel.messages.observe(viewLifecycleOwner, Observer { list ->
            messageAdapter.submitList(list)
        })
    }
}