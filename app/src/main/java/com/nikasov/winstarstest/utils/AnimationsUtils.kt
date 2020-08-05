package com.nikasov.winstarstest.utils

import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialFade

object AnimationsUtils {

    fun toggleArrow(view: View, isExpanded: Boolean): Boolean {
        return if (isExpanded) {
            view.animate().setDuration(200).rotation(180f)
            true
        } else {
            view.animate().setDuration(200).rotation(0f)
            false
        }
    }

    fun expand(view: ViewGroup) {
        val materialFade = MaterialFade().apply {
            duration = 150L
        }
        TransitionManager.beginDelayedTransition(view, materialFade)
        view.visibility = View.VISIBLE
    }

    fun collapse(view: ViewGroup) {
        val materialFade = MaterialFade().apply {
            duration = 150L
        }
        TransitionManager.beginDelayedTransition(view, materialFade)
        view.visibility = View.GONE
    }
}