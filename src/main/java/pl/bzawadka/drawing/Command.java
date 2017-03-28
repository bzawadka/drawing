package pl.bzawadka.drawing;

/**
 * A command object knows about {@link Receiver} and invokes a method of the receiver
 */
public interface Command {
    void execute();
}
