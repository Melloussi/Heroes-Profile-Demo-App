package com.network.heroprofile.ViewModel

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Parcelable
import androidx.lifecycle.*
import com.network.heroprofile.BuildConfig
import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import com.network.heroprofile.MODEL.Repository.HeroesRepo
import kotlinx.coroutines.launch

class HeroesViewModel() : ViewModel() {

    private val repo = HeroesRepo()
    private val allHeroes = MutableLiveData<MutableList<HeroProfile>>()
    val getAll = allHeroes
    private val _defaultDataKeeper = ArrayList<HeroProfile>()
    private val _searchDataKeeper = ArrayList<HeroProfile>()
    private var pageNum:Int = 0
    private var _state: Parcelable? = null
    private var _reachEnd = false
    private var _keyword = ""

//    private val ai: ApplicationInfo = application.packageManager
//        .getApplicationInfo(application.packageName, PackageManager.GET_META_DATA)
//    private val value = ai.metaData["keyValue"]
//    private val key = value.toString()

    private val key = BuildConfig.KEY



    fun getPage():Int{
        pageNum++
        return pageNum
    }

    val reachEnd get() = _reachEnd


    var state: Parcelable?
    get() = _state
    set(value) { _state = value }

    fun getHeroes(){
        viewModelScope.launch {
            val result = repo.getHeroes(pageNum)
            if(result != null && result.isNotEmpty()){
                _defaultDataKeeper.addAll(result)
                getAll.value = _defaultDataKeeper
            }else{
                _reachEnd = true
            }
        }
    }

    fun getHeroesByName(keyword:String){
        viewModelScope.launch {
            println("--------> Hi From ViewModel")
            val result = repo.getHeroesByName(key, keyword, pageNum)

            if(result != null && result.isNotEmpty()){
                if(_keyword !== keyword){
                    _searchDataKeeper.clear()
                    pageNum = 0
                }
                _searchDataKeeper.addAll(result)
                getAll.value = _searchDataKeeper
                println("------> Search List: $_searchDataKeeper")
            }else{
                _reachEnd = true
            }
        }
    }



}