package com.network.heroprofile.MODEL.DATA.Retrofit

import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import retrofit2.Response
import retrofit2.http.*

interface RequestInt {

    @GET("/heros")
    //@Headers({"pagination":""})
    suspend fun getHeroes(@Header ("pagination") pageNum:Int):Response<MutableList<HeroProfile>>
}