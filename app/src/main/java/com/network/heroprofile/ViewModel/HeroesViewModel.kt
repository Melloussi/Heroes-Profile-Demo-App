package com.network.heroprofile.ViewModel

import android.os.Parcelable
import androidx.lifecycle.*
import com.network.heroprofile.BuildConfig
import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import com.network.heroprofile.MODEL.DATA.DataClases.Screen
import com.network.heroprofile.MODEL.Repository.HeroesRepo
import kotlinx.coroutines.launch

class HeroesViewModel() : ViewModel() {

    private val repo = HeroesRepo()
    private val allHeroes = MutableLiveData<MutableList<HeroProfile>>()
    val getAll = allHeroes
    private val _defaultDataKeeper = ArrayList<HeroProfile>()
    private val _searchDataKeeper = ArrayList<HeroProfile>()
    private var mainPageNum:Int = 0
    private var searchPageNum:Int = 0
    private var _state: Parcelable? = null
    private var _reachEnd = false
    private var _keyword = ""
    private var _isSearchResultActive = false
    var isSearchResultActive
    get() = _isSearchResultActive
    set(value) {_isSearchResultActive = value}




//    private var _prvious_screen = 0
//    var PREVIOUS_SCREEN
//    get() = _prvious_screen
//    set(value) {_prvious_screen = value}

    val MAIN_SCREEN = 0
    val SEARCH_SCREEN = 1
    val PROFILE_SCREEN = 2

    private var _screen_state = Screen(MAIN_SCREEN, MAIN_SCREEN)
    var ScreenState
    get() = _screen_state
    set(value) {_screen_state = value}



    private val key = BuildConfig.KEY



//    fun getPage():Int{
//        pageNum++
//        return pageNum
//    }

    fun triggerHeroes(){

        if(_isSearchResultActive){
            _screen_state = Screen(MAIN_SCREEN, SEARCH_SCREEN)
            getAll.value = _searchDataKeeper
        }else{
            getAll.value = _defaultDataKeeper
            _screen_state = Screen(MAIN_SCREEN, MAIN_SCREEN)
        }
    }

    fun inCreaseMainPage(){
        mainPageNum++
    }
    fun inCreaseSearchPage(){
        searchPageNum++
    }

    val reachEnd get() = _reachEnd


    var state: Parcelable?
    get() = _state
    set(value) { _state = value }

    fun getHeroes(){
        viewModelScope.launch {
            val result = repo.getHeroes(mainPageNum)
            if(result != null && result.isNotEmpty()){
                _defaultDataKeeper.addAll(result)
                getAll.value = _defaultDataKeeper
                _isSearchResultActive = false
//                _prvious_screen = MAIN_SCREEN
                _screen_state = Screen(MAIN_SCREEN, MAIN_SCREEN)
            }else{
                _reachEnd = true
            }
        }
    }

    fun getHeroesByName(keyword:String){
        viewModelScope.launch {
            println("--------> Hi From ViewModel")
            val result = repo.getHeroesByName(key, keyword, searchPageNum)

            if(result != null && result.isNotEmpty()){
                if(_keyword !== keyword){
                    _searchDataKeeper.clear()
                    searchPageNum = 0
                }
                _searchDataKeeper.addAll(result)
                getAll.value = _searchDataKeeper
                _isSearchResultActive = true
//                _prvious_screen = SEARCH_SCREEN
                _screen_state = Screen(MAIN_SCREEN, SEARCH_SCREEN)
                println("------> Search List: $_searchDataKeeper")
            }else{
                _reachEnd = true
            }
        }
    }



}