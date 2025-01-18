package com.vk.directop.core.presentation

import com.vk.directop.core.domain.NetworkError
import kmp1201.composeapp.generated.resources.Res
import kmp1201.composeapp.generated.resources.error_no_internet
import kmp1201.composeapp.generated.resources.error_request_timeout
import kmp1201.composeapp.generated.resources.error_serialization
import kmp1201.composeapp.generated.resources.error_server
import kmp1201.composeapp.generated.resources.error_too_many_request
import kmp1201.composeapp.generated.resources.error_unknown

fun NetworkError.toUiText(): UiText {
    val stringRes = when (this) {
        NetworkError.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> Res.string.error_too_many_request
        NetworkError.NO_INTERNET -> Res.string.error_no_internet
        NetworkError.SERVER_ERROR -> Res.string.error_server
        NetworkError.SERIALIZATION -> Res.string.error_serialization
        NetworkError.UNKNOWN -> Res.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)
}
