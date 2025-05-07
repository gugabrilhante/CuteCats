package com.gustavo.brilhante.cutecats.di

import com.gustavo.brilhante.cutecats.data.ItemRepository
import com.gustavo.brilhante.cutecats.data.ItemService
import com.gustavo.brilhante.cutecats.data.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object CatsNetworkModule {

    @Provides
    @Named("CatsRetrofit")
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", "live_Z1MSgRQkrHWgtPqkhdosyqSC60DFTgLDdt7eKIeDzsv2LbBBbetcb9PJ2N9XIXUp")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("CatsItemService")
    fun provideItemService(@Named("CatsRetrofit") retrofit: Retrofit): ItemService {
        return retrofit.create(ItemService::class.java)
    }

    @Provides
    @Named("CatsNetworkClient")
    fun provideNetworkClient(@Named("CatsItemService") itemService: ItemService): NetworkClient {
        return NetworkClient(itemService)
    }

    @Provides
    @Named("CatsItemRepository")
    fun provideItemRepository(@Named("CatsNetworkClient") networkClient: NetworkClient): ItemRepository {
        return ItemRepository(networkClient)
    }

}