package com.alamin.placeholder.di

import com.alamin.placeholder.model.network.APIClient
import com.alamin.placeholder.model.network.ApiInterface
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
            .apply {
                this.level = HttpLoggingInterceptor.Level.BODY;
            }
    }

    @Singleton
    @Provides
    fun provideClient(interceptor: HttpLoggingInterceptor): OkHttpClient{
       return OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

        }.build();
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(APIClient.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build();
    }

    @Singleton
    @Provides
    fun provideAPIInterface(retrofit: Retrofit): ApiInterface{
        return retrofit.create(ApiInterface::class.java);
    }

}