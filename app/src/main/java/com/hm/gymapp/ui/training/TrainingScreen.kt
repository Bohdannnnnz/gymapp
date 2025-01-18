import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hm.gymapp.R

data class Workout(val name: String, val description: String)

@Composable
fun TrainingScreen(onClick: () -> Unit) {
    // Lista 30 treningów
    val workouts = getSampleWorkouts()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Available Workouts",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp),
            fontSize = 24.sp
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(workouts) { workout ->
                WorkoutCard(workout = workout, onClick = onClick)
            }
        }
    }
}

@Composable
fun WorkoutCard(workout: Workout, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick), // Kliknięcie w całą kartę
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tekst z nazwą treningu i opisem
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = workout.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = workout.description,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                )
            }
            // Przycisk z ikoną strzałki
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Go to Exercises"
                )
            }
        }
    }
}

// Funkcja generująca przykładowe treningi
fun getSampleWorkouts(): List<Workout> {
    return List(30) { index ->
        Workout(
            name = "Workout ${index + 1}",
            description = when (index) {
                0 -> "This workout focuses on full-body strength and conditioning. Great for beginners!"
                1 -> "A high-intensity interval training (HIIT) session designed to burn fat and improve endurance."
                2 -> "Strength training workout that targets the legs and lower body muscles."
                3 -> "Cardio workout focused on increasing stamina with a variety of aerobic exercises."
                4 -> "Powerlifting program that targets deadlifts, squats, and bench press for maximum strength."
                5 -> "Yoga flow aimed at improving flexibility and balance while reducing stress."
                6 -> "Core-strengthening workout with exercises designed to build your abs and lower back."
                7 -> "A combination of strength and endurance exercises to tone and sculpt the upper body."
                8 -> "A mix of cardio and strength training that will push you to your limits."
                9 -> "A low-impact workout that focuses on flexibility, range of motion, and muscle recovery."
                10 -> "HIIT workout designed to target the entire body for fat loss and muscle toning."
                11 -> "A running program aimed at improving your speed and endurance over time."
                12 -> "Strength training circuit for your chest, arms, and shoulders to build upper body strength."
                13 -> "High-intensity workout combining running and strength exercises for an intense burn."
                14 -> "A beginner-friendly workout focusing on mobility, flexibility, and basic strength exercises."
                15 -> "An endurance workout that includes long sets of bodyweight exercises to improve stamina."
                16 -> "A circuit training workout that targets all muscle groups using bodyweight exercises."
                17 -> "Weight training workout focused on building muscle mass with compound movements."
                18 -> "Tabata training that combines short bursts of intense exercise with brief rest periods."
                19 -> "A functional fitness workout that mimics real-life movements and builds overall strength."
                20 -> "Boxing workout designed to increase cardiovascular fitness and build upper body strength."
                21 -> "Full-body strength and conditioning workout using kettlebells and dumbbells."
                22 -> "Jump rope workout to improve cardiovascular fitness, coordination, and agility."
                23 -> "A mobility workout that includes stretching, foam rolling, and flexibility exercises."
                24 -> "A high-intensity bodyweight workout designed to increase muscle endurance and stamina."
                25 -> "An athletic training workout focused on agility drills, speed, and explosive movements."
                26 -> "Resistance training program that uses resistance bands to improve strength and mobility."
                27 -> "A strength and cardio workout focused on functional fitness movements like squats and lunges."
                28 -> "A bodyweight strength workout that targets the legs and core with exercises like squats and planks."
                29 -> "A bootcamp-style workout with intervals of strength exercises and cardio to build endurance."
                else -> "Full-body workout with a mix of strength training and cardiovascular exercises."
            }
        )
    }
}
