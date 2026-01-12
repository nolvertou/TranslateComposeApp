package nou.com.example.translatecomposeapp.translator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContract
import java.util.Locale

class SpeechRecognizerContract : ActivityResultContract<Unit, ArrayList<String>?>(){

    // Function createIntent is
    // Called when the speech recognizer is launched
    // Responsible for building the Intent
    override fun createIntent(
        context: Context,
        input: Unit
    ): Intent {
        // Start the system speech recognition activity”
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        // Set language model, Specifies how speech should be interpreted
        // LANGUAGE_MODEL_WEB_SEARCH: Better for short phrases
        // LANGUAGE_MODEL_FREE_FORM: Better for longer phrases
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
        // Set recognition language, Uses the device’s current language
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        // Text shown to the user while speaking
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "")
        // Android launches the speech recognition UI
        return intent
    }

    // Called after the speech recognizer finishes
    // Converts raw activity result into your output type
    override fun parseResult(
        resultCode: Int,
        intent: Intent?
    ): ArrayList<String>? {
        // It returns null if:
        // User canceled or error occurred,
        if(resultCode != Activity.RESULT_OK){
            return null
        }

        // Extract recognized text
        return intent?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
    }

}