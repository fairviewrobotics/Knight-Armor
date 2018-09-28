# Knight-Armor
A set of base code for Fairview Robotics FIRST competition robots.
This is just a test of an idea, and may not actually be used.
Knight Armor is meant to be another layer on top of WPILib, but doesn't intend to replace it.
Knight-Armor is made to make robot code a little easier, cleaner, and more idiomatic.

## Setup
To setup a Knight-Armor project, just clone this repository.
Ensure that all of the gradle dependencies are up to date.
As of right now, the things to check up on in `build.gradle` are `id "org.jetbrains.kotlin.jvm" version "1.2.71"`, `id "jaci.openrio.gradle.GradleRIO" version "2018.03.06"`, and `gradleVersion = '4.4'`.

## Use
To use the Knight-Armor project, code your robot as normal, but rather than using normal WPI classes, use Knight-Armor classes.
Knight-Armor classes are available locally and can be changed in the project in the knightarmor subdirectory of the project.
For regular season code, ensure that the code isn't hidden inside the knightarmor package.
Furthermore, the code located in the knightarmor package shouldn't be tampered with unless changing how knightarmor works is necessary and members are willing to go down that rabbit-hole.

## Deploy
Knight-Armor is using [GradleRIO](https://github.com/Open-RIO/GradleRIO), so the deploy steps for Knight-Armor will be the same as the deploy steps for GradleRIO. TODO: put instructions here also so looking at GradleRIO is unnecessary.

## Documentation
Javadoc coming soon (hopefully)! Will ideally be put in docs folder of the master branch so that it can be made into a Github Pages site.

## Features
Coming soon (hopefully)!

## Useful links
http://first.wpi.edu/FRC/roborio/release/docs/java/ for the JavaDoc on WPILib \
https://wpilib.screenstepslive.com/s/currentCS/m/java for the FRC Java Programming guide\
http://kotlinlang.org/docs/reference/ for Kotlin documentation \
https://github.com/Open-RIO/GradleRIO for the GradleRIO GitHub page