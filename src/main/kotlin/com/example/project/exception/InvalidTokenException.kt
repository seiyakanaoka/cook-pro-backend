package com.example.project.exception

class InvalidTokenException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)