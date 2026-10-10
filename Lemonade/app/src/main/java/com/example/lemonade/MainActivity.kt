package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezesLeft by remember { mutableStateOf(0) }

    val instructionId = when (currentStep) {
        1 -> R.string.lemon_select
        2 -> R.string.lemon_squeeze
        3 -> R.string.lemon_drink
        else -> R.string.lemon_restart
    }

    val imageId = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val descriptionId = when (currentStep) {
        1 -> R.string.lemon_tree_description
        2 -> R.string.lemon_description
        3 -> R.string.lemonade_description
        else -> R.string.empty_glass_description
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(instructionId),
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(descriptionId),
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(
                        width = 2.dp,
                        color = Color(0xFF69CDD8),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        when (currentStep) {
                            1 -> {
                                currentStep = 2
                                squeezesLeft = (2..4).random()
                            }

                            2 -> {
                                if (squeezesLeft > 1) {
                                    squeezesLeft--
                                } else {
                                    currentStep = 3
                                }
                            }

                            3 -> currentStep = 4
                            4 -> {
                                currentStep = 1
                                squeezesLeft = 0
                            }
                        }
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}