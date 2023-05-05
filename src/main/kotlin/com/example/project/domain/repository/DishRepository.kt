package com.example.project.domain.repository

import com.example.project.domain.entity.Dish
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DishRepository : JpaRepository<Dish, String> {
//  fun findAllOrderByDishName(): List<Dish>

  fun findByDishNameContaining(dishName: String): List<Dish>
}