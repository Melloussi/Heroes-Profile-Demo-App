package com.network.heroprofile.MODEL.Repository

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

}