package com.psydrite.vrid_blog_app

import android.app.Application
import com.psydrite.vrid_blog_app.data.api.VridRetrofitInstance
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        VridRetrofitInstance.init(this) // Application context
    }
}