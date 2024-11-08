package com.jeju.nanaland.globalvalue.type

import com.jeju.nanaland.R


enum class SearchCategoryType(val titleResId: Int) {
    All(R.string.common_전체),
    Nature(R.string.common_7대_자연),
    Festival(R.string.common_축제),
    Market(R.string.common_전통시장),
    Experience(R.string.common_액티비티),//TODO!!!! 삭제
//    Activity(R.string.common_액티비티), //TODO!!!! 추가
//    Art(R.string.common_문화예술),//TODO!!!! 추가
    Restaurant(R.string.common_제주_맛집),
    NanaPick(R.string.common_나나s_Pick)
}