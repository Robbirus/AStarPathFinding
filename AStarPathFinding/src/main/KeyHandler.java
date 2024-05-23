package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private DemoPanel demoPanel;

    public KeyHandler(DemoPanel demoPanel){
        this.demoPanel = demoPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ENTER){
            //this.demoPanel.search();
            this.demoPanel.autoSearch();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public DemoPanel getDemoPanel() {
        return demoPanel;
    }
}
