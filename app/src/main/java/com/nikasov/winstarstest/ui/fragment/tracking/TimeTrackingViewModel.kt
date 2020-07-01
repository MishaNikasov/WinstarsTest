package com.nikasov.winstarstest.ui.fragment.tracking

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikasov.winstarstest.data.room.model.tracking.TimeTrackingModel
import com.nikasov.winstarstest.data.room.reposiitory.RoomRepository
import kotlinx.coroutines.launch
import java.util.*

class TimeTrackingViewModel @ViewModelInject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    val trackingList : MutableLiveData<List<TimeTrackingModel>> = MutableLiveData()
    val currentDate = MutableLiveData(Date())

    fun update(dateInLong : Long) {
        val date = Date(dateInLong)
        getAllTracksByDate(date)
        currentDate.postValue(date)
    }

    fun addTimeTracking (date : Date, txt : String) {
        viewModelScope.launch {
            roomRepository.insertTimeTracking(TimeTrackingModel(date, "", txt, 5))
            getAllTracksByDate(date)
        }
    }

    private fun getAllTracksByDate(date : Date)  {
        viewModelScope.launch {
            trackingList.postValue(roomRepository.getTrackingByDate(date))
        }
    }
}