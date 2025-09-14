package nou.com.example.translatecomposeapp.translator

data class TranslateState(
    val textToTranslate: String = "",
    val translateText  : String = "",
    val isDownloading  : Boolean = false
)
