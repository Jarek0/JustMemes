package edu.pl.pollub.exception;

/**
 * Created by Dell on 2017-03-15.
 */
public class ObjectHasNoItemsInTableException extends Exception {
    public ObjectHasNoItemsInTableException(long objectId) {
        super("Object (id: " + objectId + " ) has no items in array");
    }
}
