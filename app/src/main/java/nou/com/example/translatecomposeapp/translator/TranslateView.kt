package nou.com.example.translatecomposeapp.translator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

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
    }
}