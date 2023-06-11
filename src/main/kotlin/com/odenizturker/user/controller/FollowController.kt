package com.odenizturker.user.controller

import com.odenizturker.user.model.UserModel
import com.odenizturker.user.service.FollowService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FollowController(
    private val followService: FollowService
) {
    @PostMapping("/users/{userId}/follow")
    suspend fun follow(
        @PathVariable userId: Long,
        @RequestParam followingUserId: Long
    ): Boolean {
        return followService.follow(userId, followingUserId)
    }

    @GetMapping("/users/{userId}/followers")
    suspend fun followers(
        @PathVariable userId: Long
    ): List<UserModel> {
        return followService.followers(userId)
    }

    @GetMapping("/users/{userId}/followers/count")
    suspend fun followerCount(
        @PathVariable userId: Long
    ): Long {
        return followService.followerCount(userId)
    }

    @GetMapping("/users/{userId}/followings")
    suspend fun followings(
        @PathVariable userId: Long
    ): List<UserModel> {
        return followService.followings(userId)
    }

    @GetMapping("/users/{userId}/followings/count")
    suspend fun followingCount(
        @PathVariable userId: Long
    ): Long {
        return followService.followingCount(userId)
    }
}
