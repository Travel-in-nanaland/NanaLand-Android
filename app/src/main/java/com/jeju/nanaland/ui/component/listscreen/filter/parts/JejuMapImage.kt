package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R

@Composable
fun JejuMapImage(
    selectedLocationList: SnapshotStateList<Boolean>
) {
    Box(
        modifier = Modifier.height(160.dp)
    ) {
//        Image(
//            modifier = Modifier
//                .padding(start = 16.dp, end = 16.dp)
//                .fillMaxWidth(),
//            painter = painterResource(R.drawable.jeju_map),
//            contentDescription = null,
//            contentScale = ContentScale.FillWidth
//        )

        Image(
            modifier = Modifier
                .width(63.dp)
                .offset((27).dp, (67).dp),
            painter = painterResource(if (selectedLocationList[3]) R.drawable.img_jejumap_1_selected else R.drawable.img_jejumap_1_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(66.dp)
                .offset((63).dp, (42).dp),
            painter = painterResource(if (selectedLocationList[5]) R.drawable.img_jejumap_2_selected else R.drawable.img_jejumap_2_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(68.dp)
                .offset((97).dp, (34).dp),
            painter = painterResource(if (selectedLocationList[1]) R.drawable.img_jejumap_3_selected else R.drawable.img_jejumap_3_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(52.dp)
                .offset((134.8).dp, (23.4).dp),
            painter = painterResource(if (selectedLocationList[0]) R.drawable.img_jejumap_4_selected else R.drawable.img_jejumap_4_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(56.dp)
                .offset((165.7).dp, (12.4).dp),
            painter = painterResource(if (selectedLocationList[2]) R.drawable.img_jejumap_5_selected else R.drawable.img_jejumap_5_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(87.dp)
                .offset((215.2).dp, (9.2).dp),
            painter = painterResource(if (selectedLocationList[4]) R.drawable.img_jejumap_6_selected else R.drawable.img_jejumap_6_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width((58).dp)
                .offset((27.4).dp, (100).dp),
            painter = painterResource(if (selectedLocationList[9]) R.drawable.img_jejumap_7_selected else R.drawable.img_jejumap_7_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(45.dp)
                .offset((80).dp, (90.4).dp),
            painter = painterResource(if (selectedLocationList[10]) R.drawable.img_jejumap_8_selected else R.drawable.img_jejumap_8_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(79.dp)
                .offset((106.4).dp, (83.2).dp),
            painter = painterResource(if (selectedLocationList[8]) R.drawable.img_jejumap_9_selected else R.drawable.img_jejumap_9_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(76.dp)
                .offset((163.2).dp, (77.2).dp),
            painter = painterResource(if (selectedLocationList[11]) R.drawable.img_jejumap_10_selected else R.drawable.img_jejumap_10_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(81.dp)
                .offset((191.3).dp, (54).dp),
            painter = painterResource(if (selectedLocationList[12]) R.drawable.img_jejumap_11_selected else R.drawable.img_jejumap_11_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width((69.8).dp)
                .offset((242.6).dp, (33.4).dp),
            painter = painterResource(if (selectedLocationList[13]) R.drawable.img_jejumap_12_selected else R.drawable.img_jejumap_12_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(32.dp)
                .offset((94).dp, (1).dp),
            painter = painterResource(if (selectedLocationList[7]) R.drawable.img_jejumap_13_selected else R.drawable.img_jejumap_13_unselected),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .width(31.dp)
                .offset((305).dp, (18).dp),
            painter = painterResource(if (selectedLocationList[6]) R.drawable.img_jejumap_14_selected else R.drawable.img_jejumap_14_unselected),
            contentDescription = null
        )
    }

}