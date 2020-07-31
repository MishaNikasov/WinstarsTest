package com.nikasov.winstarstest.ui.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.winstarstest.data.Prefs
import com.nikasov.winstarstest.data.local.ProfileRepository
import com.nikasov.winstarstest.data.room.model.profile.Profile
import com.nikasov.winstarstest.data.room.reposiitory.RoomRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel @ViewModelInject constructor(
    private val profileRepository: ProfileRepository,
    private val prefs: Prefs,
    private val roomRepository: RoomRepository
) : ViewModel() {

    val profile : MutableLiveData<Profile> = MutableLiveData()
    val splashScreen : MutableLiveData<Boolean> = MutableLiveData()

    val isLogged = prefs.isLogged().value!!

    fun getStatistic() = profileRepository.getStatistic()

    fun getProfile() {
        viewModelScope.launch {
            profile.postValue(roomRepository.getProfile())
        }
    }

    fun launchSplashScreen() {
        viewModelScope.launch {
            splashScreen.postValue(true)
            delay(2000)
            splashScreen.postValue(false)
        }
    }
}