package org.sopt.play.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.play.R


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
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