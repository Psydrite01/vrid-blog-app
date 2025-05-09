package com.psydrite.vrid_blog_app.data.daggerhilt

import com.psydrite.vrid_blog_app.data.repository.BlogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    fun provideBlogRespository(): BlogRepository{
        return BlogRepository()
    }
}