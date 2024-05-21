package com.lee_idle.slotapidemo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Purple40,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = PurpleGrey80,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

/*
    (Material Theme Color의 의미)
    primary - 기본 색상은 앱의 화면과 구성 요소에서 가장 자주 표시되는 색상입니다.
    primaryVariant - 기본 변형 색상은 기본 색상을 사용하여 앱의 두 요소(예: 상단 앱 표시줄 및 시스템 표시줄)를 구별하는 데 사용됩니다.
    secondary - 보조 색상은 제품을 강조하고 구별하는 더 많은 방법을 제공합니다.
    보조 색상은 다음에 가장 적합합니다.
    플로팅 작업 버튼, 확인란 및 라디오 버튼과 같은 선택 컨트롤, 선택한 텍스트 강조 표시, 링크 및 헤드라인
    secondaryVariant - 보조 변형 색상은 보조 색상을 사용하여 앱의 두 요소를 구별하는 데 사용됩니다.
    background - 배경색은 스크롤 가능한 콘텐츠 뒤에 나타납니다.
    surface - 표면 색상은 카드, 시트 및 메뉴와 같은 구성 요소의 표면에 사용됩니다.
    error - 오류 색상은 텍스트 필드와 같은 구성 요소 내의 오류를 나타내는 데 사용됩니다.
    onPrimary - 기본 색상 위에 표시되는 텍스트 및 아이콘에 사용되는 색상입니다.
    onSecondary - 2차 색상 위에 표시되는 텍스트 및 아이콘에 사용되는 색상입니다.
    onBackground - 배경색 위에 표시되는 텍스트 및 아이콘에 사용되는 색상입니다.
    onSurface - 표면 색상 위에 표시되는 텍스트 및 아이콘에 사용되는 색상입니다.
    onError - 오류 색상 위에 표시되는 텍스트 및 아이콘에 사용되는 색상입니다.
    isLight - 이 색상이 '밝은' 색상 세트로 간주되는지 '어두운' 색상 세트로 간주되는지 여부입니다. 이것은 일부 구성 요소의 기본 동작에 영향을 줍니다. 예를 들어 밝은 테마에서 TopAppBar는 기본적으로 배경색으로 기본을 사용하고 어두운 테마에서는 표면을 사용합니다.


 */

@Composable
fun SlotApiDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}