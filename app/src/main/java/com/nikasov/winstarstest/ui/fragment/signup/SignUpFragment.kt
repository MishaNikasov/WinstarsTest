package com.nikasov.winstarstest.ui.fragment.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.common.Constants
import com.nikasov.winstarstest.common.Constants.RC_SIGN_IN
import com.nikasov.winstarstest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val signInViewModel : SignInViewModel by viewModels()

    @Inject
    lateinit var signInOption : GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        signBtn.setOnClickListener {
            signInByGoogle()
        }

        signInViewModel.currentUser.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    loading(true)
                }
                is Resource.Empty -> {
                    loading(false)
                    goToProfile()
                }
                is Resource.Error -> {
                    loading(false)
                }
            }
        })
    }

    private fun signInByGoogle() {
        signBtn.isEnabled = false
        signInOption.signInIntent.also {
            startActivityForResult(it, RC_SIGN_IN)
        }
    }

    private fun goToProfile() {
        findNavController().apply {
            popBackStack()
            navigate(R.id.to_profile)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.RC_SIGN_IN) {
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
                account?.let {
                    googleAuthToFirebase(it)
                }
            } catch (e : Exception) {
                Timber.d(e.localizedMessage)
            }
        }
    }

    private fun googleAuthToFirebase(signInAccount: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
        signInViewModel.signInWithGoogle(credentials)
    }

    private fun loading (isShow : Boolean) {
        if (isShow) {
            loadingScreen.visibility = View.VISIBLE
        } else {
            loadingScreen.visibility = View.INVISIBLE
        }
    }

}