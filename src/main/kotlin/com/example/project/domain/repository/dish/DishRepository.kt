package com.example.project.domain.repository.dish

import com.example.project.domain.entity.dish.Dish
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DishRepository : JpaRepository<Dish, String> {
}