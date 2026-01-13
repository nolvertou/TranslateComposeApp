package nou.com.example.translatecomposeapp.translator

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import java.util.Locale

class TranslateViewModel : ViewModel() {
    var state by mutableStateOf(TranslateState())
        private set

    fun onValue(text: String){
        state = state.copy(textToTranslate = text)
    }

    val languageOptions = listOf(
        TranslateLanguage.SPANISH,
        TranslateLanguage.ENGLISH,
        TranslateLanguage.ITALIAN,
        TranslateLanguage.FRENCH,
        TranslateLanguage.GERMAN
    )

    val itemSelection = listOf(
        "SPANISH",
        "ENGLISH",
        "ITALIAN",
        "FRENCH",
        "GERMAN"
    )

    val itemsVoice = listOf(
        Locale.ROOT,
        Locale.ENGLISH,
        Locale.ITALIAN,
        Locale.FRANCE,
        Locale.GERMAN
    )

    fun onTranslate(text: String, context: Context, sourceLang: String, targetLang: String){
        val options = TranslatorOptions
            .Builder()
            .setSourceLanguage(sourceLang)
            .setTargetLanguage(targetLang)
            .build()

        val languageTranslator = Translation.getClient(options)

        languageTranslator.translate(text)
            .addOnSuccessListener {translateText ->
                state = state.copy(
                    translateText = translateText
                )

            }.addOnFailureListener{
                Toast.makeText(context, "Downloading Model...", Toast.LENGTH_SHORT).show()
                downloadingModel(languageTranslator, context, text)
            }
    }

    private fun downloadingModel(languageTranslator: Translator, context: Context, text: String){
        state = state.copy(
            isDownloading = true
        )
        val conditions = DownloadConditions
            .Builder()
            .requireWifi()
            .build()

        languageTranslator
            .downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                Toast.makeText(context, "Model was downloaded successfully", Toast.LENGTH_SHORT).show()

                // Retry translation now that model is ready
                languageTranslator.translate(text)
                    .addOnSuccessListener {translatedText ->
                        state = state.copy(
                            translateText = translatedText,
                            isDownloading = false
                        )
                    }
                    .addOnFailureListener{ error ->
                        Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                        state = state.copy(isDownloading = false)
                    }
            }
            .addOnFailureListener{
                Toast.makeText(context, "Failed model download", Toast.LENGTH_SHORT).show()
            }
    }

    fun clean(){
        state = state.copy(
            textToTranslate = "",
            translateText = ""
        )
    }
    //  It's initialized to null and will hold the instance of the TextToSpeech engine.
    private var textToSpeech: TextToSpeech? = null

    fun textToSpeech(context: Context, voice: Locale){
        textToSpeech = TextToSpeech(context){
            // This checks if the TextToSpeech engine was successfully initialized.
            if(it == TextToSpeech.SUCCESS){
                // This is a safe call to execute the block of code only if textToSpeech is not null.
                textToSpeech?.let { txtToSpeech ->
                    // This sets the language for the speech synthesis.
                    // Locale.ROOT represents a language-neutral locale.
                    txtToSpeech.language = voice
                    // This sets the speed at which the text will be spoken. 1.0f is the normal speech rate.
                    txtToSpeech.setSpeechRate(1.0f)
                    // This speaks the text stored in the state.translateText variable.
                    txtToSpeech.speak(
                        state.translateText, // This is the text that will be spoken,
                        // This queuing strategy adds the new utterance to the end of the playback queue.
                        TextToSpeech.QUEUE_ADD, //
                        null,
                        null
                    )

                }
            }
        }
    }

}