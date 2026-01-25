package com.coinny.storedcard.domain.model

import java.io.Serializable

data class VersionInfo(
    val versionCode: Int,
    val versionName: String,
    val downloadUrl: String,
    val updateMessage: String,
    val forceUpdate: Boolean = false
) : Serializable
