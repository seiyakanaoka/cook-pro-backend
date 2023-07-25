package com.example.project.presentation.exception.exception

class UserNotExistsException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)