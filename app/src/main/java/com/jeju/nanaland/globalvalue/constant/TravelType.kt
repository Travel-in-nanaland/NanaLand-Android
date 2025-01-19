package com.jeju.nanaland.globalvalue.constant

import com.google.gson.annotations.SerializedName
import com.jeju.nanaland.R
import com.jeju.nanaland.util.resource.getString

enum class TravelType {
    @SerializedName("GAMGYUL_ICECREAM")
    GAMGYUL_ICECREAM,

    @SerializedName("GAMGYUL_RICECAKE")
    GAMGYUL_RICECAKE,

    @SerializedName("GAMGYUL")
    GAMGYUL,

    @SerializedName("GAMGYUL_CIDER")
    GAMGYUL_CIDER,

    @SerializedName("GAMGYUL_AFFOKATO")
    GAMGYUL_AFFOKATO,

    @SerializedName("GAMGYUL_HANGWA")
    GAMGYUL_HANGWA,

    @SerializedName("GAMGYUL_JUICE")
    GAMGYUL_JUICE,

    @SerializedName("GAMGYUL_CHOCOLATE")
    GAMGYUL_CHOCOLATE,

    @SerializedName("GAMGYUL_COCKTAIL")
    GAMGYUL_COCKTAIL,

    @SerializedName("TANGERINE_PEEL_TEA")
    TANGERINE_PEEL_TEA,

    @SerializedName("GAMGYUL_YOGURT")
    GAMGYUL_YOGURT,

    @SerializedName("GAMGYUL_FLATCCINO")
    GAMGYUL_FLATCCINO,

    @SerializedName("GAMGYUL_LATTE")
    GAMGYUL_LATTE,

    @SerializedName("GAMGYUL_SIKHYE")
    GAMGYUL_SIKHYE,

    @SerializedName("GAMGYUL_ADE")
    GAMGYUL_ADE,

    @SerializedName("GAMGYUL_BUBBLE_TEA")
    GAMGYUL_BUBBLE_TEA,
}

fun TravelType.toViewString(): String = when (this) {
    TravelType.GAMGYUL_ICECREAM -> getString(R.string.type_감귤_아이스크림)
    TravelType.GAMGYUL_RICECAKE -> getString(R.string.type_감귤_찹쌀떡)
    TravelType.GAMGYUL -> getString(R.string.type_감귤)
    TravelType.GAMGYUL_CIDER -> getString(R.string.type_감귤_사이다)
    TravelType.GAMGYUL_AFFOKATO -> getString(R.string.type_감귤_아포가토)
    TravelType.GAMGYUL_HANGWA -> getString(R.string.type_감귤_한과)
    TravelType.GAMGYUL_JUICE -> getString(R.string.type_감귤_주스)
    TravelType.GAMGYUL_CHOCOLATE -> getString(R.string.type_감귤_초콜릿)
    TravelType.GAMGYUL_COCKTAIL -> getString(R.string.type_감귤_칵테일)
    TravelType.TANGERINE_PEEL_TEA -> getString(R.string.type_귤피차)
    TravelType.GAMGYUL_YOGURT -> getString(R.string.type_감귤_요거트)
    TravelType.GAMGYUL_FLATCCINO -> getString(R.string.type_감귤_플랫치노)
    TravelType.GAMGYUL_LATTE -> getString(R.string.type_감귤_라떼)
    TravelType.GAMGYUL_SIKHYE -> getString(R.string.type_감귤_식혜)
    TravelType.GAMGYUL_ADE -> getString(R.string.type_감귤_에이드)
    TravelType.GAMGYUL_BUBBLE_TEA -> getString(R.string.type_감귤_버블티)
}

fun String.toTravelType(): TravelType = when (this) {
    getString(R.string.type_감귤_아이스크림) -> TravelType.GAMGYUL_ICECREAM
    getString(R.string.type_감귤_찹쌀떡) -> TravelType.GAMGYUL_RICECAKE
    getString(R.string.type_감귤) -> TravelType.GAMGYUL
    getString(R.string.type_감귤_사이다) -> TravelType.GAMGYUL_CIDER
    getString(R.string.type_감귤_아포가토) -> TravelType.GAMGYUL_AFFOKATO
    getString(R.string.type_감귤_한과) -> TravelType.GAMGYUL_HANGWA
    getString(R.string.type_감귤_주스) -> TravelType.GAMGYUL_JUICE
    getString(R.string.type_감귤_초콜릿) -> TravelType.GAMGYUL_CHOCOLATE
    getString(R.string.type_감귤_칵테일) -> TravelType.GAMGYUL_COCKTAIL
    getString(R.string.type_귤피차) -> TravelType.TANGERINE_PEEL_TEA
    getString(R.string.type_감귤_요거트) -> TravelType.GAMGYUL_YOGURT
    getString(R.string.type_감귤_플랫치노) -> TravelType.GAMGYUL_FLATCCINO
    getString(R.string.type_감귤_라떼) -> TravelType.GAMGYUL_LATTE
    getString(R.string.type_감귤_식혜) -> TravelType.GAMGYUL_SIKHYE
    getString(R.string.type_감귤_에이드) -> TravelType.GAMGYUL_ADE
    getString(R.string.type_감귤_버블티) -> TravelType.GAMGYUL_BUBBLE_TEA
    else -> throw Exception("String.toTravelType Exception ($this)")
}

fun TravelType.toImageRes(): Int = when (this) {
    TravelType.GAMGYUL_ICECREAM -> R.drawable.img_type_gamgyul_icecream
    TravelType.GAMGYUL_RICECAKE -> R.drawable.img_type_gamgyul_ricecake
    TravelType.GAMGYUL -> R.drawable.img_type_gamgyul
    TravelType.GAMGYUL_CIDER -> R.drawable.img_type_gamgyul_cider
    TravelType.GAMGYUL_AFFOKATO -> R.drawable.img_type_gamgyul_affokato
    TravelType.GAMGYUL_HANGWA -> R.drawable.img_type_hangwa
    TravelType.GAMGYUL_JUICE -> R.drawable.img_type_gamgyul_juice
    TravelType.GAMGYUL_CHOCOLATE -> R.drawable.img_type_gamgyul_chocolate
    TravelType.GAMGYUL_COCKTAIL -> R.drawable.img_type_gamgyul_cocktail
    TravelType.TANGERINE_PEEL_TEA -> R.drawable.img_type_tangerine_peel_tea
    TravelType.GAMGYUL_YOGURT -> R.drawable.img_type_gamgyul_yogurt
    TravelType.GAMGYUL_FLATCCINO -> R.drawable.img_type_gamgyul_flatccino
    TravelType.GAMGYUL_LATTE -> R.drawable.img_type_gamgyul_latte
    TravelType.GAMGYUL_SIKHYE -> R.drawable.img_type_gamgyul_sikhye
    TravelType.GAMGYUL_ADE -> R.drawable.img_type_gamgyul_ade
    TravelType.GAMGYUL_BUBBLE_TEA -> R.drawable.img_type_gamgyul_bubble_tea
}