package ua.acclorite.book_story.presentation.screens.reader.components.app_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.acclorite.book_story.presentation.core.components.LocalReaderViewModel
import ua.acclorite.book_story.presentation.core.util.noRippleClickable
import ua.acclorite.book_story.presentation.screens.reader.data.ReaderEvent
import ua.acclorite.book_story.presentation.ui.Colors

/**
 * Reader bottom bar text to speech.
 * Has a slider to change progress speed.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderBottomBarTextToSpeech() {
    var sliderValue by remember { mutableFloatStateOf(0f) }
    Column(
        Modifier
            .fillMaxWidth()
            .background(Colors.readerSystemBarsColor)
            .noRippleClickable(onClick = {})
            .navigationBarsPadding()
            .padding(horizontal = 18.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(top = 3.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                CustomSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = sliderValue,
                    onValueChange = {
                        sliderValue = it
                    },
                    valueRange = 0f..50f,
                    gap = 10,
                    showIndicator = true,
                    thumb = { thumbValue ->
                        CustomSliderDefaults.Thumb(
                            thumbValue = "$thumbValue%",
                            color = Color.Transparent,
                            size = 40.dp,
                            modifier = Modifier.background(
                                brush = Brush.linearGradient(listOf(Color.Cyan, Color.Blue)),
                                shape = CircleShape
                            )
                        )
                    },
                    track = { sliderState ->
                        Box(
                            modifier = Modifier
                                .track()
                                .border(
                                    width = 1.dp,
                                    color = Color.LightGray.copy(alpha = 0.4f),
                                    shape = CircleShape
                                )
                                .background(Color.White)
                                .padding(3.5.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .progress(sliderState = sliderState)
                                    .background(
                                        brush = Brush.linearGradient(listOf(Color.Red, Color.Magenta))
                                    )
                            )
                        }
                    }
                )
            }
        }
    }
}


/**
 * Bottom Bar Slider.
 * Has semi-transparent track color.
 */
@Composable
private fun BottomBarSlider() {
    val state = LocalReaderViewModel.current.state
    val onEvent = LocalReaderViewModel.current.onEvent

    Slider(
        value = state.value.speedRate,
        onValueChange = {
            onEvent(ReaderEvent.OnScrollTTS(it))
            onEvent(
                ReaderEvent.OnChangeProgressTTS(
                    progress = it
                )
            )
        },
        colors = SliderDefaults.colors(
            inactiveTrackColor = MaterialTheme.colorScheme.secondary.copy(0.15f),
            disabledActiveTrackColor = MaterialTheme.colorScheme.primary,
            disabledThumbColor = MaterialTheme.colorScheme.primary,
            disabledInactiveTrackColor = MaterialTheme.colorScheme.secondary.copy(0.15f),
        )
    )
}

/**
 * Slider Indicator.
 * Shows an indicator at desired progress.
 */
@Composable
private fun SliderIndicator(progress: Float) {
    Row(Modifier.fillMaxWidth()) {
        Spacer(
            modifier = Modifier.fillMaxWidth(progress)
        )
        Box(
            Modifier
                .width(4.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(0.5.dp))
                .background(
                    MaterialTheme.colorScheme.onPrimary.copy(0.6f)
                )
        )
    }
}