package com.bd.sqlitecrud

import java.io.Serializable

data class Model(
    var id: Int?,
    var name: String,
    var email: String,
    var password: String,
    var account: String,
    var pin: String,
    var url: String
) : Serializable
