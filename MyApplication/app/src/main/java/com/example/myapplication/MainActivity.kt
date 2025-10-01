package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

import com.example.myapplication.PlayerData
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    PlayerRegistrationForm(Modifier.padding(it))
                }
            }
        }
    }
}


@Composable
fun PlayerRegistrationForm(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var lastName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Мужской") }
    var course by remember { mutableStateOf("1") }
    var difficulty by remember { mutableFloatStateOf(0f) }
    var birthDate by remember { mutableStateOf("") }
    var zodiac by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<PlayerData?>(null) }

    val calendar = Calendar.getInstance()
    val datePicker = DatePickerDialog(
        context,
        { _, year, month, day ->
            birthDate = "$day.${month + 1}.$year"
            zodiac = getZodiacSign(day, month + 1)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SurnameInput(lastName) { lastName = it }
        FirstnameInput(firstName) { firstName = it }
        PatronymicInput(middleName) { middleName = it }

        GenderSelector(gender) { gender = it }
        CourseDropdown(course) { course = it }
        DifficultySlider(difficulty) { difficulty = it }
        BirthDatePicker(birthDate, datePicker::show)

        ZodiacDisplay(zodiac)

        Button(
            onClick = {
                result = PlayerData(
                    lastName, firstName, middleName,
                    gender, course, difficulty.toInt(),
                    birthDate, zodiac
                )
            },
            enabled = lastName.isNotBlank() &&
                    firstName.isNotBlank() &&
                    birthDate.isNotBlank()
        ) {
            Text("Зарегистрироваться")
        }

        RegistrationResult(result)
    }
}

@Composable
fun SurnameInput(lastName: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = lastName,
        onValueChange = onChange,
        label = { Text("Фамилия") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun FirstnameInput(firstName: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = firstName,
        onValueChange = onChange,
        label = { Text("Имя") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun PatronymicInput(middleName: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = middleName,
        onValueChange = onChange,
        label = { Text("Отчество (при наличии)") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
fun GenderSelector(selectedGender: String, onGenderChange: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selectedGender == "Мужской",
            onClick = { onGenderChange("Мужской") }
        )
        Text("Мужской")
        Spacer(modifier = Modifier.width(20.dp))
        RadioButton(
            selected = selectedGender == "Женский",
            onClick = { onGenderChange("Женский") }
        )
        Text("Женский")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDropdown(course: String, onCourseChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val courses = listOf("1", "2", "3", "4", "5")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = course,
            onValueChange = {},
            readOnly = true,
            label = { Text("Курс") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            courses.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onCourseChange(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DifficultySlider(difficulty: Float, onDifficultyChange: (Float) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Уровень сложности: ${difficulty.toInt()}")
        Slider(
            value = difficulty,
            onValueChange = onDifficultyChange,
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun BirthDatePicker(birthDate: String, onPickDate: () -> Unit) {
    Button(onClick = onPickDate) {
        Text(if (birthDate.isEmpty()) "Выбрать дату рождения" else "Дата: $birthDate")
    }
}

@Composable
fun ZodiacDisplay(zodiac: String) {
    if (zodiac.isNotEmpty()) {
        Text("Знак зодиака: $zodiac")
        Image(
            painter = painterResource(id = getZodiacImage(zodiac)),
            contentDescription = "Знак зодиака",
            modifier = Modifier.size(80.dp)
        )
    }
}

@Composable
fun RegistrationResult(result: PlayerData?) {
    result?.let {
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = DividerDefaults.Thickness,
            color = DividerDefaults.color
        )
        Text(
            text = """
                Фамилия: ${it.lastName}
                Имя: ${it.firstName}
                Отчество: ${it.middleName}
                Пол: ${it.gender}
                Курс: ${it.course}
                Сложность: ${it.difficulty}
                Дата рождения: ${it.birthDate}
                Знак зодиака: ${it.zodiac}
            """.trimIndent()
        )
    }
}

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