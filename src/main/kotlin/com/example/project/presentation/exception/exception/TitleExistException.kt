package com.example.project.presentation.exception.exception

class TitleExistException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)