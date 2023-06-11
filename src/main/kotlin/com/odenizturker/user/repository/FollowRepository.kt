package com.odenizturker.user.repository

import com.odenizturker.user.entity.FollowEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface FollowRepository : ReactiveCrudRepository<FollowEntity, Long> {
    fun findByFollowerIdAndFollowingId(followerId: Long, followingId: Long): Mono<FollowEntity>
    fun findAllByFollowingId(followingId: Long): Flux<FollowEntity>
    fun countByFollowingId(followingId: Long): Mono<Long>
    fun findAllByFollowerId(followerId: Long): Flux<FollowEntity>
    fun countByFollowerId(followerId: Long): Mono<Long>
}
