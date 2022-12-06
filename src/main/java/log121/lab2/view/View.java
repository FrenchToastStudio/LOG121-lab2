package log121.lab2.view;


public interface View {

    /**
     * To be called when a view is started
     */
    void activate();

    /**
     * to be called when a view is paused
     */
    void pause();

    /**
     * to be called when a view is resume aka user tabs back in
     */
    void resume();

    /**
     * to be called when a view is destroyed
     */
    void destroy();

}
