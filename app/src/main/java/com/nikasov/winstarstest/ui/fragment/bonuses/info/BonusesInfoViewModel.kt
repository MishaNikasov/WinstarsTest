package com.nikasov.winstarstest.ui.fragment.bonuses.info

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.local.model.bonus.BonusInfoModel
import com.nikasov.winstarstest.data.local.repository.BonusesRepository

class BonusesInfoViewModel @ViewModelInject constructor(
    private val bonusesRepository: BonusesRepository
) : ViewModel() {

    fun getBonusesExchange() : List<BonusInfoModel> {
        return bonusesRepository.getBonusInfoList()
    }

}