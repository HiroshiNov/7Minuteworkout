# Class Overview

This document summarizes the purpose of the major classes in the application and highlights key functions used throughout the codebase.

## MainActivity
Entry point of the app. Handles navigation to start the workout or open the BMI calculator. Sets click listeners for the start and BMI buttons.

## ExerciseActivity
Controls the workout flow. Manages rest and exercise timers, plays sounds, speaks exercise names via Text‑to‑Speech and updates the exercise progress list.

## FinishActivity
Displayed after completing all exercises. Shows a toolbar with a back button and offers a button to exit the screen.

## BMIActivity
Simple BMI calculator allowing height and weight input. Calculates the BMI value and displays a textual interpretation such as "Normal" or "Overweight".

## ExerciseStatusAdapter
RecyclerView adapter that renders the list showing progress through each exercise. Updates item backgrounds based on whether an exercise is selected or completed.

## ExerciseModel
Data model representing a single exercise including ID, display name, image resource and progress flags (`isCompleted` and `isSelected`).

## Constants
Contains helper methods for common data. Provides `defaultExerciseList()` which returns the standard set of 12 exercises used by the app.
