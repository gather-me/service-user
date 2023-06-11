package com.odenizturker.user.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class AlreadyFollowing(followerId: Long, followingId: Long) : ResponseStatusException(HttpStatus.BAD_REQUEST, "User $followerId already following user $followingId.")
class NotFollowing(followerId: Long, followingId: Long) : ResponseStatusException(HttpStatus.BAD_REQUEST, "User $followerId not following user $followingId.")
class FieldMustBeFilled(field: String) : ResponseStatusException(HttpStatus.BAD_REQUEST, "$field The field cannot be left blank.")
class EmailAlreadyRegistered(email: String) : ResponseStatusException(HttpStatus.BAD_REQUEST, "Email address $email already registered.")
class InvalidEmailFormat(email: String) : ResponseStatusException(HttpStatus.BAD_REQUEST, "Format of email address $email not validated.")
class InvalidUsernameFormat(username: String) : ResponseStatusException(HttpStatus.BAD_REQUEST, "Format of username $username not validated.")
class UsernameAlreadyRegistered(username: String) : ResponseStatusException(HttpStatus.BAD_REQUEST, "Username $username already registered.")
