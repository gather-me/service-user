package com.odenizturker.user.controller

import com.odenizturker.user.model.RegistrationRequest
import com.odenizturker.user.model.UserModel
import com.odenizturker.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @GetMapping("users/all")
    suspend fun getUsersByIds(
        @RequestParam userIds: List<Long>
    ): List<UserModel> = userService.getUsersByIds(userIds)

    @GetMapping("users/{userId}")
    suspend fun getUserById(
        @PathVariable userId: Long
    ): UserModel = userService.getUserById(userId)

    @PostMapping("/register")
    suspend fun register(
        @RequestBody body: RegistrationRequest
    ) {
        userService.register(body)
    }
}
