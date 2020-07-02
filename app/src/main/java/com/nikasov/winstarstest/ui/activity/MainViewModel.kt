package com.nikasov.winstarstest.ui.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.winstarstest.data.local.ProfileRepository
import com.nikasov.winstarstest.data.room.model.profile.Profile
import com.nikasov.winstarstest.data.room.reposiitory.RoomRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val profileRepository: ProfileRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

    val profile : MutableLiveData<Profile> = MutableLiveData()

    fun getStatistic() = profileRepository.getStatistic()

    fun saveProfile(name: String) {
        val profile = Profile(name)
        viewModelScope.launch {
            roomRepository.insertProfile(profile)
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            profile.postValue(roomRepository.getProfile())
        }
    }
}