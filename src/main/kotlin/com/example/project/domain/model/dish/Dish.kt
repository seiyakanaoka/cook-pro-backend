package com.example.project.domain.model.dish

import com.example.project.domain.model.category.Category
import com.example.project.domain.model.material.Material
import com.example.project.domain.model.user.User
import jakarta.persistence.*
import lombok.Data
import java.sql.Timestamp
import java.util.*

@Entity
@Data
@Table(name = "dish")
data class Dish(
  @Id
  @Column(name = "dish_id", updatable = false, nullable = false)
  var dishId: String = UUID.randomUUID().toString(),
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") val user: User,
  val dishName: String,
  val dishCreateRequiredTime: Int,
  val createTimestamp: Timestamp? = Timestamp(Date().time),
  val updateTimestamp: Timestamp? = Timestamp(Date().time),
  @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
  @JoinTable(
    name = "dish_category",
    joinColumns = [JoinColumn(name = "dish_id")],
    inverseJoinColumns = [JoinColumn(name = "category_id")]
  )
  var categories: MutableList<Category> = mutableListOf(),
  @OneToMany(mappedBy = "dish", cascade = [CascadeType.ALL], orphanRemoval = true)
  var materials: MutableList<Material> = mutableListOf()
)
