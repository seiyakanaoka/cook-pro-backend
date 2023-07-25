package com.example.project.infrastructure.persistence.dish

import com.example.project.domain.model.dish.DishImage
import com.example.project.domain.repository.dish.DishImageRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface JpaDishImageRepository : DishImageRepository, JpaRepository<DishImage, String> {
}