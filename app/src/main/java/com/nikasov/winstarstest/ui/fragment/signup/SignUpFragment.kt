package com.nikasov.winstarstest.ui.fragment.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.common.Constants
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        request()
        initUi()
    }

    private fun initUi() {
        signBtn.setOnClickListener {
            signIn()
        }
    }

    private lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var auth : FirebaseAuth

    private fun request() {
        auth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        signBtn.isEnabled = false
        requireActivity().startActivityForResult(signInIntent, Constants.RC_SIGN_IN)
    }

}