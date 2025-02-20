package com.example.layoutscodelab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutscodelab.ui.theme.LayoutsCodelabTheme



/**
 * 슬롯 API
 */
@Composable
fun PhotographerCard(modifier : Modifier = Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .clickable(onClick = { })
            .padding(16.dp) //padding 순서를 변경하여 클릭시 전체가 포함되도록 적용
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.surface)

    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ){
            // todo:: image here
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
                .background(
                    MaterialTheme.colors.primary
                )
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes age", style = MaterialTheme.typography.body2)
            }

        }
    }

}



@Preview(showBackground = true)
@Composable
fun PhotographerCardPreview() {
    LayoutsCodelabTheme {
        PhotographerCard()
    }
}

