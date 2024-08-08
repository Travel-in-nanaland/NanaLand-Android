package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.jeju.nanaland.R
import com.jeju.nanaland.util.resource.getDrawable


@Composable
fun JejuMapImage(
    selectedLocationList: SnapshotStateList<Boolean>
) {
    Box {
        Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_unselected)), contentDescription = null)

        if (selectedLocationList[3])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_1)), contentDescription = null)
        if (selectedLocationList[5])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_2)), contentDescription = null)
        if (selectedLocationList[1])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_3)), contentDescription = null)
        if (selectedLocationList[0])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_4)), contentDescription = null)
        if (selectedLocationList[2])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_5)), contentDescription = null)
        if (selectedLocationList[4])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_6)), contentDescription = null)
        if (selectedLocationList[9])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_7)), contentDescription = null)
        if (selectedLocationList[10])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_8)), contentDescription = null)
        if (selectedLocationList[8])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_9)), contentDescription = null)
        if (selectedLocationList[11])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_10)), contentDescription = null)
        if (selectedLocationList[12])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_11)), contentDescription = null)
        if (selectedLocationList[13])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_12)), contentDescription = null)
        if (selectedLocationList[7])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_13)), contentDescription = null)
        if (selectedLocationList[6])
            Image(painter = rememberDrawablePainter(getDrawable(R.drawable.img_jejumap_selected_14)), contentDescription = null)
    }
}