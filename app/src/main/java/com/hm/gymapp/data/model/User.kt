package com.hm.gymapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val age: String,
    val height: String,
    val weight: String,
    val bodyFat: String,
    val gender: String,
    val primaryObjectives: List<String>,
    val muscleGroups: List<String>,
    val environmentPreference: String,
    val fitnessLevel: String,
    val activityType: String,
    val medicalConditions: String,
    val trainingDays: List<String>
)