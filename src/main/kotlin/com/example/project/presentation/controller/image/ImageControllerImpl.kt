package com.example.project.presentation.controller.image

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequiredArgsConstructor
class ImageControllerImpl : ImageController {
  @PostMapping("/upload")
  override fun uploadFile(@RequestParam("image") image: MultipartFile): Unit {
    
  }
}