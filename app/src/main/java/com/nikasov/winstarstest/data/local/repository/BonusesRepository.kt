package com.nikasov.winstarstest.data.local.repository

import android.content.Context
import com.nikasov.winstarstest.data.local.model.bonus.BonusExchangeModel
import com.nikasov.winstarstest.data.local.model.bonus.BonusInfoModel
import com.nikasov.winstarstest.data.local.model.bonus.BonusModel
import java.util.*
import javax.inject.Inject

class BonusesRepository @Inject constructor (
    private var application: Context
) {

    fun getBonusesList() : List<BonusModel> {
        val list = arrayListOf<BonusModel>()

        list.apply {
            add(
                BonusModel(
                    Calendar.getInstance().time,
                    -400,
                    "Абонемент в спортзал",
                    true
                )
            )
            add(
                BonusModel(
                    Calendar.getInstance().time,
                    -800,
                    "Абонемент в спортзал",
                    false
                )
            )
            add(
                BonusModel(
                    Calendar.getInstance().time,
                    800,
                    "Абонемент в спортзал",
                    false
                )
            )
            add(
                BonusModel(
                    Calendar.getInstance().time,
                    800,
                    "Абонемент в спортзал",
                    false
                )
            )
        }

        return list
    }

    fun getBonusesExchangeList() : List<BonusExchangeModel> {
        val list = arrayListOf<BonusExchangeModel>()

        list.apply {
            add(
                BonusExchangeModel(
                    400,
                    "Bonus name number 1"
                )
            )
            add(
                BonusExchangeModel(
                    400,
                    "Bonus name number 2"
                )
            )
            add(
                BonusExchangeModel(
                    400,
                    "Bonus name number 3"
                )
            )
            add(
                BonusExchangeModel(
                    400,
                    "Bonus name number 4"
                )
            )
            add(
                BonusExchangeModel(
                    400,
                    "Bonus name number 5"
                )
            )
            add(
                BonusExchangeModel(
                    400,
                    "Bonus name number 6"
                )
            )
            add(
                BonusExchangeModel(
                    400,
                    "Bonus name number 7"
                )
            )
            add(
                BonusExchangeModel(
                    400,
                    "Bonus name number 8"
                )
            )
        }

        return list
    }

    fun getBonusInfoList(): List<BonusInfoModel>{
        val bonusInfoModelList = arrayListOf<BonusInfoModel>()

        bonusInfoModelList.apply {
            add(BonusInfoModel(
                400,
                "For early delivery of the project",
                "Something very important needs to be done if the work is to be appreciated."
            ))
            add(BonusInfoModel(
                400,
                "For early delivery of the project",
                "Something very important needs to be done if the work is to be appreciated."
            ))
            add(BonusInfoModel(
                400,
                "For early delivery of the project",
                "Something very important needs to be done if the work is to be appreciated."
            ))
            add(BonusInfoModel(
                400,
                "For early delivery of the project",
                "Something very important needs to be done if the work is to be appreciated."
            ))
            add(BonusInfoModel(
                400,
                "For early delivery of the project",
                "Something very important needs to be done if the work is to be appreciated."
            ))
            add(BonusInfoModel(
                400,
                "For early delivery of the project",
                "Something very important needs to be done if the work is to be appreciated."
            ))
        }

        return bonusInfoModelList
    }
}