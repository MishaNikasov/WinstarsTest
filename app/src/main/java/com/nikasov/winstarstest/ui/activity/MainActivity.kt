package com.nikasov.winstarstest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.common.Settings
import com.nikasov.winstarstest.data.local.model.StatisticModel
import com.nikasov.winstarstest.ui.adapter.StatisticAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
    }

    private fun setUpController() {
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signUpFragment -> {
                    setTopTitle(destination.label.toString())
                    setUpTopBar(TopBarState.EMPTY)
                    applyView(R.id.signIn)
                }
                R.id.profileFragment -> {
                    mainViewModel.getProfile()
                    setTopTitle(Settings.USER_NAME)
                    setUpTopBar(TopBarState.PROFILE)
                    applyView(R.id.main)
                }
                else -> {
                    setTopTitle(destination.label.toString())
                    setUpTopBar(TopBarState.EMPTY)
                    applyView(R.id.main)
                }
            }
        }
    }

    private fun setUpTopBar(state: TopBarState) {
        when (state) {
            TopBarState.EMPTY -> {
                showStatisticArrow(false)
            }
            TopBarState.PROFILE -> {
                showStatisticArrow(true)
            }
        }

    }

    private fun setUpUI() {

        setUpToolbarMenu()
        setUpStatisticList()

        launchSplashScreen()

        if (checkIsLogged()) {
            goToProfile()
        }

        mainViewModel.profile.observe(this, Observer {
            setTopTitle(it.name)
        })
    }

    private fun launchSplashScreen() {
        val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        CoroutineScope(Dispatchers.Main).launch {
            splashScreen.alpha = 1f
            delay(1000)
            setUpController()
            delay(500)
            splashScreen.startAnimation(fadeOutAnimation)
            delay(250)
            splashScreen.alpha = 0f
        }
    }

    private fun setUpToolbarMenu() {
        notifications.setOnClickListener {
            findNavController(R.id.nav_host_fragment).apply {
                navigate(R.id.to_all_message)
            }
        }
    }

    private fun applyView(id : Int) {
        root.transitionToState(id)
    }

    private fun checkIsLogged(): Boolean {
        return mainViewModel.isLogged
    }

    private fun goToProfile() {
        findNavController(R.id.nav_host_fragment).apply {
            popBackStack()
            navigate(R.id.to_profile)
        }
    }

    private fun showStatisticArrow(isShow : Boolean) {
        if (!isShow)
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
                Timber.d("onItemSelected: ${item.title}")
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

    fun loginState(isLogin: Boolean) {
        if (isLogin) {
            winstarsBigLogo.visibility = View.VISIBLE
            descriptionText.visibility = View.VISIBLE
            toolbar.visibility = View.INVISIBLE
        } else {
            winstarsBigLogo.visibility = View.INVISIBLE
            descriptionText.visibility = View.INVISIBLE
            toolbar.visibility = View.VISIBLE
        }
    }

    enum class TopBarState {
        EMPTY,
        PROFILE,
    }
}