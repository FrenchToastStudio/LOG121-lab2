package log121.lab2.view.components.listener;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Custom Mouse wheel Listener with a stop wheel event
 */
public class CustomMouseWheelListener implements MouseWheelListener {

    private static final int TIMER_DELAY = 200;
    private Timer timer;

    /**
     * run when the mouse wheel is used and waits for it to stop
     * @param e the event to be processed
     */
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

    /**
     * run when the mouse wheel has been inactive for a x amount of time
     */
    public void mouseWheelStop()
    {
        this.timer.cancel();
        this.timer = null;
    }
}
