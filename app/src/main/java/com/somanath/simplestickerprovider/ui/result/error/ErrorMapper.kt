package com.somanath.simplestickerprovider.ui.result.error


import java.io.IOException

// add more exceptions as you see fit
object ErrorMapper {

    fun map(error: Throwable): GenericError {
        return when (error) {
//            is HttpException -> GenericError.HttpError(error.code())
            is UnsupportedOperationException -> GenericError.UnsupportedEndpointError(
                error.message ?: ""
            )
            is IOException -> GenericError.ConnectionError
            else -> GenericError.UnknownError(error.message ?: "")
        }
    }
}
