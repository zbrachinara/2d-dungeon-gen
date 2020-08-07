package graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Avatar implements KeyListener {

    public int[] position = new int[2];

    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {
            case 'w':

            case 'a':
            case 's':
            case 'd':
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
