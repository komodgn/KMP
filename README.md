[![](https://jitpack.io/v/komodgn/kmp.svg)](https://jitpack.io/#komodgn/kmp) ![Minimum SDK: 26](https://img.shields.io/badge/Minimum%20SDK-30-yellow)
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-purple.svg"/></a>

<img width="4541" height="1779" alt="Group 6 (1)" src="https://github.com/user-attachments/assets/3cd30984-990d-4dc1-831a-9dfead08d260" />  

## Setup
Add the dependency below to your module's build.gradle.kts file.

1. Add Repository
Add the JitPack repository to your root settings.gradle.kts:

```Kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // Add this
    }
}
```

2. Add Dependency  
- For Compose Multiplatform projects, add the dependency to your commonMain:

```Kotlin
sourceSets {
    commonMain.dependencies {
        // Includes both UI components and core calendar logic
        implementation("com.github.komodgn.KMP:calendar-ui:$version")
    }
}
```

- For Android-only Projects

```Kotlin
dependencies {
    // You can use the same artifact for Android-only projects
    implementation("com.github.komodgn.KMP:calendar-ui:$version") 
}
```

## Usage
Load a simple calendar in your Compose Multiplatform screen:

```Kotlin
import io.github.komodgn.kmp.calendar.ui.MetaCalendar
import io.github.komodgn.kmp.calendar.ui.rememberCalendarState

val calendarState = rememberCalendarState(scrollOrientation = CalendarScrollOrientation.Horizontal)

MetaCalendar(
    state = calendarState,
    onDayClick = { date ->
        // date is a kotlinx.datetime.LocalDate object
        println("Clicked date: ${date.dayOfMonth}")
    },
)
```
## Features
#### 1. Handling Selection
- You can manage the selected date state and perform actions when a user picks a day:

```Kotlin
var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

MetaCalendar(
    onDayClick = { date ->
        selectedDate = date
        // Navigate or update UI based on selectedDate
    },
)
```

#### 2. Scroll Orientation
- Supports scroll behaviors. **The default is `None` (Static).**

#### 3. Customization (Optional)
- Use Modifier to adjust the layout and look of your calendar.
