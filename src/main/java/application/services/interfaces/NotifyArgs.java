package application.services.interfaces;

public class NotifyArgs {
    protected final Integer currentTick;
    protected final IObservable subject;
    public NotifyArgs(int currentTick, IObservable subject) {
        super();
        this.currentTick = currentTick;
        this.subject = subject;
    }
    public final IObservable getSubject() { return this.subject; }
    public final Integer getCurrentTick() { return this.currentTick; }
}
