package com.nikasov.winstarstest.ui.fragment.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.nikasov.winstarstest.data.Prefs
import com.nikasov.winstarstest.data.local.repository.ProfileRepository
import com.nikasov.winstarstest.data.room.model.profile.Profile
import com.nikasov.winstarstest.data.room.reposiitory.RoomRepository
import com.nikasov.winstarstest.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel @ViewModelInject constructor(
    private val auth : FirebaseAuth,
    private val profileRepository: ProfileRepository,
    private val prefs: Prefs,
    private val roomRepository: RoomRepository
) : ViewModel(){

    var currentUser : MutableLiveData<Resource<String>> = MutableLiveData()
    val profile : MutableLiveData<Profile> = MutableLiveData()

    private fun saveIsLogged() {
        prefs.saveIsLogged()
    }

    private fun saveProfile(name: String) {
        val profile = Profile(name)
        viewModelScope.launch {
            roomRepository.insertProfile(profile)
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            profile.postValue(roomRepository.getProfile())
        }
    }

    fun signInWithGoogle (googleAuthCredential : AuthCredential) {
        currentUser.postValue(Resource.Loading())
        viewModelScope.launch {
            auth.signInWithCredential(googleAuthCredential).await()
            saveProfile(auth.currentUser?.displayName.toString())
            getProfile()
            saveIsLogged()
            currentUser.postValue(Resource.Empty())
        }
    }

}