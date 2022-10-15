package com.network.heroprofile.MODEL.Repository

import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile

interface HeroesRepoInt {
    suspend fun getHeroes(pageNum:Int): MutableList<HeroProfile>?
}