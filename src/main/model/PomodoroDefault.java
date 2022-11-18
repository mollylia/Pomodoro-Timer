package model;

// Based on Category in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// A set of default options for Pomodoro intervals provided to the user
public enum PomodoroDefault {
    SHORT_STUDY, LONG_STUDY, SHORT_BREAK, LONG_BREAK;

    public static int getPomodoroDefault(PomodoroDefault defaultTime) {
        switch (defaultTime) {
            case SHORT_STUDY:
                return 1500;
//                return 5;

            case LONG_STUDY:
                return 3000;
//                return 8;

            case SHORT_BREAK:
                return 300;
//                return 3;
        }
        return 600;
//        return 4;
    }
}