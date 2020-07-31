package com.nikasov.winstarstest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.nikasov.winstarstest.R
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
                    setUpTopBar(TopBarState.AUTH)
                }
                R.id.profileFragment -> {
                    setUpTopBar(TopBarState.PROFILE)
                }
                R.id.closedMessageFragment -> {
                    setUpTopBar(TopBarState.CLOSED_MESSAGE)
                }
                R.id.fromMeFragment -> {
                    setUpTopBar(TopBarState.TASK)
                }
                else -> {
                    setUpTopBar(TopBarState.EMPTY)
                    setTopTitle(destination.label.toString())
                }
            }
        }
    }

    private fun setUpTopBar(state: TopBarState) {
        when (state) {
            TopBarState.EMPTY -> {
                showStatisticArrow(false)
                applyView(R.id.main)
            }
            TopBarState.AUTH -> {
                showStatisticArrow(false)
                applyView(R.id.signIn)
            }
            TopBarState.PROFILE -> {
                mainViewModel.getProfile()
                showStatisticArrow(true)
                applyView(R.id.main)
            }
            TopBarState.TASK -> {
                showStatisticArrow(false)
                applyView(R.id.task)
            }
            TopBarState.CLOSED_MESSAGE -> {
                setTopTitle("All massage", "Closed notifications")
                applyView(R.id.main)
            }
        }
    }

    private fun setUpUI() {

        setUpToolbarMenu()
        setUpStatisticList()

        if (mainViewModel.isLogged) {
            goToProfile()
        }

        mainViewModel.profile.observe(this, Observer {
            setTopTitle(it.name)
        })

        launchSplashScreen()
    }

    private fun launchSplashScreen() {
        val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        CoroutineScope(Dispatchers.Main).launch {
            splashScreen.alpha = 1f
            delay(1000)
            setUpController()
            delay(1000)
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

    private fun goToProfile() {
        findNavController(R.id.nav_host_fragment).apply {
            popBackStack()
            navigate(R.id.to_profile)
        }
    }

    private fun showStatisticArrow(isShow : Boolean) {
        if (!isShow){
            arrow.alpha = 0f
            arrow.isEnabled = false
        }
        else {
            arrow.isEnabled = true
            arrow.alpha = 1f
            arrow.rotation = 0f
        }
    }

    private fun showExtendedTopBar(isShow : Boolean) {
        if (isShow){
            textArrows.alpha = 1f
            secondText.alpha = 1f
        } else {
            textArrows.alpha = 0f
            secondText.alpha = 0f
        }
    }

    private fun setTopTitle (title : String, secondString: String? = null) {
        if (secondString == null) {
            firstText.text = title
            showExtendedTopBar(false)
        } else {
            secondText.text = secondString
            showExtendedTopBar(true)
        }
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

    enum class TopBarState {
        EMPTY,
        AUTH,
        PROFILE,
        CLOSED_MESSAGE,
        TASK
    }
}