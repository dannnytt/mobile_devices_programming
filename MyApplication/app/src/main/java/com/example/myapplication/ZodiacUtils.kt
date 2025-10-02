package com.example.myapplication

import androidx.annotation.DrawableRes

fun getZodiacSign(day: Int, month: Int): String = when (month) {
    1 -> if (day < 20) "Козерог" else "Водолей"
    2 -> if (day < 19) "Водолей" else "Рыбы"
    3 -> if (day < 21) "Рыбы" else "Овен"
    4 -> if (day < 20) "Овен" else "Телец"
    5 -> if (day < 21) "Телец" else "Близнецы"
    6 -> if (day < 21) "Близнецы" else "Рак"
    7 -> if (day < 23) "Рак" else "Лев"
    8 -> if (day < 23) "Лев" else "Дева"
    9 -> if (day < 23) "Дева" else "Весы"
    10 -> if (day < 23) "Весы" else "Скорпион"
    11 -> if (day < 22) "Скорпион" else "Стрелец"
    12 -> if (day < 22) "Стрелец" else "Козерог"
    else -> ""
}

@DrawableRes
fun getZodiacImage(sign: String): Int {
    return when (sign) {
        "Козерог" -> R.drawable.image1
        "Водолей" -> R.drawable.image2
        "Рыбы" -> R.drawable.image3
        "Овен" -> R.drawable.image4
        "Телец" -> R.drawable.image5
        "Близнецы" -> R.drawable.image6
        "Рак" -> R.drawable.image7
        "Лев" -> R.drawable.image8
        "Дева" -> R.drawable.image9
        "Весы" -> R.drawable.image10
        "Скорпион" -> R.drawable.image11
        "Стрелец" -> R.drawable.image12
        else -> R.drawable.ic_launcher_foreground
    }
}
