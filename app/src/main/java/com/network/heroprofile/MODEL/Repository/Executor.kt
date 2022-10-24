package com.network.heroprofile.MODEL.Repository

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.network.heroprofile.MODEL.DATA.DataClases.HeroProfile
import com.network.heroprofile.MODEL.DATA.Retrofit.RetrofitInstance
import okio.IOException
import retrofit2.Response
import java.util.HashMap

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

    override suspend fun getHeroesByName(key:String, keyword: String, pageNum:Int): MutableList<HeroProfile>? {

        val headers= HashMap<String, String>()

        headers.put("Authorization", key)
        headers.put("keyword", keyword)
        headers.put("pagination",pageNum.toString())

        println("Heard: $headers")

        val response: Response<MutableList<HeroProfile>>? = try {
            RetrofitInstance.api.getHeroesByName(headers)
        }catch (exception:IOException){
            println(exception)
            null
        }
        val responseState = if (response?.isSuccessful == true && response.body() != null) response.body() else null
        println("--------> responseState: $responseState")
        return responseState
    }
}