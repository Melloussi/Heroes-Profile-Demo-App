package com.network.heroprofile.UI.Activities

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.network.heroprofile.UI.Adapter.HerosAdapter
import com.network.heroprofile.MODEL.DATA.DataClases.Hero
import com.network.heroprofile.R
import com.network.heroprofile.UI.Fragments.MainFragment
import com.network.heroprofile.UI.Fragments.ProfileFragment
import com.network.heroprofile.ViewModel.HeroesViewModel

class MainActivity : AppCompatActivity() {

    val viewModel: HeroesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        heroesVM = ViewModelProvider(requireActivity())[HeroesViewModel::class.java]


//        viewModel.CURRENT_SCREEN = viewModel.SEARCH_SCREEN

        fragmentSwitcher(MainFragment())

    }

    override fun onBackPressed() {
//        super.onBackPressed()
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
                //Press Back Twice to Exit
                Toast.makeText(this, "Press Back Twice to Exit", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun fragmentSwitcher(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

}