package com.hm.gymapp.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import com.hm.gymapp.BuildConfig
import com.hm.gymapp.data.model.User
import com.hm.gymapp.data.service.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {

    val messages = mutableStateListOf<Message>()


    private lateinit var generativeModel: GenerativeModel
    init {
        viewModelScope.launch {
            val user: User? = userDao.getUserById(1)
            if (user != null) {
                generativeModel = GenerativeModel(
                    modelName = "gemini-1.5-flash",
                    apiKey = BuildConfig.apiKey,
                    generationConfig = generationConfig {
                        temperature = 1.3f
                        topK = 32
                        topP = 1f
                        maxOutputTokens = 4096
                    },
                    systemInstruction = content { text(describeUser(user)) }
                )
            }
            else {
                generativeModel = GenerativeModel(
                    modelName = "gemini-1.5-flash",
                    apiKey = BuildConfig.apiKey,
                    generationConfig = generationConfig {
                        temperature = 1.3f
                        topK = 32
                        topP = 1f
                        maxOutputTokens = 4096
                    },
                    systemInstruction = content { text("You are a Virtual Trainer assistant. From now on, act as a Virtual Trainer assistant, providing personalized fitness advice and training plans based on the user's profile.") }
                )
            }
        }
    }

    fun sendMessage(text: String, isUser: Boolean = true) {
        if (isUser) {
            messages.add(Message(text, "user"))
            viewModelScope.launch {
                val chat = generativeModel.startChat(
                    history = messages.map {
                        content(role = it.role) { text(it.content) }
                    }
                )
                val response = chat.sendMessage(text).text

                messages.add(Message(response ?: "Something went wrong.", "model"))
            }
        }
    }

    fun describeUser(user: User): String {
        return """
            You are a Virtual Trainer assistant. The user you are assisting has the following profile:
        Meet our user!
        Age: ${user.age}
        Height: ${user.height} cm
        Weight: ${user.weight} kg
        Body Fat: ${user.bodyFat}%
        Gender: ${user.gender}
        Primary Objectives: ${user.primaryObjectives.joinToString(", ")}
        Muscle Groups: ${user.muscleGroups.joinToString(", ")}
        Environment Preference: ${user.environmentPreference}
        Fitness Level: ${user.fitnessLevel}
        Activity Type: ${user.activityType}
        Medical Conditions: ${user.medicalConditions}
        Training Days: ${user.trainingDays.joinToString(", ")}
        From now on, act as a Virtual Trainer assistant, providing personalized fitness advice and training plans based on the user's profile.
    """.trimIndent()
    }
}

data class Message(val content: String, val role: String) {
    val isUser: Boolean
        get() = role == "user"
}