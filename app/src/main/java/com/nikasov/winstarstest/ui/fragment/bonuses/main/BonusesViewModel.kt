package com.nikasov.winstarstest.ui.fragment.bonuses.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.local.model.BonusModel
import com.nikasov.winstarstest.data.local.repository.BonusesRepository

class BonusesViewModel @ViewModelInject constructor(
    private val bonusesRepository: BonusesRepository
) : ViewModel() {

    fun getBonuses() : List<BonusModel>{
        return bonusesRepository.getBonusesList()
    }

}