package com.network.heroprofile.ViewModel

import android.os.Parcelable
import androidx.lifecycle.*
import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import com.network.heroprofile.MODEL.Repository.HeroesRepo
import kotlinx.coroutines.launch

class HeroesViewModel() : ViewModel() {

    private val repo = HeroesRepo()
    private val allHeroes = MutableLiveData<MutableList<HeroProfile>>()
    val getAll = allHeroes
    private val _dataKeeper = ArrayList<HeroProfile>()
    private var pageNum:Int = 0
    private var _state: Parcelable? = null
    private var _reachEnd = false




    fun getPage():Int{
        pageNum++
        return pageNum
    }

    val reachEnd get() = _reachEnd


    var state: Parcelable?
    get() = _state
    set(value) { _state = value }

    fun getHeroes(pageNum:Int){
        viewModelScope.launch {
            val result = repo.getHeroes(pageNum)
            if(result != null && result.isNotEmpty()){
                _dataKeeper.addAll(result)
                getAll.value = _dataKeeper
            }else{
                _reachEnd = true
            }
        }
    }



}