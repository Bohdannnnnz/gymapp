import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.hm.gymapp.R

data class Exercise(
    val name: String,
    val imageRes: Int,
    val repetitions: String,
    val duration: String,
    val restTime: String,
    val tips: String
)

@Composable
fun ExercisesScreen(onClick: () -> Unit) {
    // Lista 5 ćwiczeń
    val exercises = getSampleExercises()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Exercises",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp),
            fontSize = 24.sp
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(exercises) { exercise ->
                ExerciseCard(exercise = exercise, onClick = onClick)
            }
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = exercise.imageRes),
                contentDescription = exercise.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Repetitions: ${exercise.repetitions}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                )
                Text(
                    text = "Duration: ${exercise.duration}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                )
                Text(
                    text = "Rest Time: ${exercise.restTime}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                )

                Spacer(modifier = Modifier.height(8.dp)) // Odstęp

                Text(
                    text = "Tips: ${exercise.tips}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp)
                )
            }
        }
    }
}

fun getSampleExercises(): List<Exercise> {
    return listOf(
        Exercise(
            name = "Squat",
            imageRes = R.drawable.squat, // Zastąp nazwą swojego pliku PNG
            repetitions = "20 reps",
            duration = "40 seconds",
            restTime = "90 seconds",
            tips = "Ensure your knees are behind your toes when squatting. Keep your chest up and back straight."
        ),
        Exercise(
            name = "Push-up",
            imageRes = R.drawable.push, // Zastąp nazwą swojego pliku PNG
            repetitions = "15-20 reps",
            duration = "30 seconds",
            restTime = "60 seconds",
            tips = "Keep your body straight, don't let your back sag. Lower yourself slowly and push up explosively."
        ),
        Exercise(
            name = "Plank",
            imageRes = R.drawable.plank, // Zastąp nazwą swojego pliku PNG
            repetitions = "Hold for 30 seconds",
            duration = "30 seconds",
            restTime = "60 seconds",
            tips = "Engage your core and keep your body in a straight line from head to toes."
        ),
        Exercise(
            name = "Lunges",
            imageRes = R.drawable.lunges, // Zastąp nazwą swojego pliku PNG
            repetitions = "12 reps per leg",
            duration = "30 seconds",
            restTime = "60 seconds",
            tips = "Take a big step forward, keeping your knee over your ankle, and lower your body down."
        ),
        Exercise(
            name = "Mountain Climbers",
            imageRes = R.drawable.mountain_climbers, // Zastąp nazwą swojego pliku PNG
            repetitions = "30 seconds",
            duration = "30 seconds",
            restTime = "60 seconds",
            tips = "Keep your core tight and move your legs in a running motion while maintaining a plank position."
        )
    )
}
