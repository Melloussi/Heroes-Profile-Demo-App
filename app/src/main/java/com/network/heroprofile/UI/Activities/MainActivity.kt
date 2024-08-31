package com.network.heroprofile.UI.Activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.network.heroprofile.R
import com.network.heroprofile.UI.Fragments.MainFragment
import com.network.heroprofile.ViewModel.HeroesViewModel

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    val viewModel: HeroesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.splashScreenTheme)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

//        heroesVM = ViewModelProvider(requireActivity())[HeroesViewModel::class.java]


//        viewModel.CURRENT_SCREEN = viewModel.SEARCH_SCREEN

        fragmentSwitcher(MainFragment())

    }

    override fun onBackPressed() {

        val screen_state = viewModel.ScreenState
        println("-------> Previous Screen: ${screen_state.previous} Current Screen: ${screen_state.current}")

        when(screen_state.current){
            viewModel.PROFILE_SCREEN -> {
                if(screen_state.previous == viewModel.SEARCH_SCREEN){
                    //Back to Search
                    fragmentSwitcher(MainFragment())
                }else{
                    //Back to Main
                    fragmentSwitcher(MainFragment())
                }
            }
            viewModel.SEARCH_SCREEN -> {
                //Back to Main
                viewModel.isSearchResultActive = false
                viewModel.triggerHeroes()
            }
            viewModel.MAIN_SCREEN -> {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    return
                }

                this.doubleBackToExitPressedOnce = true
                Toast.makeText(this, "Press Back Again to Exit", Toast.LENGTH_SHORT).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    doubleBackToExitPressedOnce = false
                }, 2000)
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    fun fragmentSwitcher(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

}