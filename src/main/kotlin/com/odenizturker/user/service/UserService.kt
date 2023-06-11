package com.odenizturker.user.service

import com.odenizturker.user.entity.UserEntity
import com.odenizturker.user.exception.EmailAlreadyRegistered
import com.odenizturker.user.exception.FieldMustBeFilled
import com.odenizturker.user.exception.InvalidEmailFormat
import com.odenizturker.user.exception.InvalidUsernameFormat
import com.odenizturker.user.model.RegistrationRequest
import com.odenizturker.user.model.UserModel
import com.odenizturker.user.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val validationService: ValidationService
) {
    suspend fun getUserById(userId: Long): UserModel {
        return userRepository.findById(userId).awaitSingle().toModel()
    }

    suspend fun register(body: RegistrationRequest) {
        validateRequest(body)
        val record = UserEntity(
            firstName = body.firstName,
            secondName = body.secondName,
            username = body.username,
            emailAddress = body.emailAddress.lowercase(),
            password = BCryptPasswordEncoder().encode(body.password),
            registrationCompleted = false
        )
        userRepository.save(record).awaitSingle()
    }

    private suspend fun validateRequest(body: RegistrationRequest) {
        if (body.firstName.isBlank()) throw FieldMustBeFilled("first name")
        if (body.secondName.isBlank()) throw FieldMustBeFilled("second name")

        val emailAddress = body.emailAddress.lowercase()
        validateEmailAddress(emailAddress)
        validateUsername(body.username)
    }

    private suspend fun validateEmailAddress(email: String) {
        when {
            !validationService.validateEmailFormat(email) -> throw InvalidEmailFormat(email)
            !validateEmailUsage(email) -> throw EmailAlreadyRegistered(email)
        }
    }

    private suspend fun validateEmailUsage(email: String): Boolean {
        userRepository.findByEmailAddress(email).awaitSingleOrNull() ?: return true
        return false
    }

    private suspend fun validateUsername(username: String) {
        when {
            !validationService.validateUsernameFormat(username) -> throw InvalidUsernameFormat(username)
            !validateUsernameUsage(username) -> throw EmailAlreadyRegistered(username)
        }
    }

    private suspend fun validateUsernameUsage(username: String): Boolean {
        userRepository.findByUsername(username).awaitSingleOrNull() ?: return true
        return false
    }

    suspend fun getUsersByIds(userIds: List<Long>): List<UserModel> {
        return userRepository.findAllById(userIds).map {
            it.toModel()
        }.collectList().awaitSingle()
    }
}
