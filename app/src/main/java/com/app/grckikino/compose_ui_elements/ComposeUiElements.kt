package com.app.grckikino.compose_ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.grckikino.R
import com.app.grckikino.models.RoundsModel
import com.app.grckikino.theming_compose.getColorForNumber
import com.app.grckikino.utils.KeysAndConstants
import com.app.grckikino.utils.getHistoryTimeString





@Composable
fun emptyScr(emptyText: String) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = emptyText,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}



@Composable
fun oneResultItem(round: RoundsModel) {
    Column {
        headerResultItem(round)
        gridResultItem(round)
    }

}

@Composable
fun headerResultItem(round: RoundsModel) {
    val formattedText = stringResource(
        id = R.string.header_text_result_page,
        getHistoryTimeString(round.drawTime), round.drawId
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = formattedText,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 12.sp
        )
    }
}

@Composable
fun gridResultItem(round: RoundsModel) {
    val drawnNumbers = round.winningNumbers.list
    val ballsNumb = drawnNumbers.size
    BoxWithConstraints {
        val itemSize = maxWidth / KeysAndConstants.COLUMN_NUMBERS_IN_RESULTS
        Column(
            modifier = Modifier
                .padding(top = 1.dp, bottom = 20.dp)
        ) {
            (0 until ballsNumb).chunked(KeysAndConstants.COLUMN_NUMBERS_IN_RESULTS).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    rowItems.forEach { inex ->
                        val number = drawnNumbers[inex]
                        Box(
                            modifier = Modifier
                                .size(itemSize)
                                .padding(4.dp)
                                .border(
                                    width = 2.dp,
                                    color = getColorForNumber(number),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = number.toString(),
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    }
                }
            }
        }
    }
}

