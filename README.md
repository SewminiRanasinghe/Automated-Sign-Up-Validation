# Automated Sign-Up Validation

This project automates the sign-up process on the [COG Staging Website](https://cog-stg.incubatelabs.com/) using Selenium WebDriver and TestNG. It demonstrates the use of dynamic waits, exception handling, and structured test execution for validating a web form.

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- ChromeDriver
- WebDriverManager

## Features

- Automated navigation through sign-in and sign-up pages
- Sign-up with email workflow
- Form filling with test data
- Form submission validation
- Use of explicit waits and exception handling
- Organized test steps with `@Test` priorities

## Test Data

First Name: Example  
Last Name: Tester  
Email: example@test.com  
Mobile: 0711234567  
Password: StrongPass1234

## Notes
The test includes basic positive path validation only.

Future improvements include adding Page Object Model (POM) and expanding test cases for negative scenarios.
