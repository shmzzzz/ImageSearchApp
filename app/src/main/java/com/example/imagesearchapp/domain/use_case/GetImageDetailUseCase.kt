package com.example.imagesearchapp.domain.use_case

import com.example.imagesearchapp.common.NetworkResponse
import com.example.imagesearchapp.data.remote.toImageDetail
import com.example.imagesearchapp.domain.model.ImageDetail
import com.example.imagesearchapp.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImageDetailUseCase @Inject constructor(
    private val repository: ImageRepository
) {

    operator fun invoke(imageId: String): Flow<NetworkResponse<ImageDetail>> = flow {
        try {
            // ローディング状態か
            emit(NetworkResponse.Loading<ImageDetail>())
            // 画像IDを受け取って詳細情報を取得する
            val imageDetail = repository.getImageById(imageId).toImageDetail()
            // 通信成功したか
            emit(NetworkResponse.Success<ImageDetail>(imageDetail))
        } catch (e: Exception) {
            // 追伸失敗した場合はエラーメッセージを表示する
            emit(NetworkResponse.Failure<ImageDetail>(e.message.toString()))
        }
    }
}
