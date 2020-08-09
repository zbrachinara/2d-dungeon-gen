package logic;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Timer {

    long startTime;
    public Label timerDisplay = new Label();

    public Timer() {
        startTime = System.currentTimeMillis();
        timerDisplay.setPadding(new Insets(0, 685, 450, 0));
        timerDisplay.setFont(new Font("serif", 16));
    }

    public void update() {

        long elapsedTime = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long remainingTime = 3600 - elapsedSeconds;
        long secondsDisplay = remainingTime % 60;
        long minutesDisplay = remainingTime / 60;
        String displayText = minutesDisplay + ":" + secondsDisplay;
        timerDisplay.setText(displayText);

    }

}
