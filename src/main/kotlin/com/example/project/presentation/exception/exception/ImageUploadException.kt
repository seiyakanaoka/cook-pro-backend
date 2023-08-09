package com.example.project.presentation.exception.exception

class ImageUploadException(val httpStatusCode: Int, message: String? = null) : RuntimeException(message)