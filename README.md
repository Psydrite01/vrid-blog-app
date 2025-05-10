# Vrid Blog App

A clean and functional blog reader Android application developed using Kotlin and Jetpack Compose, created as part of the Vrid internship assignment.

## ✅ Core Requirements

- **Kotlin for development:**  
  Developed using the latest Kotlin version `2.0.21`.

- **Clean and maintainable architecture:**  
  Follows MVVM architecture. Utilizes structured code with modular components and externalized resources (e.g., strings, themes).

- **Jetpack Compose for UI:**  
  Built with Compose to deliver a modern, accessible, and responsive user interface.

- **Smooth navigation:**  
  Implemented using Jetpack Navigation with a navigation graph. Smooth transitions in both debug and release builds.

- **Git for code management:**  
  Full version control using GitHub, with meaningful commits and proper branching.

## 💡 Optional Enhancements

- **Caching blog data:**  
  Instead of SQLite, used a lightweight file-based approach with JSON serialization for local caching.

## 🚀 Bonus Features

- **Offline support:**  
  Cached data ensures the app remains functional when offline.

- **Efficient data loading:**  
  Only necessary data is fetched and stored locally to reduce redundant network calls. There’s scope for further optimization using paging libraries.

- **Pagination:**  
  Implemented dynamic pagination by adjusting the page query parameter as the user scrolls.

## 🔧 Developer Enhancements

- **Dependency Injection:**  
  Integrated Dagger Hilt for scalable and testable dependency management.

- **Improved UI with inline media:**  
  Dynamically fetched and displayed blog images for a richer reading experience.

## 📷 Screenshots

<!-- Insert screenshots here when available -->
<!-- ![Home Screen](screenshots/home.png) -->
<!-- ![Blog View](screenshots/detail.png) -->
