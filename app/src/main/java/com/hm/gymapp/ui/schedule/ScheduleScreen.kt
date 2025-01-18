import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hm.gymapp.R // Zamień na odpowiednią ścieżkę zasobów

@Composable
fun ScheduleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tytuł
        Text(
            text = "Schedule",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Zdjęcie
        Image(
            painter = painterResource(id = R.drawable.calendar), // Zastąp nazwą swojego pliku obrazu
            contentDescription = "Schedule Image",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp) // Minimalna wysokość obrazu, aby wypełniał szerokość
        )
    }
}
