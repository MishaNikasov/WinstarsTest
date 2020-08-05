package com.nikasov.winstarstest.ui.fragment.bonuses.exchange

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.winstarstest.data.local.model.BonusExchangeModel
import com.nikasov.winstarstest.data.local.repository.BonusesRepository

class BonusesExchangeViewModel @ViewModelInject constructor(
    private val bonusesRepository: BonusesRepository
) : ViewModel() {

    fun getBonusesExchange() : List<BonusExchangeModel> {
        return bonusesRepository.getBonusesExchangeList()
    }

}