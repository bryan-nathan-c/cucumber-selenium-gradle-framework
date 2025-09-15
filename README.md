# Cucumber Selenium + API Gradle Framework
Framework ini digunakan untuk menguji aplikasi web (UI) dan API secara otomatis dengan pendekatan Behavior Driven Development (BDD) menggunakan Cucumber.
Framework ini menggabungkan Selenium WebDriver untuk otomasi antarmuka pengguna dan RestAssured untuk pengujian layanan API, sehingga dapat digunakan sebagai solusi end-to-end testing dalam satu project.
Struktur proyek dirancang dengan konsep Page Object Model (POM) pada pengujian UI untuk memisahkan logika halaman dari step definitions, sehingga kode menjadi lebih rapi, reusable, dan mudah dirawat.
Untuk pengujian API, setiap skenario ditulis di file .feature terpisah yang mudah dibaca baik oleh developer maupun non-teknis.
---
## Tools & Framework

- **Cucumber** – Behavior Driven Development (BDD)
- **Selenium WebDriver** – Automasi UI
- **RestAssured** – Automasi API
- **Java & Gradle** – Bahasa dan build tool
- **JUnit / TestNG** – Test runner
- **WebDriverManager** – Manajemen driver otomatis
- **Allure (opsional)** – Laporan uji interaktif

---

## Fitur Utama

- ✅ Implementasi BDD dengan Cucumber
- ✅ UI Automation berbasis Page Object Model (POM)
- ✅ API Automation dengan skenario `.feature` terpisah
- ✅ Mendukung eksekusi test via Gradle
- ✅ Laporan otomatis dari Gradle / Cucumber (bisa dikombinasikan dengan Allure)

---

## 🛠️ Persiapan Project

Pastikan hal berikut sudah terpasang:

- Java JDK **17+**
- Gradle **8+** (atau gunakan `./gradlew`)
- Browser **Google Chrome**
- API Key untuk ReqRes:
```x-api-key: reqres-free-v1```

## 📌 Cara Menjalankan Test

1. **Clone repositori**
   ```bash
   git clone https://github.com/bryan-nathan-c/cucumber-selenium-gradle-framework.git
   cd cucumber-selenium-gradle-framework
2. Jalankan test:
   ```./gradlew test```

3. Lihat laporan hasil test:
   ```build/reports/tests/test/index.html```
---

## 📌 Contoh Test Cases

### 🔹 UI Test – SauceDemo Login
```gherkin
Feature: SauceDemo Login

  Scenario: Successful login with valid credentials
    Given user is on the SauceDemo login page
    When username "standard_user" and password "secret_sauce" are entered
    And the login button is clicked
    Then user should be redirected to the Products page

  Scenario: Failed login with invalid credentials
    Given user is on the SauceDemo login page
    When username "wrong" and password "bad" are entered
    And the login button is clicked
    Then an error message "Epic sadface: Username and password do not match any user in this service" should be displayed

  Scenario: Username is empty
    Given user is on the SauceDemo login page
    When username "" and password "secret_sauce" are entered
    And the login button is clicked
    Then an error message "Epic sadface: Username is required" should be displayed

  Scenario: Password is empty
    Given user is on the SauceDemo login page
    When username "standard_user" and password "" are entered
    And the login button is clicked
    Then an error message "Epic sadface: Password is required" should be displayed


##🔹 API Test – ReqRes API

Feature: User API Testing

Scenario: Get user list from API

- Given the API endpoint is "https://reqres.in/api/users?page=2"
- When a GET request is sent
- Then the response status code should be 200
- And the response should contain "Janet"

