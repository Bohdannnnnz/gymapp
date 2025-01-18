package com.hm.gymapp.ui.survey

import android.content.IntentSender.OnFinished
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hm.gymapp.R
import com.hm.gymapp.data.model.User
import com.hm.gymapp.ui.theme.AppTheme
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyScreen(
    modifier: Modifier = Modifier,
    viewModel: SurveyViewModel = hiltViewModel(),
    onFinished: () -> Unit
) {

    val pagerState = rememberPagerState { 3 }
    val coroutineScope = rememberCoroutineScope()


    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var bodyFat by remember { mutableStateOf(0f) }
    var selectedGender by remember { mutableStateOf("Man") }
    var selectedObjectives by remember { mutableStateOf(setOf<String>()) }
    var selectedMuscleGroups by remember { mutableStateOf(setOf<String>()) }
    var selectedEnvironment by remember { mutableStateOf("Solo training") }
    var selectedFitnessLevel by remember { mutableStateOf("Beginner") }
    var activityType by remember { mutableStateOf("") }
    var medicalConditions by remember { mutableStateOf("") }
    var selectedDays by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Questionnaire")
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (pagerState.currentPage == 2) {
                    val user = User(
                        id = 1,
                        age = age,
                        height = height,
                        weight = weight,
                        bodyFat = bodyFat.toString(),
                        gender = selectedGender,
                        primaryObjectives = selectedObjectives.toList(),
                        muscleGroups = selectedMuscleGroups.toList(),
                        environmentPreference = selectedEnvironment,
                        fitnessLevel = selectedFitnessLevel,
                        activityType = activityType,
                        medicalConditions = medicalConditions,
                        trainingDays = selectedDays.toList()
                    )
                    onFinished()
                    viewModel.saveUser(user)
                } else {
                    coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            }) {
                Text("Next")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { padding ->

        HorizontalPager(
            state = pagerState, // Adjust the count as needed for more pages
            modifier = Modifier.padding(padding)
        ) { page ->
            when (page) {
                0 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextField(
                                value = age,
                                onValueChange = { age = it },
                                label = { Text("Age") },
                                modifier = Modifier.width(120.dp),
                                maxLines = 1,
                                shape = RoundedCornerShape(0.dp)
                            )
                            TextField(
                                value = height,
                                onValueChange = { height = it },
                                label = { Text("Height") },
                                modifier = Modifier.width(120.dp),
                                maxLines = 1,
                                shape = RoundedCornerShape(0.dp)
                            )
                            TextField(
                                value = weight,
                                onValueChange = { weight = it },
                                label = { Text("Weight") },
                                modifier = Modifier.width(120.dp),
                                maxLines = 1,
                                shape = RoundedCornerShape(0.dp)
                            )
                        }

                        Text("Gender")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(onClick = { selectedGender = "Male" }) {
                                Icon(
                                    painter = painterResource(R.drawable.man_ic),
                                    contentDescription = null,
                                    tint = if (selectedGender == "Male") AppTheme.colorScheme.primary else Color.Black
                                )
                            }
                            IconButton(onClick = { selectedGender = "Female" }) {
                                Icon(
                                    painter = painterResource(R.drawable.woman),
                                    contentDescription = null,
                                    tint = if (selectedGender == "Female") AppTheme.colorScheme.primary else Color.Black
                                )
                            }
                        }
                        // Dynamiczny obraz SVG w zależności od wartości bodyFat
                        val svgResId = when (bodyFat.toInt()) {
                            in 0..25 -> R.drawable.skin
                            in 26..50 -> R.drawable.body_builder
                            in 51..75 -> R.drawable.fat
                            else -> R.drawable.ultra_fat
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = svgResId),
                                contentDescription = "Body Fat Illustration",
                                modifier = Modifier.size(120.dp),
                                tint = Color.Unspecified // Zachowanie oryginalnych kolorów SVG
                            )
                        }

                        Text("Body Fat")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("${bodyFat}%", modifier = Modifier.width(50.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Slider(
                                value = bodyFat,
                                onValueChange = {
                                    bodyFat =
                                        BigDecimal(it.toDouble()).setScale(1, RoundingMode.HALF_UP)
                                            .toFloat()
                                },
                                valueRange = 0f..100f,
                                steps = 99,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        Button(onClick = { }) {
                            Text("Add Photo")
                        }
                    }
                }

                1 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text("Fitness Level")
                        SinglePickFlowRow(
                            options = listOf("Beginner", "Intermediate", "Advanced"),
                            selectedOption = selectedFitnessLevel,
                            onOptionSelected = { selectedFitnessLevel = it }
                        )

                        TextField(
                            value = activityType,
                            onValueChange = { activityType = it },
                            label = { Text("Activity Type") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(0.dp),
                            minLines = 3
                        )

                        TextField(
                            value = medicalConditions,
                            onValueChange = { medicalConditions = it },
                            label = { Text("Medical Conditions or Injuries") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(0.dp),
                            minLines = 3
                        )

                        Text("Desired Days of the Week for Training")
                        MultiplePickFlowRow(
                            options = listOf(
                                "Monday",
                                "Tuesday",
                                "Wednesday",
                                "Thursday",
                                "Friday",
                                "Saturday",
                                "Sunday"
                            ),
                            selectedOptions = selectedDays,
                            onOptionSelected = { day ->
                                selectedDays = if (selectedDays.contains(day)) {
                                    selectedDays - day
                                } else {
                                    selectedDays + day
                                }
                            }
                        )
                    }
                }

                2 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text("Primary Objective")
                        MultiplePickFlowRow(
                            options = listOf(
                                "Building muscle",
                                "Losing weight",
                                "Preparing for an event",
                                "Improving endurance or flexibility",
                                "Maintaining general health and wellness"
                            ),
                            selectedOptions = selectedObjectives,
                            onOptionSelected = { option ->
                                selectedObjectives = if (selectedObjectives.contains(option)) {
                                    selectedObjectives - option
                                } else {
                                    selectedObjectives + option
                                }
                            }
                        )

                        Text("Muscle groups to focus on")
                        MultiplePickFlowRow(
                            options = listOf("Chest", "Back", "Shoulders", "Arms", "Abs", "Legs"),
                            selectedOptions = selectedMuscleGroups,
                            onOptionSelected = { option ->
                                selectedMuscleGroups = if (selectedMuscleGroups.contains(option)) {
                                    selectedMuscleGroups - option
                                } else {
                                    selectedMuscleGroups + option
                                }
                            }
                        )

                        Text("Environment Preferences")
                        SinglePickFlowRow(
                            options = listOf("Group activities", "Solo training", "Mixed training"),
                            selectedOption = selectedEnvironment,
                            onOptionSelected = { selectedEnvironment = it }
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun InitialInfoPage(onNext: () -> Unit = {}) {
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var bodyFat by remember { mutableStateOf(0f) }
    var selectedGender by remember { mutableStateOf("Man") }


}

@Composable
fun ObjectivePage() {
    var selectedObjectives by remember { mutableStateOf(setOf<String>()) }
    var selectedMuscleGroups by remember { mutableStateOf(setOf<String>()) }
    var selectedEnvironment by remember { mutableStateOf("Solo training") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Primary Objective")
        MultiplePickFlowRow(
            options = listOf(
                "Building muscle",
                "Losing weight",
                "Preparing for an event",
                "Improving endurance or flexibility",
                "Maintaining general health and wellness"
            ),
            selectedOptions = selectedObjectives,
            onOptionSelected = { option ->
                selectedObjectives = if (selectedObjectives.contains(option)) {
                    selectedObjectives - option
                } else {
                    selectedObjectives + option
                }
            }
        )

        Text("Muscle groups to focus on")
        MultiplePickFlowRow(
            options = listOf("Chest", "Back", "Shoulders", "Arms", "Abs", "Legs"),
            selectedOptions = selectedMuscleGroups,
            onOptionSelected = { option ->
                selectedMuscleGroups = if (selectedMuscleGroups.contains(option)) {
                    selectedMuscleGroups - option
                } else {
                    selectedMuscleGroups + option
                }
            }
        )

        Text("Environment Preferences")
        SinglePickFlowRow(
            options = listOf("Group activities", "Solo training", "Mixed training"),
            selectedOption = selectedEnvironment,
            onOptionSelected = { selectedEnvironment = it }
        )
    }
}

@Composable
fun FitnessLevelPage() {
    var selectedFitnessLevel by remember { mutableStateOf("Beginner") }
    var activityType by remember { mutableStateOf("") }
    var medicalConditions by remember { mutableStateOf("") }
    var selectedDays by remember { mutableStateOf(setOf<String>()) }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiplePickFlowRow(
    options: List<String>,
    selectedOptions: Set<String>,
    onOptionSelected: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .background(
                        if (selectedOptions.contains(option)) AppTheme.colorScheme.primary else Color.Transparent
                    )
                    .padding(8.dp)
                    .clickable { onOptionSelected(option) }
            ) {
                Text(
                    text = option,
                    color = if (selectedOptions.contains(option)) Color.White else Color.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SinglePickFlowRow(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { option ->
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Gray)
                    .background(
                        if (option == selectedOption) AppTheme.colorScheme.primary else Color.Transparent
                    )
                    .padding(8.dp)
                    .clickable { onOptionSelected(option) }
            ) {
                Text(
                    text = option,
                    color = if (option == selectedOption) Color.White else Color.Black
                )
            }
        }
    }
}

@Composable
fun FancyTab(title: String, onClick: () -> Unit, selected: Boolean) {
    Tab(selected, onClick) {
        Column(
            Modifier
                .padding(10.dp)
                .height(50.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .size(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        color =
                        if (selected) AppTheme.colorScheme.primary
                        else AppTheme.colorScheme.background
                    )
            )
            Text(
                text = title,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}