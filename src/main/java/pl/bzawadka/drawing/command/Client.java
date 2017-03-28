package pl.bzawadka.drawing.command;

import pl.bzawadka.drawing.Command;

/**
 * Both an {@link Invoker} object and several {@link Command} objects are held by a {@link Client} object.
 * The client decides which commands to execute at which points.
 * To execute a command, it passes the command object to the invoker object
 */
public @interface Client {
}
