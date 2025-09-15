Cucumber Selenium + API Gradle Framework
Deskripsi

Framework ini digunakan untuk menguji aplikasi web dan API secara otomatis menggunakan:

Cucumber untuk BDD

Selenium WebDriver untuk uji UI

RestAssured untuk uji API

Java & Gradle sebagai test runner

Struktur framework menerapkan Page Object Model (POM) untuk UI test, serta skenario API ditulis dalam file .feature terpisah.

Fitur

Implementasi BDD dengan Cucumber.

UI Automation dengan Selenium (contoh: SauceDemo login).

API Automation dengan RestAssured (contoh: GET user list dari ReqRes API).

Struktur Page Object Model (POM) untuk UI.

Mendukung JUnit dan Gradle untuk eksekusi test.

Report otomatis dari Gradle / Cucumber (dapat dikombinasikan dengan Allure).

Persiapan Project

Pastikan Java JDK 17+ terinstall.

Pastikan Gradle 8+ terinstall atau gunakan wrapper (./gradlew).

Pastikan browser Chrome terinstall (WebDriver otomatis diatur oleh WebDriverManager).

Untuk API test dengan ReqRes, gunakan API key gratis:

x-api-key: reqres-free-v1

Cara Menjalankan Test

Clone repositori ke komputer Anda:

git clone https://github.com/bryan-nathan-c/cucumber-selenium-gradle-framework.git
cd cucumber-selenium-gradle-framework


Jalankan test menggunakan Gradle:

./gradlew test


Setelah test selesai, laporan otomatis dibuat di:

build/reports/tests/test/index.html

Contoh Kasus Uji
🔹 UI Test (SauceDemo Login)
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

🔹 API Test (ReqRes API)
Feature: User API Testing

  Scenario: Get user list from API
    Given the API endpoint is "https://reqres.in/api/users?page=2"
    When a GET request is sent
    Then the response status code should be 200
    And the response should contain "Janet"
