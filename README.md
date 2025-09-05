# Cucumber Selenium Gradle Framework

## Deskripsi
Framework ini digunakan untuk menguji aplikasi web secara otomatis menggunakan **Cucumber**, **Selenium WebDriver**, **Java**, dan **Gradle**.  
Framework menerapkan **Page Object Model (POM)** untuk memisahkan logika halaman dari langkah pengujian (step definitions).  
Contoh kasus uji disertakan untuk mendemonstrasikan fungsionalitas login pada aplikasi web demo (misal: SauceDemo).

---

## Fitur
- Implementasi **BDD** dengan **Cucumber**.
- Struktur **Page Object Model (POM)**.
- Mendukung **JUnit** dan Gradle untuk eksekusi test.
- Report otomatis dari Gradle / Cucumber.
- Contoh kasus uji login:  
  - Login berhasil dengan kredensial valid  
  - Login gagal dengan kredensial invalid  
  - Username kosong  
  - Password kosong

---

## Persiapan Project
1. Pastikan **Java JDK 17+** terinstall.  
2. Pastikan **Gradle 8+** terinstall atau gunakan wrapper (`./gradlew`).  
3. Pastikan browser Chrome terinstall. WebDriver otomatis diatur oleh **WebDriverManager**.

---

## Cara Menjalankan Test

1. **Clone repositori** ke komputer Anda:
` git clone https://github.com/bryan-nathan-c/cucumber-selenium-gradle-framework.git
cd cucumber-selenium-gradle-framework `


2. Jalankan test menggunakan Gradle:
`./gradlew test`

3. Setelah test selesai, laporan otomatis akan dibuat di folder:
Setelah test selesai, laporan otomatis dibuat di:
` build/reports/tests/test/index.html `

## Contoh Kasus Uji

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
