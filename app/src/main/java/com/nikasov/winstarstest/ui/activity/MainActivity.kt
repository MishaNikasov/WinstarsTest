package com.nikasov.winstarstest.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.nikasov.winstarstest.R
import com.nikasov.winstarstest.common.Settings
import com.nikasov.winstarstest.data.local.model.StatisticModel
import com.nikasov.winstarstest.ui.adapter.StatisticAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//todo: 3 стейт который тригерится после авторизации
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpStatisticList()

        checkIsLogged()
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.signUpFragment -> {
                    root.transitionToState(R.id.signIn)
                    setTopTitle(destination.label.toString())
                    arrow.alpha = 0f
                }
                R.id.profileFragment -> {
                    root.transitionToState(R.id.start)
                    setTopTitle(destination.label.toString())
                    arrow.alpha = 1f
                    arrow.rotation = 0f
                }
                else -> {
                    root.transitionToState(R.id.start)
                    setTopTitle(destination.label.toString())
                    arrow.alpha = 0f
                }
            }
        }

        root.setTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}
            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}
            override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                
            }

        })
    }

    private fun checkIsLogged() {
        if (!Settings.IS_LOGGED_IN){
            findNavController(R.id.nav_host_fragment).navigate(R.id.from_signUp_to_profile)
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

}