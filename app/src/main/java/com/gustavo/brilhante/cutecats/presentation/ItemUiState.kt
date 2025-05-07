package com.gustavo.brilhante.cutecats.presentation

import com.gustavo.brilhante.cutecats.domain.Item


sealed class ItemUiState {
    data object Loading : ItemUiState()
    data class Success(val items: List<Item>) : ItemUiState()
    data class Error(val message: String) : ItemUiState()
}