package com.example.project.presentation.exception.exception

class InvalidTokenException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)