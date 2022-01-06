package com.patofch.marvelcomics.core.di

import com.patofch.marvelcomics.BuildConfig
import com.patofch.marvelcomics.data.data_source.api.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val url = request.url().newBuilder()
                .addQueryParameter("ts", BuildConfig.TS)
                .addQueryParameter("apikey", BuildConfig.PUBLIC_API_KEY)
                .addQueryParameter("hash", BuildConfig.HASH)
                .build()
            chain.proceed(request.newBuilder().url(url).build())
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        networkInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(networkInterceptor)
            .build()

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }
}