package com.odenizturker.user.model

data class RegistrationRequest(
    val firstName: String,
    val secondName: String,
    val username: String,
    val emailAddress: String,
    val password: String
)
