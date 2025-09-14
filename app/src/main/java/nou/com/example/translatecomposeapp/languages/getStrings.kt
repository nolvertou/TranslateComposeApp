package nou.com.example.translatecomposeapp.languages

import androidx.compose.runtime.Composable

@Composable
fun getStrings(): List<Map<String, String>>{

    val en = mapOf(
        "title" to "Hello World",
        "subtitle" to "The World is yours"
    )

    val es = mapOf(
        "title" to "Hola Mundo",
        "subtitle" to "El Mundo es tuyo"
    )

    return listOf(
        en,
        es
    )
}