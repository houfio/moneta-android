package io.houf.moneta.model

data class ResponseModel<T>(
    val data: List<T>,
    val status: StatusModel
)

data class StatusModel(
    val timestamp: String,
    val errorCode: Int,
    val errorMessage: String?,
    val elapsed: Int,
    val creditCount: Int
)
