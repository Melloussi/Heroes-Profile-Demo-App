package com.network.heroprofile.UI.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.network.heroprofile.UI.Adapter.HerosAdapter
import com.network.heroprofile.MODEL.DATA.DataClases.Hero
import com.network.heroprofile.R
import com.network.heroprofile.UI.Fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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