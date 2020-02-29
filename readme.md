## Objectives
The purpose of this assignment is to practice writing apps with multiple activities and improve your skills in the areas below:
    * Use of an Intent to pass data between two activities
    * Using widgets and events to change content dynamically in a graphical user interface

## What do do
In this assignment, you will create an app named HW3_To-Do-List that can be used to create a to-do list. Specifically, the app must have the following:
    * The app must use at least 2 different activities. 
        * The first activity should have at least a listView (displaying the tasks), a title (e.g., TO-DO-LIST), an image(e.g., a check mark icon) and a button opening the second activity to enter new tasks. 
        * The second activity should have at least an editText to enter tasks and three buttons as follows: 
            1. A button to enter more than one task (clicking this button allows the user to keep adding a new task while staying on the same activity screen)
            2. A button to send the entered task(s) to the first activity (clicking this button should pass the entered tasks to the first activity by using an intent. (Hint: storing the entered tasks in a list and return this list in an intent would achieve this.)
            3. A button to go back to the first activity without sending any task data to the first activity (i.e., cancel button)
    * The app must handle empty strings. For example, if the user presses the save button without entering a task in the second activity, the user should be informed to enter a task.
    * After the user enters tasks in the second activity, the listView in the first activity should be updated with the entered tasks.
    * Long pressing a task on the listView should delete the task from the list.
    * If there is no task left on the list, a Toast message should display “All tasks are completed!”.
    * The tasks entered should be persistent unless they are deleted by the user. For example,if the user enters 5 tasks and then closes the app, the app should remember the previously entered tasks when the app is launched again. (Hint:you can use SharedPreferences to store/save entered tasks, and load once the app is launched)