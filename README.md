# NIT3213 Assessment Application

An Android application developed as part of the NIT3213 Final Assignment.  
This app demonstrates **Login, Dashboard, and Details screens** with API integration.

---

## Features
- **Login Screen**: Authenticate with username (first name) & password (student ID without 's').
- **Dashboard**: Fetch and display a list of entities from the API.
- **Details Page**: Show detailed information of a selected entity.
- **Back & Logout Buttons** for smooth navigation.
- **Modern UI**: Minimal, formal, and user-friendly design.

---

##  Tech Stack
- Kotlin  
- MVVM Architecture (ViewModel, LiveData, Repository)  
- Retrofit2 + Gson (API communication)  
- Hilt (Dependency Injection)  
- RecyclerView + CardView (list display)  
- Material Components (UI styling)

---

## How to Build & Run

  1. Create a respiratory in github
  2. Push the Repository
  3. Open the project in Android Studio
  4. Go to File → Open... and select the project folder.Let Gradle sync and download dependencies.
  5. Run the app
  6. Connect a device or start an emulator.Click Run ▶ in Android Studio.

## API Endpoints
Base URL:
https://nit3213api.onrender.com/

## Login (POST)
/sydney/auth

## Dashboard (GET)
/dashboard/{keypass}

## Dependencies used
<img width="1558" height="1057" alt="image" src="https://github.com/user-attachments/assets/1966f54d-84fc-491f-a46d-3cd5d24ac96b" />

## Author

Name: Aarya Bhari

Student ID: s8102986

Unit: NIT3213 – Mobile Application Development



