package com.example.project.domain.repository

import com.example.project.domain.entity.Material
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MaterialRepository : JpaRepository<Material, String> {
}