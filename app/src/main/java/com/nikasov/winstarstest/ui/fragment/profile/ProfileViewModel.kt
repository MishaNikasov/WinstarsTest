package com.nikasov.winstarstest.ui.fragment.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.ProfileRepository

class ProfileViewModel @ViewModelInject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun getAllActions() = profileRepository.getAllProfileActions()

    fun getStatistic() = profileRepository.getStatistic()

}