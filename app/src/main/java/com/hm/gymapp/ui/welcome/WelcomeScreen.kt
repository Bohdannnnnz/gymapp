import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import com.hm.gymapp.R

@Composable
fun WelcomeScreen(
    onLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Wyświetlenie obrazu PNG
        Image(
            painter = painterResource(id = R.drawable.welcome_logo), // Zastąp `welcome_logo` nazwą swojego pliku PNG
            contentDescription = "Logo", // Opis dla dostępności
            modifier = Modifier
                .fillMaxWidth(0.8f) // Obraz zajmuje 80% szerokości ekranu
                .padding(bottom = 48.dp) // Zwiększony odstęp od przycisków
        )
        // Kolumna z przyciskami
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onLogin,
                modifier = Modifier.fillMaxWidth().height(56.dp) // Powiększenie przycisku
            ) {
                Text("Dive in!", fontSize = 18.sp)
            }
        }
    }
}
