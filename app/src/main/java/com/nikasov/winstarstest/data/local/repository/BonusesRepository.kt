package com.nikasov.winstarstest.data.local.repository

import android.content.Context
import com.nikasov.winstarstest.data.local.model.BonusExchangeModel
import com.nikasov.winstarstest.data.local.model.BonusModel
import com.nikasov.winstarstest.data.local.model.FeedbackStatModel
import java.util.*
import javax.inject.Inject

class BonusesRepository @Inject constructor (
    private var application: Context
) {

    fun getBonusesList() : List<BonusModel> {
        val list = arrayListOf<BonusModel>()

        list.apply {
            add(BonusModel(
                    Calendar.getInstance().time,
                    -400,
                    "Абонемент в спортзал",
                    true))
            add(BonusModel(
                    Calendar.getInstance().time,
                    -800,
                    "Абонемент в спортзал",
                    false))
            add(BonusModel(
                    Calendar.getInstance().time,
                    800,
                    "Абонемент в спортзал",
                false))
            add(BonusModel(
                    Calendar.getInstance().time,
                    800,
                    "Абонемент в спортзал",
                false))
        }

        return list
    }

    fun getBonusesExchangeList() : List<BonusExchangeModel> {
        val list = arrayListOf<BonusExchangeModel>()

        list.apply {
            add(BonusExchangeModel(
                400,
                "Bonus name number 1"
            ))
            add(BonusExchangeModel(
                400,
                "Bonus name number 2"
            ))
            add(BonusExchangeModel(
                400,
                "Bonus name number 3"
            ))
            add(BonusExchangeModel(
                400,
                "Bonus name number 4"
            ))
            add(BonusExchangeModel(
                400,
                "Bonus name number 5"
            ))
            add(BonusExchangeModel(
                400,
                "Bonus name number 6"
            ))
            add(BonusExchangeModel(
                400,
                "Bonus name number 7"
            ))
            add(BonusExchangeModel(
                400,
                "Bonus name number 8"
            ))
        }

        return list
    }
}