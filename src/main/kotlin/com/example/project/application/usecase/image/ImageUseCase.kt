package com.example.project.application.usecase.image

import org.springframework.web.multipart.MultipartFile

interface ImageUseCase {
  fun uploadImage(image: MultipartFile): String
}