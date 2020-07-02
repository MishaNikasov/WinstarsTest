package com.nikasov.winstarstest.ui.fragment.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nikasov.winstarstest.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        signBtn.setOnClickListener {
            goToProfile()
        }
    }

    fun goToProfile() {
        findNavController().navigate(R.id.from_signUp_to_profile)
    }

}