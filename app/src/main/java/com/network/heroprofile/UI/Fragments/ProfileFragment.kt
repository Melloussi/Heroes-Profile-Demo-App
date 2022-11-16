package com.network.heroprofile.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import com.network.heroprofile.MODEL.DATA.DataClases.Screen
import com.network.heroprofile.R
import com.network.heroprofile.UI.Activities.MainActivity
import com.network.heroprofile.ViewModel.HeroesViewModel
import com.network.heroprofile.ViewModel.ProfileViewModel
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    private var index = 0

    private lateinit var intelligence_progress: CircularProgressBar
    private lateinit var strength_progress: CircularProgressBar
    private lateinit var speed_progress: CircularProgressBar
    private lateinit var durability_progress: CircularProgressBar
    private lateinit var power_progress: CircularProgressBar
    private lateinit var combat_progress: CircularProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val heroProfileVM = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        val heroesVM = ViewModelProvider(requireActivity())[HeroesViewModel::class.java]

        val profile_Screen = heroesVM.PROFILE_SCREEN
        val previous_Screen = heroesVM.ScreenState.current
        //set New State
        heroesVM.ScreenState = Screen(previous_Screen, profile_Screen)

        val heroName: TextView = view.findViewById(R.id.heroName)
        val profileImage: ImageView = view.findViewById(R.id.profileImage)
        val fav: ImageView = view.findViewById(R.id.fav)
        val back: ImageView = view.findViewById(R.id.back)
        intelligence_progress = view.findViewById(R.id.intelligence_progress)
        strength_progress = view.findViewById(R.id.strength_progress)
        speed_progress = view.findViewById(R.id.speed_progress)
        durability_progress = view.findViewById(R.id.durability_progress)
        power_progress = view.findViewById(R.id.power_progress)
        combat_progress = view.findViewById(R.id.combat_progress)


        val data = heroProfileVM.profileData


        //data.biography.fullName
        heroName.text = data.name
        Picasso.get().load(data.images.sm).into(profileImage)
        progressState(data)

        back.setOnClickListener(View.OnClickListener {
//            heroProfileVM.isBackFromProfile = true
            (activity as MainActivity?)?.fragmentSwitcher(MainFragment())
        })


        return view
    }

    private fun progressState(data: HeroProfile) {
        intelligence_progress.apply {
            setProgressWithAnimation(data.powerstats.intelligence.toFloat(), 3000)
            roundBorder = true
            progressMax = 100f
        }
        strength_progress.apply {
            setProgressWithAnimation(data.powerstats.strength.toFloat(), 3000)
            roundBorder = true
            progressMax = 100f
        }
        speed_progress.apply {
            setProgressWithAnimation(data.powerstats.speed.toFloat(), 3000)
            roundBorder = true
            progressMax = 100f
        }
        durability_progress.apply {
            setProgressWithAnimation(data.powerstats.durability.toFloat(), 3000)
            roundBorder = true
            progressMax = 100f
        }
        power_progress.apply {
            setProgressWithAnimation(data.powerstats.power.toFloat(), 3000)
            roundBorder = true
            progressMax = 100f
        }
        combat_progress.apply {
            setProgressWithAnimation(data.powerstats.combat.toFloat(), 3000)
            roundBorder = true
            progressMax = 100f
        }
    }
}