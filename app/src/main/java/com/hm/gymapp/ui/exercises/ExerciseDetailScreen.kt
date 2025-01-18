import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.hm.gymapp.R

@Composable
fun ExerciseDetailScreen() {
    // Dane ćwiczenia - przysiady
    val exercise = ExerciseDetail(
        name = "Squats",
        videoRes = R.raw.squat,  // Zastąp nazwą swojego pliku wideo .mov w katalogu raw
        description = "Squats are a fundamental exercise that targets the lower body, specifically the quadriceps, hamstrings, and glutes. Proper form is essential for maximizing benefits and preventing injury.",
        muscleGroups = "Quadriceps, Hamstrings, Glutes, Core",
        steps = listOf(
            "Stand with your feet shoulder-width apart and your toes slightly pointed outward.",
            "Keep your back straight and chest lifted. Engage your core muscles.",
            "Slowly bend your knees and lower your hips down as if you are sitting in a chair.",
            "Make sure your knees do not go past your toes. Lower yourself until your thighs are parallel to the ground or deeper if possible.",
            "Push through your heels to rise back up to the starting position, keeping your core tight."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tytuł ćwiczenia
        Text(
            text = exercise.name,
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Wideo ćwiczenia
        VideoPlayer(videoRes = exercise.videoRes)

        // Opis ćwiczenia
        Text(
            text = "How to perform Squats:",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = exercise.description,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Partie mięśniowe
        Text(
            text = "Muscle Groups Engaged:",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = exercise.muscleGroups,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Kroki wykonania ćwiczenia
        Text(
            text = "Steps to Perform Squats:",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            exercise.steps.forEachIndexed { index, step ->
                Text(
                    text = "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun VideoPlayer(videoRes: Int) {
    val context = LocalContext.current
    val videoUri = "android.resource://${context.packageName}/$videoRes"

    AndroidView(
        factory = { ctx ->
            VideoView(ctx).apply {
                setVideoURI(Uri.parse(videoUri))
                start() // Automatyczne odtwarzanie wideo
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(bottom = 16.dp)
    )
}

// Klasa Exercise zawierająca dane ćwiczenia
data class ExerciseDetail(
    val name: String,
    val videoRes: Int,
    val description: String,
    val muscleGroups: String,
    val steps: List<String>
)
