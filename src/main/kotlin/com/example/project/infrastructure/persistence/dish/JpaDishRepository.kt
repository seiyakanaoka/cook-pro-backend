package com.example.project.infrastructure.persistence.dish

import com.example.project.domain.model.category.Category
import com.example.project.domain.model.dish.Dish
import com.example.project.domain.model.dish.DishImage
import com.example.project.domain.model.dish.DishProcess
import com.example.project.domain.model.material.Material
import com.example.project.domain.model.user.User
import com.example.project.domain.repository.dish.DishRepository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface JpaDishRepository : DishRepository, JpaRepository<Dish, String> {
  override fun delete(dish: Dish): Unit

  @Query("select d from Dish d where d.user.userId = :userId order by d.createTimestamp desc")
  override fun findAllByOrderByCreateTimestampDesc(@Param("userId") userId: String): List<Dish>

  @Query("select c from Dish d join d.categories c where d.user.userId = :userId and d.dishId = :dishId")
  override fun findByDishCategories(
    @Param("userId") userId: String,
    @Param("dishId") dishId: String
  ): List<Category>

  @Query("select d from Dish d where d.user.userId = :userId and d.dishName like %:dishName% order by d.createTimestamp desc")
  override fun findByDishNameContainingOrderByCreateTimestampDesc(
    @Param("userId") userId: String,
    @Param("dishName") dishName: String?
  ): List<Dish>

  @Query("select m from Dish d inner join Material m on d.dishId = m.dish.dishId where d.dishId = :dishId order by d.createTimestamp desc")
  override fun findByDishMaterials(@Param("dishId") dishId: String): List<Material>

  @Query("select dp from Dish d inner join DishProcess dp on d.dishId = dp.dish.dishId where d.dishId = :dishId")
  override fun findByProcesses(@Param("dishId") dishId: String): List<DishProcess>

  @Query("select di from Dish d inner join DishImage di on d.dishId = di.dish.dishId where d.dishId = :dishId")
  override fun findByDishImages(@Param("dishId") dishId: String): List<DishImage>

  @Query("select d from Dish d inner join d.categories c where c.id in :categoryIds and d.user.userId = :userId order by d.createTimestamp desc")
  override fun findByCategoryDishes(
    @Param("userId") userId: String,
    @Param("categoryIds") categoryIds: List<String>
  ): List<Dish>

  @Query("select u from Dish d inner join User u on u.userId = d.user.userId where u.userId = :userId")
  override fun findByDishesUser(@Param("userId") userId: String): User
}