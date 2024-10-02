package ua.acclorite.book_story.presentation.screens.settings.nested.reader.components.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ua.acclorite.book_story.R
import ua.acclorite.book_story.presentation.core.components.LocalMainViewModel
import ua.acclorite.book_story.presentation.data.MainEvent
import ua.acclorite.book_story.presentation.screens.settings.components.SwitchWithTitle

/**
 * Double Click Text To Speech setting.
 * Changes Reader's double click Text To Speech.
 */
@Composable
fun DoubleClickTextToSpeechSetting() {
    val state = LocalMainViewModel.current.state
    val onMainEvent = LocalMainViewModel.current.onEvent

    SwitchWithTitle(
        selected = state.value.doubleClickTextToSpeech,
        title = stringResource(id = R.string.double_click_text_to_speech_option),
        description = stringResource(id = R.string.double_click_text_tp_speech_option_desc),
        onClick = {
            onMainEvent(
                MainEvent.OnChangeDoubleClickTextToSpeech(
                    !state.value.doubleClickTextToSpeech
                )
            )
        }
    )
}