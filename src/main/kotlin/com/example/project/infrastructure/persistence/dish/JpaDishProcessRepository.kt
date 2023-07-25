package com.example.project.infrastructure.persistence.dish

import com.example.project.domain.model.dish.DishProcess
import com.example.project.domain.repository.dish.DishProcessRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface JpaDishProcessRepository : DishProcessRepository, JpaRepository<DishProcess, String> {
}