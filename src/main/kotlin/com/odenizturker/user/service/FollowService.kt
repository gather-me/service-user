package com.odenizturker.user.service

import com.odenizturker.user.entity.FollowEntity
import com.odenizturker.user.model.UserModel
import com.odenizturker.user.repository.FollowRepository
import com.odenizturker.user.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FollowService(
    private val followRepository: FollowRepository,
    private val userRepository: UserRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    suspend fun follow(followerUserId: Long, followingUserId: Long): Boolean {
        val follow: FollowEntity = followRepository.findByFollowerIdAndFollowingId(followerUserId, followingUserId).awaitSingleOrNull()
            ?: run {
                val entity = FollowEntity(
                    followerId = followerUserId,
                    followingId = followingUserId
                )
                followRepository.save(entity).awaitSingle()
                logger.debug("User $followerUserId successfully followed user $followingUserId")
                return true
            }
        followRepository.delete(follow).awaitSingleOrNull()
        logger.debug("User $followerUserId successfully unfollowed user $followingUserId")
        return false
    }

    suspend fun followers(userId: Long): List<UserModel> {
        val followerIds = followRepository.findAllByFollowingId(userId).map { it.followerId }
        return userRepository.findAllById(followerIds).collectList().awaitSingle().map { it.toModel() }
    }

    suspend fun followerCount(userId: Long): Long {
        return followRepository.countByFollowingId(userId).awaitSingle()
    }

    suspend fun followings(userId: Long): List<UserModel> {
        val followingIds = followRepository.findAllByFollowerId(userId).map { it.followingId }
        return userRepository.findAllById(followingIds).map { it.toModel() }.collectList().awaitSingle()
    }

    suspend fun followingCount(userId: Long): Long {
        return followRepository.countByFollowerId(userId).awaitSingle()
    }
}
