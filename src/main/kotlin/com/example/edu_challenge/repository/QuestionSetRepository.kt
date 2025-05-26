package com.example.edu_challenge.repository

import com.example.edu_challenge.model.QuestionSet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionSetRepository : JpaRepository<QuestionSet, Long>