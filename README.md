🐾 CuteCats
Check out the source code here 👉 github.com/gugabrilhante/CuteCats

https://github.com/gugabrilhante/CuteCats/issues/1#issue-3046923786

This Android app fetches and displays 10 random images of cats or dogs using the public TheCatApi and TheDogApi. You can switch between them using a simple tab-style interface.

🚀 How to Run the Project
Clone the repository:

bash
Copiar
Editar
git clone https://github.com/gugabrilhante/CuteCats.git
Open in Android Studio (Hedgehog or newer recommended).

Wait for Gradle sync to complete.

Connect a device or launch an emulator.

Click Run ▶️ to build and start the app.

🛠️ Tech Stack
Kotlin

Jetpack Compose – Modern declarative UI

Hilt – Dependency Injection

Retrofit – Networking

Coil – Image loading

MVVM + Clean Architecture

SOLID principles – especially Dependency Inversion Principle (DIP)

🧱 Architecture Overview
The project follows a layered Clean Architecture:

Copiar
Editar
presentation/
├── ItemScreen.kt
├── ItemViewModel.kt
├── state/ (UI States)
domain/
├── model/
├── repository/
data/
├── remote/
├── di/ (Hilt Modules)
ViewModel interacts with UseCase, which interacts with a Repository interface.

You can switch between TheCatApi and TheDogApi by injecting different implementations of the same repository interface.

📄 License
This project is licensed under the MIT License. Feel free to use, modify, and distribute.
