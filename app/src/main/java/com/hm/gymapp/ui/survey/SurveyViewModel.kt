package com.hm.gymapp.ui.survey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hm.gymapp.data.model.User
import com.hm.gymapp.data.service.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val userDao: UserDao
): ViewModel() {

    fun saveUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }
}