package com.nikasov.winstarstest.ui.fragment.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.winstarstest.data.local.ProfileRepository
import com.nikasov.winstarstest.data.room.model.profile.Profile
import com.nikasov.winstarstest.data.room.reposiitory.RoomRepository
import kotlinx.coroutines.launch

class SignInViewModel @ViewModelInject constructor(
    private val profileRepository: ProfileRepository,
    private val roomRepository: RoomRepository
) : ViewModel(){

    val profile : MutableLiveData<Profile> = MutableLiveData()

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