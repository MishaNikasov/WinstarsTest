package com.nikasov.winstarstest.ui.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.local.ProfileRepository

class MainViewModel @ViewModelInject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    fun getStatistic() = profileRepository.getStatistic()

}