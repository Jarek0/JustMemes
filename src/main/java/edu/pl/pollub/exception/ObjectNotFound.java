package edu.pl.pollub.exception;

/**
 * Created by Dell on 2017-03-15.
 */
public class ObjectNotFound extends Exception {
    public ObjectNotFound(long objectId) {
        super("There is no object of this id: " + objectId);
    }

    public ObjectNotFound(String objectName) {
        super("There is no object with name: " + objectName);
    }
}
