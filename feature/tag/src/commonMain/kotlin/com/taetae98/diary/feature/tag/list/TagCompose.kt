package com.taetae98.diary.feature.tag.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.taetae98.diary.core.model.tag.Tag
import com.valentinilk.shimmer.shimmer

@Composable
internal fun TagCompose(
    modifier: Modifier = Modifier,
    tag: Tag?,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.run {
            if (tag == null) {
                shimmer()
            } else {
                this
            }
        },
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .heightIn(min = 50.dp)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = tag?.title.orEmpty(),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
