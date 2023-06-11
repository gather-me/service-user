package com.odenizturker.user.entity

import com.odenizturker.user.model.UserModel
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("\"user\"")
data class UserEntity(
    @Id
    val id: Long? = null,
    val firstName: String,
    val secondName: String,
    val username: String,
    val emailAddress: String,
    val password: String,
    val registrationCompleted: Boolean
) {
    fun toModel() = UserModel(
        id = id!!,
        firstName = firstName,
        secondName = secondName,
        username = username,
        emailAddress = emailAddress
    )
}
