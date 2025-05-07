package com.gustavo.brilhante.cutecats.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {
    @GET("images/search")
    suspend fun getItems(@Query("limit") limit: Int = 10): List<ItemDto>
}