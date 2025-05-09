package com.psydrite.vrid_blog_app.data.api

import android.content.Context
import com.psydrite.vrid_blog_app.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object VridRetrofitInstance {

    private lateinit var retrofit: Retrofit
    lateinit var api: VridInterface
        private set

    fun init(context: Context) {
        val baseUrl = context.getString(R.string.blog_api)
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(VridInterface::class.java)
    }
}