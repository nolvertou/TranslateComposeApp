package nou.com.example.translatecomposeapp.views.languages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch
import nou.com.example.translatecomposeapp.R

@Composable
fun LanguagesView(){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = LanguageStore(context)

    // get index language from dataStore and save it at indexLang,
    // initial value will be zero
    val indexLang by dataStore.getIndexLang.collectAsState(initial = 0)

    // Dropdown Variables
    val items = listOf("English", "Spanish")
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }

    var languages = getStrings()
    var currentLanguage by remember { mutableStateOf(languages[indexLang]) }

    LaunchedEffect(indexLang) {
        currentLanguage = languages[indexLang]
        selectedIndex = indexLang
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = items[selectedIndex])
            IconButton(onClick = { expanded = true}) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "DropDown Icon")
            }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                items.forEachIndexed{ index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedIndex = index
                            currentLanguage = languages[index]
                            scope.launch {
                                dataStore.saveIndexLang(index)
                            }

                            expanded = false
                        })

                }
            }
        }

        Text( text = currentLanguage["title"].toString(), fontWeight = FontWeight.Bold)
        Text( text = currentLanguage["subtitle"].toString())
    }

}