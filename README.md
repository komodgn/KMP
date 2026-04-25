# Calendar UI
[![](https://jitpack.io/v/komodgn/kmp.svg)](https://jitpack.io/#komodgn/kmp)  

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
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).
