package com.gustavo.brilhante.cutecats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavo.brilhante.cutecats.domain.ItemsFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val facade: ItemsFacade
) : ViewModel() {

    private val _uiState = MutableStateFlow<ItemUiState>(ItemUiState.Loading)
    val uiState: StateFlow<ItemUiState> = _uiState

    init {
        loadCatItems()
    }

    fun loadCatItems() {
        viewModelScope.launch {
            try {
                val items = facade.fetchCatItems()
                _uiState.value = ItemUiState.Success(items)
            } catch (e: Exception) {
                _uiState.value = ItemUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }

    fun loadDogItems() {
        viewModelScope.launch {
            try {
                val items = facade.fetchDogItems()
                _uiState.value = ItemUiState.Success(items)
            } catch (e: Exception) {
                _uiState.value = ItemUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}