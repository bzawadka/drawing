package pl.bzawadka.drawing;

/**
 * A {@link Command} object knows about {@link Receiver} and invokes a method of the receiver.
 * Values for parameters of the receiver method are stored in the command.
 * The receiver then does the work.
 */
public @interface Receiver {
}
