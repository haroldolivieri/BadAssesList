# Bad Asses List
[![Build Status](https://travis-ci.org/haroldolivieri/BadAssesList.svg?branch=master)](https://travis-ci.org/haroldolivieri/BadAssesList) [![codecov](https://codecov.io/gh/haroldolivieri/BadAssesList/branch/master/graph/badge.svg)](https://codecov.io/gh/haroldolivieri/BadAssesList)

## Architecture & Design Pattern
This project is a sample of good usages about MVP and dagger-android.
Each feature is separated as a subcomponent.This was made easier with **AndroidInjector** and **@ContributesAndroidInjector** on Dagger 2.11.

*keywords: dagger-android-injection + RxJava2 + MVP + jacoco test coverage*

## Unit Tests
There are only unit tests aiming to made easier the CI pipeline.
The Dagger Injection Graph was mocked using Roboletric.
If you face any problem to run the tests, please, follow this [setup](http://robolectric.org/getting-started/)
on **Building with Android Studio** section.

*keywords: Robolectric + PowerMock + Mockito + Dagger2 + RxJava2*




