package com.odenizturker.user.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("follow")
data class FollowEntity(
    @Id
    val id: Long? = null,
    val followerId: Long,
    val followingId: Long
)
