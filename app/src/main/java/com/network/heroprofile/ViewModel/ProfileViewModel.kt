package com.network.heroprofile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile

class ProfileViewModel: ViewModel() {

    private lateinit var _getProfileData:HeroProfile
    var isBackFromProfile = false

    var profileData
    get() = _getProfileData
    set(value) {
        _getProfileData = value
    }



//    fun profileData(data:HeroProfile){
//        _getProfileData.value = data
//        println("-------------> I Sent to you The Data$ $data")
//        println("-------------> ${_getProfileData.value}")
//        println("-------------> ${profileData}")
//    }


}