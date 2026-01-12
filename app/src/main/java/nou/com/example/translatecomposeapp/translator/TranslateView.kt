package nou.com.example.translatecomposeapp.translator

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import nou.com.example.translatecomposeapp.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun TranslateView(viewModel: TranslateViewModel) {
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val languageOptions = viewModel.languageOptions
    val itemsSelection = viewModel.itemSelection

    var indexSource by remember{ mutableIntStateOf(0) }
    var indexTarget by remember{ mutableIntStateOf(1) }
    var expandSource by remember{ mutableStateOf(false) }
    var expandTarget by remember{ mutableStateOf(false) }
    var selectedSourceLang by remember { mutableStateOf(languageOptions[0]) }
    var selectedTargetLang by remember { mutableStateOf(languageOptions[1]) }

    // It creates a permissionState variable that remembers the state of the RECORD_AUDIO permission.
    val permissionState = rememberPermissionState(permission = Manifest.permission.RECORD_AUDIO)

    // This SideEffect will be executed after the initial composition,
    //  and it will launch a request for the RECORD_AUDIO permission.
    SideEffect {
        permissionState.launchPermissionRequest()
    }

    // - Creates a launcher for the speech recognition activity.
    // - When the speech recognizer returns a result, the onResult lambda is executed.
    // - It takes the result, converts it to a string, removes the brackets,
    //   trims any leading whitespace, and then passes the cleaned-up text to the onValue
    //   function of the viewModel.
    val speechRecognitionLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognizerContract(),
        onResult = {
            viewModel.onValue(it.toString()
                .replace("[","")
                .replace("]", "")
                .trimStart()
            )
        })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            DropdownLang(
                itemSelection = itemsSelection,
                selectedIndex = indexSource,
                expand = expandSource,
                onCLickExpanded = { expandSource = true },
                onClickDismiss = { expandSource = false },
                onClickItem = {index ->
                    indexSource = index
                    selectedSourceLang = languageOptions[index]
                    expandSource = false
                }
            )

            Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "ArrowForward Icon",
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )

            DropdownLang(
                itemSelection = itemsSelection,
                selectedIndex = indexTarget,
                expand = expandTarget,
                onCLickExpanded = { expandTarget = true },
                onClickDismiss = { expandTarget = false },
                onClickItem = {index ->
                    indexTarget = index
                    selectedTargetLang = languageOptions[index]
                    expandTarget = false
                }
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = state.textToTranslate,
            onValueChange = { viewModel.onValue(it)},
            label = { Text(text = "Type something ...")},
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done   // tells the keyboard to show a "Done" button
            ),
            keyboardActions = KeyboardActions(
                // When the user taps the "Done" button on the keyboard, this lambda runs.
                onDone = {
                    viewModel.onTranslate(
                        state.textToTranslate,
                        context,
                        selectedSourceLang,
                        selectedTargetLang
                    )
                }
            ),
            // TODO: Fix issue, outlinedTextFieldColors was not founded
            //colors =  TextFieldDefaults.outlinedTextFieldColors(
            //    focusedIndicatorColor = Color.Transparent,
            //   unfocusedIndicatorColor = Color.Transparent,
            //),
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            // onClick handler for the microphone icon button.
            MainIconButton(icon = Icons.Filled.Mic) {
                // It checks if the RECORD_AUDIO permission has been granted.
                // If it has, it launches the speechRecognitionLauncher.
                // Otherwise, it launches the permission request again.
                if(permissionState.status.isGranted){
                    speechRecognitionLauncher.launch(Unit)
                }else{
                    permissionState.launchPermissionRequest()
                }
            }
            MainIconButton(icon = Icons.Filled.Translate) {
                viewModel.onTranslate(
                    state.textToTranslate,
                    context,
                    selectedSourceLang,
                    selectedTargetLang
                )
            }
            MainIconButton(icon = Icons.AutoMirrored.Filled.VolumeUp) {
                viewModel.textToSpeech(context)
            }
            MainIconButton(icon = Icons.Filled.Delete) {
                viewModel.clean()
            }
        }

        if(state.isDownloading){
            CircularProgressIndicator()
            Text(text = "Downloading Model, wait a moment")
        }else{
            OutlinedTextField(
                value = state.translateText,
                onValueChange = { },
                label = { Text(text = "Translated Text")},
                readOnly = false,
                //TODO  colors ,
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )

        }
    }
}