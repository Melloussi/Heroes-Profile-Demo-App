package com.network.heroprofile.MODEL.Repository

import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import com.network.heroprofile.MODEL.DATA.Retrofit.RetrofitInstance
import okio.IOException
import retrofit2.Response

class Executor : HeroesRepoInt{

    override suspend fun getHeroes(pageNum: Int): MutableList<HeroProfile>? {
        val response: Response<MutableList<HeroProfile>>? = try {
            RetrofitInstance.api.getHeroes(pageNum)
        }catch (exception:IOException){
            exception.printStackTrace()
            null
        }

        return if (response?.isSuccessful == true && response.body() != null) response.body() else null
    }
}