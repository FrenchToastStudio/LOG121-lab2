package log121.lab2.view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Timer;
import java.util.TimerTask;

public class CustomMouseWheelListener implements MouseWheelListener {

    private static final int TIMER_DELAY = 200;
    private Timer timer;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if(this.timer != null)
        {
            this.timer.cancel();
        }
        this.timer = new Timer();

        final CustomMouseWheelListener mouseWheelListener = this;
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mouseWheelListener.mouseWheelStop();
            }
        }, TIMER_DELAY);
    }

    public void mouseWheelStop()
    {
        this.timer.cancel();
        this.timer = null;
    }
}
