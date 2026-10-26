package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.Black
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.Red
import org.sopt.play.core.designsystem.theme.SoptTypography
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.home.model.Product

@Composable
fun SoptProductCard(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Box {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 5f),
            )

            Icon(
                painter = painterResource(
                    id = if (product.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline,
                ),
                contentDescription = if (product.isLiked) "좋아요한 상품" else null,
                tint = if (product.isLiked) Red else White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        bottom = 8.dp,
                        end = 8.dp,
                    )
                    .size(24.dp),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    top =12.dp,
                    end = 8.dp,
                    bottom = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = product.storeName,
                style = SoptTypography.m14,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = product.productName,
                style = SoptTypography.r14,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Row (
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${product.discountRate}%",
                    style = SoptTypography.m14,
                    color = Red,
                )

                Text(
                    text = "%,d".format(product.price),
                    style = SoptTypography.m14,
                    color = Black,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SoptProductCardPreview() {
    val longTextProduct = Product(
        id = 100,
        imageRes = R.drawable.img_product_1,
        storeName = "트랜디어패럴 공식 스토어 단독 브랜드관",
        productName = "[카프리1위🏆/5부,7부,9부] 데일리 와이드 핏 카프리 팬츠 봄 여름 가을 신상",
        discountRate = 99,
        price = 1234567,
        isLiked = true,
    )

    val shortTextProduct = Product(
        id = 101,
        imageRes = R.drawable.img_product_2,
        storeName = "어텀",
        productName = "셔츠",
        discountRate = 5,
        price = 9900,
        isLiked = false,
    )
    PlaySoptTheme {
        Row(
            modifier = Modifier.width(360.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SoptProductCard(
                product = longTextProduct,
                modifier = Modifier.weight(1f),
            )

            SoptProductCard(
                product = shortTextProduct,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
