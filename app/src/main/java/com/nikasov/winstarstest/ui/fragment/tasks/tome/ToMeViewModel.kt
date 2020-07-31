package com.nikasov.winstarstest.ui.fragment.tasks.tome

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.Prefs
import com.nikasov.winstarstest.data.local.repository.ProfileRepository
import com.nikasov.winstarstest.data.local.model.MessageModel
import com.nikasov.winstarstest.data.local.repository.MessageRepository

class ToMeViewModel @ViewModelInject constructor(
    private val messageRepository: MessageRepository,
    private val prefs: Prefs
) : ViewModel(){



}