package com.gustavo.brilhante.cutecats.data

import javax.inject.Inject

class ItemRepository @Inject constructor(private val networkClient: NetworkClient) {

    suspend fun fetchItems(): List<ItemDto> {
        return try {
            // Chama a API para buscar os itens
            networkClient.getItems()
        } catch (e: Exception) {
            // Em caso de erro, pode lançar uma exceção personalizada ou apenas propagar a exception
            throw Exception("Erro ao carregar os itens: ${e.message}")
        }
    }
}