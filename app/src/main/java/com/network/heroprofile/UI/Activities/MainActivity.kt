package com.network.heroprofile.UI.Activities

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.network.heroprofile.UI.Adapter.HerosAdapter
import com.network.heroprofile.MODEL.DATA.DataClases.Hero
import com.network.heroprofile.R
import com.network.heroprofile.UI.Fragments.MainFragment
import com.network.heroprofile.UI.Fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentSwitcher(MainFragment())

    }
    fun fragmentSwitcher(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }

}