package com.example.imagesearchapp.domain.use_case

import com.example.imagesearchapp.common.NetworkResponse
import com.example.imagesearchapp.data.remote.toImages
import com.example.imagesearchapp.domain.model.Image
import com.example.imagesearchapp.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    // 画像検索を実行する関数を定義する
    operator fun invoke(query: String): Flow<NetworkResponse<List<Image>>> = flow {
        try {
            // ローディング状態かどうか
            emit(NetworkResponse.Loading<List<Image>>())
            // クエリを受け取って検索処理を行う
            val images = repository.searchImages(query).toImages()
            // 通信が成功したかどうか
            emit(NetworkResponse.Success<List<Image>>(images))
        } catch (e: Exception) {
            // 通信が失敗していた場合はエラーメッセージを受け取り、表示する
            emit(NetworkResponse.Failure<List<Image>>(e.message.toString()))
        }
    }
}
