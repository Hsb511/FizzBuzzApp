# ![logo](https://github.com/Hsb511/FizzBuzzApp/blob/main/app/src/main/res/mipmap-mdpi/ic_launcher_round.png) DIV1D  

This fizz-buzz application was developed as part of an Android Kata for eXalt IT. The apk can be downloaded [here](https://github.com/Hsb511/FizzBuzzApp/releases/download/1.1.0/fizzbuzzapp_release_1.1.0.apk)

The main feature follows the [Fizz buzz game](https://en.wikipedia.org/wiki/Fizz_buzz).

The app consists of two screens:
 - A form that accepts five parameters: the two dividers **int1** and **int2**, the **limit** and the two strings to display, **str1** and **str2**
 - A scrollable list generated from the parameters according to the fizz buzz game

What the screens look like:

| Form screen light | List screen light | Form screen dark | List screen dark |
|-------------------|-------------------|------------------|------------------|
| ![form light mode](https://github.com/Hsb511/FizzBuzzApp/blob/main/previews/form_light_mode.jpg?raw=true) | ![list light mode](https://github.com/Hsb511/FizzBuzzApp/blob/main/previews/list_light_mode.jpg?raw=true) | ![form dark mode](https://github.com/Hsb511/FizzBuzzApp/blob/main/previews/form_dark_mode.jpg?raw=true) | ![list dark mode](https://github.com/Hsb511/FizzBuzzApp/blob/main/previews/list_dark_mode.jpg?raw=true) |

The current version of the application is [1.1.0](https://github.com/Hsb511/FizzBuzzApp/releases/tag/1.1.0) as specified in the app/build.gradle.kts

# Technical stack

The app follows an MVVM clean architecture with 3 main layers : UI (presentation), domain and data. 

The main libraries used in this app are:
- **[Jetpack compose](https://developer.android.com/jetpack/compose)** (version 1.2.0-beta03): I chose compose to put into practice the theoritical learning I recently had. It also made it easy to write maintainable code and an intuitive UI that follows material guidelines.
- **[Hilt](https://developer.android.com/training/dependency-injection/hilt-android)** (version 2.41): A library that allows depency injection, a requirement when working with clean architecture.
- **[Android kotlin coroutines](https://developer.android.com/kotlin/coroutines)** (version 1.6.0): Coroutines are tools meant to ease asynchronous code development. The two main advantages are code simplification and performance improvement through lightweight and fewer memory leaks. Furthermore it is very well integrated with Jetpack.
- **[JUnit](https://developer.android.com/training/testing/local-tests#test-class)** (version 4.13.2): JUnit is one of the most popular unit-testing framework.
- **[Room](https://developer.android.com/training/data-storage/room)** (Version 2.4.2): Room is a persistence library relying on SQLite and recommended by Google. It works around three major concepts : Database, DAO and Entity

# Computation and performance

After different tests, I noticed that with a limit greater than 1 000 000 the app crashed. So I decided to divide my list in pages of 1 000 000 values if the limit was too big. The main computation is made in a coroutine inside the `computeList()` function in the viewmodel.  
The business logic behind it is defined in the use case `ComputeFizzBuzzListUseCase`. We generate a list of longs from the current start (by default 1) to the current limit and for each of these values we make the fizz buzz replacement when needed. The output is a list of string.

# What to add in a future version
(That will probably never exist)
- A more explicit form with error messages: the controls on the fields are already implemented but it is not clear for the user what he can fill or not
- A scroll bar and buttons to navigate more easily on the list and pages
