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
object DogsNetworkModule {

    @Provides
    @Named("DogsRetrofit")
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", "live_ED5kzKiRg2PhYy3M1TWpoYC1VBQ08gaPztitaaHLxO94pmTKzbNsxY9PLcaTJloc")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("DogsItemService")
    fun provideItemService(@Named("DogsRetrofit") retrofit: Retrofit): ItemService {
        return retrofit.create(ItemService::class.java)
    }

    @Provides
    @Named("DogsNetworkClient")
    fun provideNetworkClient(@Named("DogsItemService") itemService: ItemService): NetworkClient {
        return NetworkClient(itemService)
    }

    @Provides
    @Named("DogsItemRepository")
    fun provideItemRepository(@Named("DogsNetworkClient") networkClient: NetworkClient): ItemRepository {
        return ItemRepository(networkClient)
    }

}