package nou.com.example.translatecomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import nou.com.example.translatecomposeapp.ui.theme.TranslateComposeAppTheme
import nou.com.example.translatecomposeapp.translator.TranslateView
import nou.com.example.translatecomposeapp.translator.TranslateViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel : TranslateViewModel by viewModels()
        setContent {
            TranslateComposeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MyView()
                    //LanguagesView()
                    TranslateView(viewModel)
                }
            }
        }
    }
}

@Composable
fun MyView(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( text = stringResource(id = R.string.title), fontWeight = FontWeight.Bold)
        Text( text = stringResource(id = R.string.subtitle))
    }
}