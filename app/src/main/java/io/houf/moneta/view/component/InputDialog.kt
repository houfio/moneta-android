package io.houf.moneta.view.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import io.houf.moneta.R

@Composable
fun InputDialog(
    title: String,
    initial: String,
    onDismiss: (String) -> Unit,
    onValidate: ((String) -> Boolean)? = null
) {
    var text by remember { mutableStateOf(initial) }

    Dialog({ onDismiss(initial) }) {
        Surface(elevation = 16.dp, shape = RoundedCornerShape(6.dp)) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                TextField(
                    value = text,
                    onValueChange = { value ->
                        if (value.isEmpty() || onValidate?.invoke(value) != false) {
                            text = value
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions { onDismiss(text.ifEmpty { initial }) },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(Modifier.padding(top = 8.dp)) {
                    Spacer(Modifier.weight(1.0f))
                    TextButton({ onDismiss(text.ifEmpty { initial }) }) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}
