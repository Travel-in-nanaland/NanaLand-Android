package com.jeju.nanaland.globalvalue.type

import com.google.gson.annotations.SerializedName

sealed interface ReviewKeyword {
    val stringIndex: Int

    enum class Mood: ReviewKeyword {
        @SerializedName("ANNIVERSARY")
        ANNIVERSARY { override val stringIndex = 0 },
        @SerializedName("CUTE")
        CUTE { override val stringIndex = 1 },
        @SerializedName("LUXURY")
        LUXURY { override val stringIndex = 2 },
        @SerializedName("SCENERY")
        SCENERY { override val stringIndex = 3 },
        @SerializedName("KIND")
        KIND { override val stringIndex = 4 },
    }
    enum class With: ReviewKeyword {
        @SerializedName("CHILDREN")
        CHILDREN { override val stringIndex = 5 },
        @SerializedName("FRIEND")
        FRIEND { override val stringIndex = 6 },
        @SerializedName("PARENTS")
        PARENTS { override val stringIndex = 7 },
        @SerializedName("ALONE")
        ALONE { override val stringIndex = 8 },
        @SerializedName("HALF")
        HALF { override val stringIndex = 9 },
        @SerializedName("RELATIVE")
        RELATIVE { override val stringIndex = 10 },
        @SerializedName("PET")
        PET { override val stringIndex = 11 },
    }
    enum class Infra: ReviewKeyword {
        @SerializedName("OUTLET")
        OUTLET { override val stringIndex = 12 },
        @SerializedName("LARGE")
        LARGE { override val stringIndex = 13 },
        @SerializedName("PARK")
        PARK { override val stringIndex = 14 },
        @SerializedName("BATHROOM")
        BATHROOM { override val stringIndex = 15 },
    }
}

