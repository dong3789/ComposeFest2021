package com.example.layoutscodelab

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import com.example.layoutscodelab.ui.theme.LayoutsCodelabTheme
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("BUTTON 1")
        }
        Text("텍스트", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 30.dp)
            //가운데 정렬
//            centerHorizontallyTo(parent)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("BUTTON 2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutContentPreview() {
    LayoutsCodelabTheme {
        ConstraintLayoutContent()
    }
}


// 만약 텍스트가 너무 길경우, 글자가 잘리는 현상을 막히 위해 ConstraintLayout사용
@Composable
fun LargeConstraintLayout() {
    ConstraintLayout() {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "매우 기이이이이이이이이이잉이이이이이이이이이이이이이잉인 텍스트",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)

                // 특정 상황에서 줄바꿈기능능
//               width = Dimension.preferredWrapContent
               // Dimenstion을 강제로 줄바꿈 가능
               width = Dimension.preferredWrapContent.atLeast(200.dp)
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun LargeConstraintLayoutPreview() {
    LayoutsCodelabTheme {
        LargeConstraintLayout()
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if(maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp)
        }else{
            decoupledConstraints(margin = 33.dp)
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }

    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("teeeext")


        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DecoupledConstraintLayoutPreview() {
    LayoutsCodelabTheme {
        DecoupledConstraintLayout()
    }
}