package com.example.project.domain.infrastructure.persistence.dish

import com.example.project.domain.domain.model.dish.Dish
import com.example.project.domain.domain.repository.dish.DishRepository
import com.example.project.domain.entity.DishImage
import com.example.project.domain.entity.DishProcess
import com.example.project.domain.entity.Material
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface JpaDishRepository : DishRepository, JpaRepository<Dish, String> {
  @Query("select d from Dish d order by d.createTimestamp desc")
  override fun findAllByOrderByCreateTimestampDesc(): List<Dish>

  @Query("select d from Dish d where d.dishName like %?1% order by d.createTimestamp desc")
  override fun findByDishNameContainingOrderByCreateTimestampDesc(dishName: String): List<Dish>

  @Query("select m from Dish d inner join Material m on d.dishId = m.dish.dishId where d.dishId = :dishId order by d.createTimestamp desc")
  override fun findByMaterialsOrderByCreateTimestampDesc(@Param("dishId") dishId: String): List<Material>

  @Query("select dp from Dish d inner join DishProcess dp on d.dishId = dp.dish.dishId where d.dishId = :dishId")
  override fun findByProcesses(@Param("dishId") dishId: String): List<DishProcess>

  @Query("select di from Dish d inner join DishImage di on d.dishId = di.dish.dishId where d.dishId = :dishId")
  override fun findByDishImages(@Param("dishId") dishId: String): List<DishImage>

  override fun findByCategoriesCategoryIdInOrderByCreateTimestampDesc(categoryIds: List<String>): List<Dish>
}