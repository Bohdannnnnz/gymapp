import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hm.gymapp.R

data class Trainer(val name: String, val imageRes: Int, val specialties: List<String>, val description: String)

@Composable
fun TrainerSelectionScreen() {
    // Lista trenerów zainicjowana wewnątrz widoku
    val trainers = getSampleTrainers()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choose Your Trainer",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp),
            fontSize = 24.sp
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(trainers) { trainer ->
                TrainerCard(trainer = trainer, onClick = { /* Dodaj akcję dla przycisku */ })
            }
        }
    }
}

@Composable
fun TrainerCard(trainer: Trainer, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Pierwszy rząd: zdjęcie trenera, imię i specjalizacje
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = trainer.imageRes),
                    contentDescription = trainer.name,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                )
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = trainer.name,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = trainer.specialties.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp)) // Odstęp między sekcją specjalizacji a opisem trenera
            // Drugi rząd: opis trenera
            Text(
                text = trainer.description,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(16.dp)) // Odstęp przed przyciskiem
            // Przycisk Select Trainer
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Select Trainer")
            }
        }
    }
}

fun getSampleTrainers(): List<Trainer> {
    return listOf(
        Trainer(
            name = "John Doe",
            imageRes = R.drawable.avatar4, // Zastąp nazwą swojego pliku PNG
            specialties = listOf("Strength Training", "Weight Loss"),
            description = "John specializes in strength training and weight loss programs. He is a certified fitness coach."
        ),
        Trainer(
            name = "Jane Smith",
            imageRes = R.drawable.avatar2, // Zastąp nazwą swojego pliku PNG
            specialties = listOf("Yoga", "Flexibility Training"),
            description = "Jane is an expert in yoga and flexibility training. She helps clients achieve mental and physical balance."
        ),
        Trainer(
            name = "Mike Johnson",
            imageRes = R.drawable.avatar3, // Zastąp nazwą swojego pliku PNG
            specialties = listOf("HIIT", "Endurance Training"),
            description = "Mike is certified in high-intensity interval training and endurance programs. He pushes clients to their limits."
        ),
        Trainer(
            name = "Emily Brown",
            imageRes = R.drawable.avatar1, // Zastąp nazwą swojego pliku PNG
            specialties = listOf("Cardio", "Running Coach"),
            description = "Emily is passionate about cardio workouts and running. She focuses on improving endurance and speed."
        ),
        Trainer(
            name = "David Wilson",
            imageRes = R.drawable.avatar5, // Zastąp nazwą swojego pliku PNG
            specialties = listOf("Bodybuilding", "Muscle Gain"),
            description = "David is a bodybuilding expert who helps clients gain muscle mass and improve strength with customized programs."
        )
    )
}
