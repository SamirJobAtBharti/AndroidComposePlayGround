package com.samirapp.composeandroidplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samirapp.composeandroidplayground.ui.theme.ComposeAndroidPlayGroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAndroidPlayGroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding->
                    NavigationDrawerExample()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/*

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeAndroidPlayGroundTheme {
        Greeting("Android")
    }
}
*/

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Counter() {
    // Define a state variable for the count
    val count = remember { mutableIntStateOf(0) }

    // Use SideEffect to log the current value of count
    SideEffect {
        // Called on every recomposition
        println("Count is ${count.intValue}")
    }

    Column  {
        Button (onClick = { count.intValue++ }) {
            Text("Increase Count  ${count.intValue}")
        }

        // With every state update, text is changed and recomposition is triggered
        Text("Counter ${count.intValue}")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleChoiceSegmentedButton() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Day", "Month", "Week", "Year", "All")

    SingleChoiceSegmentedButtonRow(modifier = Modifier.padding(0.dp, 16.dp)){
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = { selectedIndex = index
                          Log.d("Test", options[index])},
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}