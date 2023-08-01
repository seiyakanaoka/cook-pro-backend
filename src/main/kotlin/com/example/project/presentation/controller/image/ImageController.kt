package com.example.project.presentation.controller.image

import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

interface ImageController {
  fun uploadFile(@RequestParam("image") image: MultipartFile): Unit
}