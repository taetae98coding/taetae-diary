package com.taetae98.diary.core.compose.memo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.taetae98.diary.core.model.memo.Memo
import com.valentinilk.shimmer.shimmer

@Composable
internal fun MemoCompose(
    modifier: Modifier = Modifier,
    memo: Memo?,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.run {
            if (memo == null) {
                shimmer()
            } else {
                this
            }
        },
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = memo?.title.orEmpty(),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}
