package pl.bzawadka.drawing;

import pl.bzawadka.drawing.command.Receiver;

/**
 * A command object knows about {@link Receiver} and invokes a method of the receiver
 */
public interface Command {
    void execute();
}
