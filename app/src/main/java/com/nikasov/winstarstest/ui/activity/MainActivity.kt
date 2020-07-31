package com.nikasov.winstarstest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.common.Settings
import com.nikasov.winstarstest.data.local.model.StatisticModel
import com.nikasov.winstarstest.ui.adapter.StatisticAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        if (checkIsLogged()) {
            goToProfile()
        }

        mainViewModel.profile.observe(this, Observer {
            setTopTitle(it.name)
        })
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
}