package com.nikasov.winstarstest.ui.fragment.message

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.Prefs
import com.nikasov.winstarstest.data.local.repository.ProfileRepository
import com.nikasov.winstarstest.data.local.model.MessageModel
import com.nikasov.winstarstest.data.local.repository.MessageRepository

class AllMessageViewModel @ViewModelInject constructor(
    private val messageRepository: MessageRepository,
    private val prefs: Prefs
) : ViewModel(){

    val messages = MutableLiveData<List<MessageModel>>()

    fun getMessages() {
        messages.postValue(messageRepository.getAllMessages())
    }

}