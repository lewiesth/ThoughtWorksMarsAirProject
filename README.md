# Thoughtworks MarsAir
This repository contains the automated testing suite for the MarsAir booking application. I have decided to use Playwright Java as the testing framework and BDD cucumber for behaviour driven testing.

#### Tech stack used

* Language - Java 21

* Automation Framework - Playwright Java

* Test Runner - jUnit 5

* BDD Framework - Cucumber 7

* Reporting Framework - Allure 

## Set up

To run this project, you would need to have installed Java and maven, and defined them in your system variables

### Clone the repository

```
git clone https://github.com/lewiesth/ThoughtWorksMarsAirProject
cd ThoughtWorksMarsAirProject
```

### Executing the test suite

To execute the test suite, simply run the following command. It will default to using the chromium browser in headless mode

```
mvn clean test
```

If you want to specify the browser to be used or would likes the test to run headed, the following command can be used instead. For this project, chromium, firefox and webkit are supported.

```
mvn clean test -DBROWSER=firefox -DHEADLESS=false
```

The test cases in this repository has also been tagged for more specific scenarios, such as sanity testing or for executing any specific user story.

```
mvn clean test -Dcucumber.filter.tags="@Sanity"
```

## Reporting

This project uses Allure for visualization of test results. The test results would be stored in the allure-results folder on test execution and can be generated with the following commands

```
allure generate --clean
allure open
```

## Github Actions

For ease of readability, the test execution results has also been deployed on Github pages, and can be found at https://lewiesth.github.io/ThoughtWorksMarsAirProject/

## Results

In this project, 13 test cases have been generated across the 4 user stories. Of the 13, 3 has failed and the defect report for these issues have been raised.

<img width="913" height="834" alt="image" src="https://github.com/user-attachments/assets/70cadd72-af3b-4c3d-8877-cb2a7d7c4175" />

Using the allure report generated, we can also easily reference the point of failure as screenshots have been attached for each failing scenario.

<img width="1714" height="914" alt="image" src="https://github.com/user-attachments/assets/7bf8e3a2-7f89-4a89-a038-05d00f0a4789" />

For the purpose of this exercise, we have only conducted functional testing. It is recommended that additional layers of testing be considered for a more comprehensive quality review, such as performance testing and security testing.
