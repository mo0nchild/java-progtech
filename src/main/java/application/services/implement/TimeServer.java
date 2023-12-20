package application.services.implement;

import application.services.interfaces.IObservable;
import application.services.interfaces.IObserver;
import application.services.interfaces.NotifyArgs;

import java.util.*;

public class TimeServer implements IObservable {
    protected final List<IObserver> subscribers = new ArrayList<>();
    protected final Timer timerHandler = new Timer();
    protected Integer currentTick = 0;
    public TimeServer(int timerStep) {
        super();
        var timerTask = new TimerTask() {
            @Override
            public void run() { notifyAllObserver(); currentTick++; }
        };
        this.timerHandler.schedule(timerTask, new Date(), timerStep);
    }
    public TimeServer(int timerStep, List<IObserver> subscribers) {
        this(timerStep);
        this.subscribers.addAll(subscribers);
    }
    public final Integer getCurrentTick() { return this.currentTick; }
    public final void setCurrentTick(Integer value) { this.currentTick = value; }
    public final List<IObserver> getSubscribers() {
        return new ArrayList<IObserver>(this.subscribers);
    }
    @Override
    public void notifyAllObserver() {
        var eventArgs = new NotifyArgs(this.currentTick, this);
        for (var observer : this.subscribers) observer.update(eventArgs);
    }
    @Override
    public void attach(IObserver subscriber) { this.subscribers.add(subscriber); }
    @Override
    public void detach(IObserver subscriber) { this.subscribers.remove(subscriber); }
}
