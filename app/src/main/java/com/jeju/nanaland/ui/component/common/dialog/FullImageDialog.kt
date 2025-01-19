package com.jeju.nanaland.ui.component.common.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.Dialog
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideImageState

//@Composable
//fun FullImageDialog(url: String) {
//    Image()
//}
//
//
//
//@Composable
//private fun TransformableDialog(imageView: @Composable ()->Unit) {
//    var scale by remember { mutableFloatStateOf(1f) }
//    var offset by remember { mutableStateOf(Offset.Zero) }
//    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
//        scale *= zoomChange
//        offset += offsetChange
//
//        if(scale <= 1f) scale = 1f
//    }
//    Box(
//        Modifier
//            // apply other transformations like rotation and zoom
//            // on the pizza slice emoji
//            .graphicsLayer(
//                scaleX = scale,
//                scaleY = scale,
//                translationX = offset.x,
//                translationY = offset.y
//            )
//            // add transformable to listen to multitouch transformation events
//            // after offset
//            .transformable(state = state)
//            .background(Color.Blue)
//            .fillMaxSize()
//    )
//
//}
@Composable
fun FullImageDialog(
    images: List<String>,
    onDismiss: () -> Unit
) {
    WrapperView(onDismiss) {
        NetworkImagePager(images)
    }
}

@Composable
private fun WrapperView(onDismiss: () -> Unit, content: @Composable () -> Unit){
    Dialog (onDismiss) {
        NanaLandTheme {
            Box() {
                content()

            }
        }
    }
}

@Composable
private fun NetworkImagePager(
    images: List<String>
) {
    val pagerState = rememberPagerState { images.size }
    val imageSizes = remember { mutableStateListOf(*Array(images.size) { IntSize.Zero })}

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        ZoomableImageWithTransformable(
            imageSize = imageSizes[page],
            modifier = Modifier.fillMaxSize()
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                imageModel = { images[page] },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit
                ),
                onImageStateChanged =  {
                    if(it is GlideImageState.Success) {
                        it.imageBitmap?.let { bitmap ->
                            imageSizes[page] = IntSize(bitmap.width, bitmap.height)
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun ZoomableImageWithTransformable(
    imageSize: IntSize,
    modifier: Modifier = Modifier,
    imageView: @Composable BoxScope.() -> Unit
) {
    val scale = remember { mutableFloatStateOf(1f) }
    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }

    val viewSize = remember { mutableStateOf(IntSize.Zero) }

    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        // 확대/축소 제한
        val newScale = (scale.floatValue * zoomChange).coerceIn(1f, 5f) // 최소, 최대 스케일링
        scale.floatValue = newScale

        // 이동 제한
        val maxX = (imageSize.width * newScale - viewSize.value.width) / 2
        val maxY = (imageSize.height * newScale - viewSize.value.height) / 2

        offsetX.floatValue = (offsetX.floatValue + offsetChange.x).coerceIn(-maxX, maxX)
        offsetY.floatValue = (offsetY.floatValue + offsetChange.y).coerceIn(-maxY, maxY)
    }

    Box(
        modifier = modifier
            .background(Color.Black)
            .onGloballyPositioned { coordinates ->
                viewSize.value = coordinates.size
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { tapOffset ->
                        val newScale = if (scale.value > 1f) 1f else 2.5f // 확대/축소 토글
                        val scaleFactor = newScale / scale.value

                        // 터치 좌표를 중심으로 오프셋 조정
                        offsetX.value = (offsetX.value - tapOffset.x) * scaleFactor + tapOffset.x
                        offsetY.value = (offsetY.value - tapOffset.y) * scaleFactor + tapOffset.y

                        // 스케일 업데이트
                        scale.value = newScale
                    }
                )
            }
            .transformable(
                state = state,
                lockRotationOnZoomPan = true
            )
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
                translationX = offsetX.value,
                translationY = offsetY.value,
            ),
        content = imageView
    )
}
