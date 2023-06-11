package com.odenizturker.user.repository

import com.odenizturker.user.entity.UserEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<UserEntity, Long> {
    fun findByEmailAddress(emailAddress: String): Mono<UserEntity>
    fun findByUsername(username: String): Mono<UserEntity>
}
