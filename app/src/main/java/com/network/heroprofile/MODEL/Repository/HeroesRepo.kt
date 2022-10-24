package com.network.heroprofile.MODEL.Repository

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class HeroesRepo () : HeroesRepoInt{

    override suspend fun getHeroes(pageNum: Int): MutableList<HeroProfile>? {
        val result = CoroutineScope(Dispatchers.IO).async {
            Executor().getHeroes(pageNum)
        }
        return result.await()
    }

    override suspend fun getHeroesByName(key:String, keyword: String, pageNum:Int): MutableList<HeroProfile>? {
        val result = CoroutineScope(Dispatchers.IO).async {
            println("--------> Hi From Repo")
            Executor().getHeroesByName(key, keyword, pageNum)
        }
        return result.await()
    }

}