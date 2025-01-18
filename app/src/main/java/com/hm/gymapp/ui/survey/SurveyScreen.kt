package com.hm.gymapp.ui.survey

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hm.gymapp.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurveyScreen(
    modifier: Modifier = Modifier,
    viewModel: SurveyViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState { 4 }
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
            FloatingActionButton(onClick = { /* Handle next button click */ }) {
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
                0 -> InitialInfoPage()
                // Add more pages here
            }
        }

    }
}

@Composable
fun InitialInfoPage() {
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var bodyFat by remember { mutableStateOf(0f) }

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
            IconButton(onClick = { /* Handle male selection */ }) {
                Icon(Icons.Default.Person, contentDescription = "Male")
            }
            IconButton(onClick = { /* Handle female selection */ }) {
                Icon(Icons.Default.Person, contentDescription = "Female")
            }
        }
        Text("Body Fat")
        Slider(
            value = bodyFat,
            onValueChange = { bodyFat = it },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { /* Handle add photo */ }) {
            Text("Add Photo")
        }
    }
}