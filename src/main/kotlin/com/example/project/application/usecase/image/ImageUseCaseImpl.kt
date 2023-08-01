package com.example.project.application.usecase.image

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@RequiredArgsConstructor
@Service
class ImageUseCaseImpl() : ImageUseCase {
  override fun uploadImage(image: MultipartFile): String {
    return "objectKey"
  }
}