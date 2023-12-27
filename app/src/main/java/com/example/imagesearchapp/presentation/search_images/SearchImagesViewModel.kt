package com.example.imagesearchapp.presentation.search_images

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearchapp.common.NetworkResponse
import com.example.imagesearchapp.domain.use_case.SearchImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchImagesViewModel @Inject constructor(
    // 画像検索機能をView側に提供する
    private val searchImagesUseCase: SearchImagesUseCase,
) : ViewModel() {
    // ViewModel内でのみ変更可能なState
    private val _state = mutableStateOf(SearchImagesState())

    // View側から参照可能なState
    val state: State<SearchImagesState> = _state

    // アプリ起動時に画面が表示されないことを防ぐため、searchImages メソッドを実行する
    init {
        searchImages("programming")
    }

    // 画像検索を実行する関数
    fun searchImages(query: String) {
        searchImagesUseCase(query).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    // stateの中身を書き換える
                    _state.value =
                        SearchImagesState(isLoading = false, images = result.data ?: emptyList())
                }

                is NetworkResponse.Failure -> {
                    // stateの中身を書き換える
                    _state.value = SearchImagesState(isLoading = false, error = result.error)
                }

                is NetworkResponse.Loading -> {
                    // stateの中身を書き換える
                    _state.value = SearchImagesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
