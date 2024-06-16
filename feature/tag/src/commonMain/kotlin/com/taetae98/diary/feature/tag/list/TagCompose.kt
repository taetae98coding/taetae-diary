package com.taetae98.diary.feature.tag.list

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
        Text(
            modifier = Modifier.padding(12.dp),
            text = tag?.title.orEmpty(),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
