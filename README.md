# Calendar UI
[![](https://jitpack.io/v/komodgn/kmp.svg)](https://jitpack.io/#komodgn/kmp) ![Minimum SDK: 26](https://img.shields.io/badge/Minimum%20SDK-30-yellow)
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-purple.svg"/></a>   

### Setup
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

### Usage
Load a simple calendar in your Compose Multiplatform screen:

```Kotlin
import io.github.komodgn.kmp.calendar.ui.MetaCalendar
import kotlinx.datetime.Month

MetaCalendar(
    initialYear = 2026,
    initialMonth = Month.APRIL,
    onDayClick = { date ->
        // date is a kotlinx.datetime.LocalDate object
        println("Clicked date: ${date.dayOfMonth}")
    }
)
```

### Handling Selection
You can manage the selected date state and perform actions when a user picks a day:

```Kotlin
var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

MetaCalendar(
    initialYear = 2026,
    initialMonth = Month.APRIL,
    onDayClick = { date ->
        selectedDate = date
        // Navigate or update UI based on selectedDate
    }
)
```

### Scroll Orientation
Supports scroll behaviors. **The default is `None` (Static).**
```kotlin
import io.github.komodgn.kmp.calendar.core.CalendarScrollOrientation

MetaCalendar(
    scrollOrientation = CalendarScrollOrientation.Horizontal,
    initialYear = 2026,
    initialMonth = Month.APRIL,
    onDayClick = { /* ... */ }
)
```

### Customization (Optional)
Use Modifier to adjust the layout and look of your calendar:

```Kotlin
MetaCalendar(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clip(RoundedCornerShape(12.dp)),
    initialYear = 2026,
    initialMonth = Month.MAY,
    onDayClick = { /* Handle click */ }
)
```
---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/).
