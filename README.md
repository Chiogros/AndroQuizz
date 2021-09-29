# Loustics
A simple multi-themed quizz for an Android college project.

There are a few courses : French, Geography, History and Math.
For each theme, there are multiple chapters that brings different questions.

<div style="display: flex;">
  <img src="screenshots/account_selection.png" alt="Account selection" width="200"/>
  <img src="screenshots/courses_menu.png" alt="Course menu" width="200"/>
  <img src="screenshots/mcq_about_europe_example.png" alt="MCQ example with Europe's theme" width="200"/>
  <img src="screenshots/math_example.png" alt="Example with math" width="200"/>
</div>

All the questions are stored in a database.
To add / edit / remove some questions, you should check in `java/.../db/AppDatabase.java`
You can set multiple answers for a question: as much rights and wrongs answers as you want.
Theses answers are parsed in JSON in two arrays : `right` and `wrong`.
