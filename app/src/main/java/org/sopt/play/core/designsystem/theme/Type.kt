package org.sopt.play.core.designsystem.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.play.R


val pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium)
)

object SoptTypography {
    val b28 = TextStyle(fontFamily = pretendard, fontWeight = FontWeight.Bold,
        fontSize = 28.sp, lineHeight = 1.2.em, letterSpacing = (-0.01).em)
    val m18 = TextStyle(fontFamily = pretendard, fontWeight = FontWeight.Medium,
        fontSize = 18.sp, lineHeight = 1.2.em, letterSpacing = (-0.01).em)
    val sb16 = TextStyle(fontFamily = pretendard, fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp, lineHeight = 1.2.em, letterSpacing = (-0.01).em)
    val m14 = TextStyle(fontFamily = pretendard, fontWeight = FontWeight.Medium,
        fontSize = 14.sp, lineHeight = 1.2.em, letterSpacing = (-0.01).em)
    val sb14 = TextStyle(fontFamily = pretendard, fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp, lineHeight = 1.2.em, letterSpacing = (-0.01).em)
}
