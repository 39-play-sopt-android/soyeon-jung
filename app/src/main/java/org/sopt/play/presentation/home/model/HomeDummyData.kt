package org.sopt.play.presentation.home.model

import org.sopt.play.R

object HomeDummyData {
    val menus = listOf(
        "2배적립",
        "홈",
        "셀렉티드",
        "브랜드",
        "뷰티",
        "라이프",
        "랭킹",
    )

    val banners = listOf(
        R.drawable.img_banner_1,
        R.drawable.img_banner_2,
        R.drawable.img_banner_3,
    )

    val categories = listOf(
        "전체",
        "상의",
        "팬츠",
        "원피스",
        "아우터",
        "스커트",
        "슈즈",
        "가방",
        "이너웨어/잠옷",
        "폰꾸미기",
    )

    val products = listOf(
        Product(
            id = 1,
            imageRes = R.drawable.img_product_1,
            storeName = "트랜디어패럴",
            productName = "[카프리1위🏆/5부,7부,9부] 데일리 카프리 팬츠",
            discountRate = 36,
            price = 28800,
            isLiked = false,
        ),
        Product(
            id = 2,
            imageRes = R.drawable.img_product_2,
            storeName = "슬로우앤드",
            productName = "[🍁가을/3기장선택!] made. 와이드 슬랙스",
            discountRate = 50,
            price = 26820,
            isLiked = true,
        ),
        Product(
            id = 3,
            imageRes = R.drawable.img_product_3,
            storeName = "어텀",
            productName = "보울7부티셔츠",
            discountRate = 10,
            price = 17100,
            isLiked = false,
        ),
        Product(
            id = 4,
            imageRes = R.drawable.img_product_4,
            storeName = "슬로우앤드",
            productName = "[오픈할인중] made. 도브 셔츠",
            discountRate = 40,
            price = 35200,
            isLiked = false,
        ),
    )
}
