package org.sopt.play.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import org.sopt.play.core.designsystem.theme.Gray5
import org.sopt.play.core.designsystem.theme.Gray6
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.designsystem.theme.Red
import org.sopt.play.core.designsystem.theme.SoptTypography
import org.sopt.play.core.designsystem.theme.White
import org.sopt.play.presentation.home.model.HomeDummyData
import org.sopt.play.presentation.home.model.Product

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Box {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.productName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 5f),
            )

            Icon(
                painter = painterResource(
                    id = if (product.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline,
                ),
                contentDescription = "좋아요",
                tint = if (product.isLiked) Red else White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(20.dp),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.padding(horizontal = 8.dp),
        ) {
            Text(
                text = product.storeName,
                style = SoptTypography.m14,
                color = Gray6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.productName,
                style = SoptTypography.r14,
                color = Gray5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Text(
                    text = "${product.discountRate}%",
                    style = SoptTypography.sb14,
                    color = Red,
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "%,d".format(product.price),
                    style = SoptTypography.sb14,
                    color = Black,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    PlaySoptTheme {
        Row(
            modifier = Modifier.width(360.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            ProductCard(
                product = HomeDummyData.products[0],
                modifier = Modifier.weight(1f),
            )

            ProductCard(
                product = HomeDummyData.products[1],
                modifier = Modifier.weight(1f),
            )
        }
    }
}
