package com.gustavo.brilhante.cutecats.data

class NetworkClient(private val itemService: ItemService) {
    suspend fun getItems(): List<ItemDto> {
        return itemService.getItems()
    }
}
