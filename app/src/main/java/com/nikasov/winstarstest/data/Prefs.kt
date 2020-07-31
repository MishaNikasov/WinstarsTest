package com.nikasov.winstarstest.data

import android.content.Context
import android.preference.PreferenceManager
import com.nikasov.winstarstest.utils.booleanLiveData
import javax.inject.Inject

class Prefs  @Inject constructor (
    context: Context
) {

    companion object {
        const val FIRST_RUN = "first_run"
    }

    private val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor = sharedPref.edit()

    fun isLogged() = sharedPref.booleanLiveData(FIRST_RUN, false)

    fun saveIsLogged() {
        editor.apply {
            putBoolean(FIRST_RUN, true)
        }.apply()
    }
}