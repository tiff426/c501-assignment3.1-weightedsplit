package com.example.assignment31

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment31.ui.theme.Assignment31Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment31Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    WeightedRow(modifier = Modifier.padding(innerPadding).height(300.dp).fillMaxWidth())
                }
//                WeightedRow(modifier = Modifier.height(300.dp).fillMaxSize())
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment31Theme {
        Greeting("Android")
    }
}

/**
 * The outermost level, WeightedRow is a composable  that represents a row
 * where one section is 25% anf the other is 75%
 * The 75% section is then divided into 3 parts, represented by WeightedColumn
 */
@Composable
fun WeightedRow(modifier: Modifier) {
    Row(modifier = modifier.fillMaxSize()) {
        WeightedChild(text="25% width, 100% height", modifier = Modifier.weight(1f).fillMaxHeight(), backgroundColor = Color.Yellow)
        WeightedColumn(modifier = Modifier.weight(3f).fillMaxHeight())
    }

}

/**
 * WeightedColumn helper to create composable that represents the Column of 3 weighted
 * children to go into largest section of weighted row
 *
 */
@Composable
fun WeightedColumn(modifier: Modifier) { // column needs to be able to be weighted since itll go into row
    // first child
    Column(modifier = modifier.fillMaxSize(),) { // passed in modifier + whatever else
        WeightedChild(
            text="75% width, 20% height",
            modifier = Modifier.weight(2f).fillMaxWidth(),
            backgroundColor = Color.Red
        )
        WeightedChild(
            text="75% width, 30% height",
            modifier = Modifier.weight(3f).fillMaxWidth(),
            backgroundColor = Color.Blue
        )
        WeightedChild(
            text="75% width, 50% height",
            modifier = Modifier.weight(5f).fillMaxWidth(),
            backgroundColor = Color.Green
        )
    }
}

/**
 * Composable to represent a child composable that can be weighted. Makes use of ColumnScope to
 * ensure access to weight modifier
 */
@Composable
fun ColumnScope.WeightedChild(
    text: String, modifier: Modifier, backgroundColor: Color
) {
    Box(
        // passed in modifier + whatever else is specified below
        modifier = modifier
            .fillMaxHeight()
            .background(backgroundColor)
            .border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

/**
 * Composable to represent a child composable that can be weighted. Makes use of RowScope to
 * ensure access to weight modifier
 */
@Composable
fun RowScope.WeightedChild(
    text: String, modifier: Modifier, backgroundColor: Color
) {
    Box(
        // passed in modifier + whatever else is specified below
        modifier = modifier
            .fillMaxHeight()
            .background(backgroundColor)
            .border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

