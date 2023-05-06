package com.example.project.domain.repository

import com.example.project.domain.entity.Dish
import com.example.project.domain.entity.Material
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DishRepository : JpaRepository<Dish, String> {
  @Query("select d from Dish d order by d.createTimestamp asc")
  fun findAllByOrderByCreateTimestampAsc(): List<Dish>

  @Query("select d from Dish d where d.dishName like %?1% order by d.createTimestamp asc")
  fun findByDishNameContainingOrderByCreateTimestampAsc(dishName: String): List<Dish>

  @Query("select d from Dish d inner join Material m on d.dishId = m.dish.dishId where u.dishId = :dishId")
  fun findByMaterials(@Param("dishId") dishId: String): List<Material>
}