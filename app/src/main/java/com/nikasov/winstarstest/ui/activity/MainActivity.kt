package com.nikasov.winstarstest.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.common.Constants.RC_SIGN_IN
import com.nikasov.winstarstest.common.Settings
import com.nikasov.winstarstest.data.local.model.StatisticModel
import com.nikasov.winstarstest.ui.adapter.StatisticAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        setUpStatisticList()

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signUpFragment -> {
                    disableStatistic(true)
                    setTopTitle(destination.label.toString())
                    applyView(R.id.signIn)
                }
                R.id.profileFragment -> {
                    mainViewModel.getProfile()
                    setTopTitle(Settings.USER_NAME)
                    disableStatistic(false)
                    applyView(R.id.main)
                }
                else -> {
                    setTopTitle(destination.label.toString())
                    disableStatistic(true)
                    applyView(R.id.main)
                }
            }
        }

        mainViewModel.profile.observe(this, Observer {
            setTopTitle(it.name)
        })
    }

    private fun applyView(id : Int) {
        root.transitionToState(id)
    }

    private fun disableStatistic(isHide : Boolean) {
        if (isHide)
            arrow.alpha = 0f
        else {
            arrow.alpha = 1f
            arrow.rotation = 0f
        }
    }

    private fun setTopTitle (title : String) {
        nameText.text = title
    }

    private fun setUpStatisticList() {
        val interaction = object : StatisticAdapter.Interaction {
            override fun onItemSelected(position: Int, item: StatisticModel) {
                Log.d("ProfileFragment", "onItemSelected: ${item.title}")
            }
        }

        val statisticAdapter = StatisticAdapter(interaction)

        mainViewModel.getStatistic().observe(this, Observer {list ->
            statisticAdapter.submitList(list)
        })
        statisticRecycler.apply {
            adapter = statisticAdapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.d("TAG", "onActivityResult: ${e.localizedMessage}")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    mainViewModel.saveProfile(auth.currentUser?.displayName.toString())
                    mainViewModel.getProfile()
                    findNavController(R.id.nav_host_fragment).navigate(R.id.from_signUp_to_profile)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        user?.let {
            try {
//                findNavController(R.id.nav_host_fragment).navigate(R.id.from_signUp_to_profile)
            } catch (e : Exception) {

            }
        }
    }
}