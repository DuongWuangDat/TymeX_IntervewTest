# Currency Converter App

## Overview
The **Currency Converter App** is an Android application built using **Jetpack Compose** and follows the **MVVM (Model-View-ViewModel)** architecture. It allows users to convert between various currencies with real-time exchange rates, featuring an elegant and user-friendly interface. This app is ideal for travelers, business professionals, and anyone dealing with multiple currencies.

<img src="[https://example.com/your-image.jpg](https://firebasestorage.googleapis.com/v0/b/remood-177f5.appspot.com/o/preview_screen.jpg?alt=media&token=8a0211c1-4105-4f53-a154-4be2e0c9679d)" alt="Alt text" width="300">

---

## Features
- 🌍 **Real-time Conversion**: Instantly convert between numerous currencies with up-to-date exchange rates.
- 🖌️ **Jetpack Compose UI**: A modern, clean, and responsive interface.
- ⚙️ **Customizable Preferences**: Configure default currencies and application settings.

---

## Build and Run Steps

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/DuongWuangDat/TymeX_IntervewTest.git
   ```
2. Open the project in Android Studio (at Question1/TymeX_CurrencyConverter_Test folder).
3. Sync the project with Gradle files by clicking on **Sync Now**.
4. Add your API key for the currency exchange service:
   - Open the `local.properties` file (create one if it doesn't exist).
   - Add the following line:
     ```properties
     apiKey=YOUR_API_KEY
     ```
   - Free API_KEY: ```c7db9735f221cc0f0ef45ab7```
5. Build and run the app on an emulator or a physical Android device.

---

## API Information
The app uses **ExchangeRate-API** as its source for real-time currency exchange rates. For more information, visit [ExchangeRate-API](https://www.exchangerate-api.com/).

---

## Challenges and Notes

### Challenges
- **UI Design**: Balancing aesthetics with performance in Jetpack Compose components.
- **State Management**: Managing data flows and UI updates within the **MVVM architecture** required careful implementation.

### Notes
- The app uses a third-party API for currency rates. Ensure your API key is active and has sufficient quota.
- Utilized MVVM architecture to maintain and read code easily.
- Caching is implemented with Room Database for offline use. Rates might be outdated if the app has been offline for an extended period.

---
