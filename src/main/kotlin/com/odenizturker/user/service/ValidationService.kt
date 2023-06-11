package com.odenizturker.user.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ValidationService(
    @Value("\${email.format.regex}")
    private val emailFormatRegex: Regex
) {
    fun validateEmailFormat(email: String): Boolean {
        return emailFormatRegex.matches(email)
    }

    fun validateUsernameFormat(username: String): Boolean {
        if (username.length < 3) return false
        return true
    }
}
