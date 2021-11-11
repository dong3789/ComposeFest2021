package com.example.layoutscodelab

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.rememberCoroutineScope
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsCodelabTheme {
                // A surface container using the 'background' color from the theme
//                LazyList()
                //LayoutsCodelab()
                ScrollingList()
            }
        }
    }
}

/**
 * 레이아웃
 */
//@Preview(showBackground = true)
@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Icon(Icons.Rounded.Search, contentDescription = "search")
                    Text(text = "LayoutsCodelab")
                }
            )
        }
    ) {
        innerPadding ->
//            BodyContent(Modifier.padding(innerPadding).padding(20.dp))
                // 필요할때만 추가 가능
            BodyContent(Modifier.padding(innerPadding))
    }
}


/**
 * 수정자
 */
@Composable
fun BodyContent(modifier: Modifier = Modifier) {
//    Column(modifier = modifier.padding(8.dp)) { //모든 Bodycontent modifier padding을 추가
    Column(modifier = modifier) {
        Text(text = "Hi here")
        Text("This is Layouts codelab")
    }
}

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

//@Preview(showBackground = true)
@Composable
fun PhotographerCardPreview() {
    LayoutsCodelabTheme {
        PhotographerCard()
    }
}


/**
 * 목록 작업
 */
@Composable
fun SimpleList() {
    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text("Item #$it")
        }
    }
}

//@Preview
//@Composable
//fun LazyList() {
//    val scrollState = rememberLazyListState()
//    LazyColumn(state = scrollState) {
//        items(100) {
//            ImageListItem(index = it)
//        }
//    }
//}

@Composable
fun ScrollingList() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scrolling to the Top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text("Scroll to the End")
            }
        }
        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(index = it)
            }
        }
    }
}


@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

