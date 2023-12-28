package com.example.imagesearchapp.presentation.image_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearchapp.common.NetworkResponse
import com.example.imagesearchapp.domain.use_case.GetImageDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    private val getImageDetailUseCase: GetImageDetailUseCase,
    // MainActivityでパスとして渡ってきた画像IDを受け取るためのもの
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(ImageDetailState())
    val state: State<ImageDetailState> = _state

    init {
        // パスから画像IDを取得する
        savedStateHandle.get<String>("imageId")?.let { imageId ->
            getImageDetail(imageId)
        }
    }

    private fun getImageDetail(imageId: String) {
        // GetImageDetailUseCase.invokeでFlowが返ってくるので
        // emitが呼ばれる = Flowの状態が更新されたら毎回実行する(onEach)
        getImageDetailUseCase(imageId).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    _state.value = ImageDetailState(imageDetail = result.data)
                }

                is NetworkResponse.Failure -> {
                    _state.value = ImageDetailState(error = result.error)
                }

                is NetworkResponse.Loading -> {
                    _state.value = ImageDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
