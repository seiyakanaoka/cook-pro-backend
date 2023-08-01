package com.example.project.presentation.controller.image

import com.example.project.application.usecase.image.ImageUseCase
import com.example.project.presentation.response.image.ImageResponse
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class ImageControllerImpl(private val imageUseCase: ImageUseCase) : ImageController {
  @PostMapping("/image/upload")
  override fun uploadImage(@RequestParam("image") image: MultipartFile): ImageResponse {
    val imageId = imageUseCase.uploadImage(image)
    return ImageResponse(imageId)
  }
}