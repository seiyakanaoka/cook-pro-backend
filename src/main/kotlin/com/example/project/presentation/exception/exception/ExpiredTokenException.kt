package com.example.project.presentation.exception.exception

class ExpiredTokenException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)