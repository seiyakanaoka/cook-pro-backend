package com.example.project.presentation.controller.image

import com.example.project.presentation.response.image.ImageResponse
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

interface ImageController {
  fun uploadImage(@RequestParam("image") image: MultipartFile): ImageResponse
}