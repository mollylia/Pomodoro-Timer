# Pomodoro Timer

The application will allow users to **schedule focused work sessions with short breaks** to help with concentration and
productivity. Users set time intervals, and a timer displays the current interval on the screen with an option to
automatically start the next one once the current one has ended or be notified once each interval ends. This application
is intended for students who have trouble staying focused or for those who need reminders to take breaks.

This project interests me because I am searching for an application that meets my needs. That is, an application that
allows me to schedule different time intervals and have them start automatically once the preceding one has ended and
notifies me once an interval has ended. The Pomodoro timers I tried do not have these features, so I miss scheduled
breaks, and manually changing or starting the timer becomes annoying.

## User stories
- As a user, I want to be able to add different time intervals onto a list of intervals I want to run.
- As a user, I want to be able have the next time interval start automatically once the preceding one has ended.
- As a user, I want to be able to see the remaining time on the current time interval.
- As a user, I want to be notified when an interval has ended.

- As a user, when I select the quit option from the application menu, I want to be reminded to save the state of the
timer to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my pomodoro timer from file.

## Instructions for Grade
- You can generate the first required event related to adding Xs to a Y (adding Xs to a Y) by clicking on the *edit* tab
in the menu bar and then selecting *Add Interval*. This can also be done by using the shortcut: CTRL + A.
- You can generate the second required event related to adding Xs to a Y (viewing all the Xs in a Y) by clicking on the
*view* tab in the menu bar and then selecting *View Intervals*. This can also be done by using the shortcut: CTRL + V.
- You can locate my visual component by looking at the timer's background image.
- You can save the state of my application by clicking on the *File* tab in the menu bar and then selecting *Save Timer*
or by using the shortcut: CTRL + S.
- You can reload the state of my application by clicking on the *File* tab in the menu bar and then selecting *Load
Timer* or by using the shortcut: CTRL + L.

## Phase 4: Task 2
Fri Dec 02 10:01:15 PST 2022
Loaded Pomodoro Timer
Fri Dec 02 10:01:15 PST 2022
Added interval: project (study, 3000 seconds)
Fri Dec 02 10:01:15 PST 2022
Saved Pomodoro Timer
Fri Dec 02 10:01:22 PST 2022
Loaded Pomodoro Timer
Fri Dec 02 10:01:22 PST 2022
Added interval: project (study, 3000 seconds)
Fri Dec 02 10:01:22 PST 2022
Started Pomodoro Timer
Fri Dec 02 10:01:35 PST 2022
Stopped Pomodoro Timer
Fri Dec 02 10:01:38 PST 2022
Saved Pomodoro Timer

- added an interval called *project*, started the timer, stopped the timer, and chose to save timer when I quit.

## Phase 4: Task 3
If I had more time, some refactoring that I would do to improve my design includes:
- Eliminating redundant associations that increase coupling unnecessarily.
  - get rid of association between AddIntervalFrame, PomodoroInterval, and PomodoroTimer, so there is only one
  association between AddIntervalFrame and PomodoroTimerApp. PomodoroInterval and PomodoroTimer can be accessed through
  PomodoroTimerApp.
  - get rid of association between MainGUI and PomodoroTimer, so there is only one association between MainGUI and
  PomodoroTimerApp. PomodoroTimer can be accessed through PomodoroTimerApp.
  - get rid of association between ViewIntervalFrame and PomodoroTimer, so there is only one association between
  ViewIntervalFrame and PomodoroTimerApp. PomodoroTimer can be accessed through PomodoroTimerApp.
- Apply the Singleton Pattern to the PomodoroTimerApp class.
  - create a single, private, static field of the class's type to hold the single instance of the class.
  - create a private constructor that takes no arguments.
  - create a public static method that allows access to the single instance.
  - in the public static method, if the single instance is null, create an instance.
  - make the public static method return the single instance.