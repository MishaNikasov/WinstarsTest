package com.nikasov.winstarstest.ui.fragment.tasks.fromme

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.Prefs
import com.nikasov.winstarstest.data.local.repository.MessageRepository

class FromMeViewModel @ViewModelInject constructor(
    private val messageRepository: MessageRepository,
    private val prefs: Prefs
) : ViewModel(){



}