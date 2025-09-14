package nou.com.example.translatecomposeapp.translator

import android.text.Selection
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DropdownLang(
    itemSelection: List<String>,
    selectedIndex: Int,
    expand: Boolean,
    onCLickExpanded: () -> Unit,
    onClickDismiss: () -> Unit,
    onClickItem: (index: Int) -> Unit
) {
    Box(){
        OutlinedButton(onClick = { onCLickExpanded() }) {
            Text(text = itemSelection[selectedIndex])
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Arrow DropDown")
        }

        DropdownMenu(expanded = expand, onDismissRequest = { onClickDismiss() }) {
            itemSelection.forEachIndexed{ index, item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = { onClickItem(index) }
                )
            }
        }
    }
}