package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Command;

/**
 * An invoker object knows how to execute a {@link Command}. The invoker does not
 * know anything about a concrete command, it knows only about command interface.
 */
public @interface Invoker {
}
