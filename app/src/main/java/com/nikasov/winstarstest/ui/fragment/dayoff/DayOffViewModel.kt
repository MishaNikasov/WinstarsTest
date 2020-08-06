package com.nikasov.winstarstest.ui.fragment.dayoff

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.local.model.DayOffModel
import com.nikasov.winstarstest.data.local.repository.DayOffRepository

class DayOffViewModel @ViewModelInject constructor(
    private val dayOffRepository: DayOffRepository
) : ViewModel() {

    fun getDayOffList(): List<DayOffModel> {
        return dayOffRepository.getDayOffList()
    }

}