package com.example.project.infrastructure.persistence.material

import com.example.project.domain.model.material.Material
import com.example.project.domain.repository.material.MaterialRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaMaterialRepository : MaterialRepository, JpaRepository<Material, String> {
}