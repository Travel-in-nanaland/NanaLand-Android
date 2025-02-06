package com.jeju.nanaland.ui.component.common.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideImageState

@Composable
fun FullImageDialog(
    image: String,
    onDismiss: () -> Unit
) {
    val scale = remember { mutableFloatStateOf(1f) }
    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }

    val imageSize = remember { mutableStateOf(IntSize.Zero)}
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val viewSize = remember { mutableStateOf(IntSize(displayMetrics.widthPixels, displayMetrics.heightPixels)) }

    Dialog (onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(Modifier
            .fillMaxSize()
            .background(Color.Black)
            .transformable(
                state = rememberTransformableState { zoomChange, offsetChange, _ ->
                    // 확대/축소 제한
                    val newScale = (scale.floatValue * zoomChange).coerceIn(1f, 5f) // 최소, 최대 스케일링
                    scale.floatValue = newScale

                    // 이동 제한
                    val maxX = maxOf((imageSize.value.width * newScale - viewSize.value.width) / 2, 0f)
                    val maxY = maxOf((imageSize.value.height * newScale - viewSize.value.height) / 2, 0f)

                    offsetX.floatValue = (offsetX.floatValue + offsetChange.x).coerceIn(-maxX, maxX)
                    offsetY.floatValue = (offsetY.floatValue + offsetChange.y).coerceIn(-maxY, maxY)
                }
            )
            .graphicsLayer(
                scaleX = scale.floatValue,
                scaleY = scale.floatValue,
                translationX = offsetX.floatValue,
                translationY = offsetY.floatValue,
            )
        ) {
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                imageModel = { image },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Fit
                ),
                requestOptions = {
                    RequestOptions().downsample(DownsampleStrategy.CENTER_INSIDE)
                },
                onImageStateChanged =  {
                    if(it is GlideImageState.Success) {
                        it.imageBitmap?.let { bitmap ->
                            imageSize.value = IntSize(bitmap.width, bitmap.height)
                        }
                    }
                }
            )
        }
        Icon(
            modifier = Modifier
                .padding(12.dp)
                .size(36.dp)
                .clickableNoEffect(onDismiss)
                .padding(6.dp),
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = getColor().white
        )
    }
}
//
//@Composable
//private fun WrapperView(
//    state: PagerState,
//    onDismiss: () -> Unit,
//    content: @Composable () -> Unit
//) {
//    Dialog (onDismiss,
//        properties = DialogProperties(usePlatformDefaultWidth = false)
//    ) {
//        Box {
//            content()
//            Icon(
//                modifier = Modifier
//                    .padding(12.dp)
//                    .size(24.dp)
//                    .clickableNoEffect(onDismiss),
//                imageVector = Icons.Default.Close,
//                contentDescription = null,
//                tint = getColor().white
//            )
//            Row(
//                Modifier
//                    .wrapContentHeight()
//                    .fillMaxWidth()
//                    .align(Alignment.BottomCenter)
//                    .padding(bottom = 8.dp),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                repeat(state.pageCount) { iteration ->
//                    val color = if (state.currentPage == iteration) Color.DarkGray else Color.LightGray
//                    Box(
//                        modifier = Modifier
//                            .padding(2.dp)
//                            .clip(CircleShape)
//                            .background(color)
//                            .size(16.dp)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun NetworkImagePager(
//    state: PagerState,
//    images: List<String>
//) {
//    val imageSizes = remember { mutableStateListOf(*Array(images.size) { IntSize.Zero })}
//
//    HorizontalPager(
//        state = state,
//        modifier = Modifier.fillMaxSize()
//    ) { page ->
//        ZoomableImageWithTransformable(
//            imageSize = imageSizes[page],
//            modifier = Modifier.fillMaxSize()
//        ) {
//            GlideImage(
//                modifier = Modifier.fillMaxSize(),
//                imageModel = { images[page] },
//                imageOptions = ImageOptions(
//                    contentScale = ContentScale.Fit
//                ),
//                onImageStateChanged =  {
//                    if(it is GlideImageState.Success) {
//                        it.imageBitmap?.let { bitmap ->
//                            imageSizes[page] = IntSize(bitmap.width, bitmap.height)
//                        }
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//private fun ZoomableImageWithTransformable(
//    imageSize: IntSize,
//    modifier: Modifier = Modifier,
//    imageView: @Composable BoxScope.() -> Unit
//) {
//    val scale = remember { mutableFloatStateOf(1f) }
//    val offsetX = remember { mutableFloatStateOf(0f) }
//    val offsetY = remember { mutableFloatStateOf(0f) }
//
//    val viewSize = remember { mutableStateOf(IntSize.Zero) }
//
//    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
//        // 확대/축소 제한
//        val newScale = (scale.floatValue * zoomChange).coerceIn(1f, 5f) // 최소, 최대 스케일링
//        scale.floatValue = newScale
//
//        // 이동 제한
//        val maxX = (imageSize.width * newScale - viewSize.value.width) / 2
//        val maxY = (imageSize.height * newScale - viewSize.value.height) / 2
//
//        offsetX.floatValue = (offsetX.floatValue + offsetChange.x).coerceIn(-maxX, maxX)
//        offsetY.floatValue = (offsetY.floatValue + offsetChange.y).coerceIn(-maxY, maxY)
//    }
//
//    Box(
//        modifier = modifier
//            .background(Color.Black)
//            .onGloballyPositioned { coordinates ->
//                viewSize.value = coordinates.size
//            }
//            .transformable(
//                state = state
//            )
//            .graphicsLayer(
//                scaleX = scale.value,
//                scaleY = scale.value,
//                translationX = offsetX.value,
//                translationY = offsetY.value,
//            ),
//        content = imageView
//    )
//}