package com.bntech.imperio.gateway.exception;

/***
 * Exception that should be thrown when site can't be found
 * @message Message of exception
 * todo: This should is in common. Need to build it somehow into container.
 */
public class NotFoundException extends Exception {
    public NotFoundException(final String message) {
        super(message);
    }
}
