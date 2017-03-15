package edu.pl.pollub.exception;

/**
 * Created by Dell on 2017-03-15.
 */
public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(long objectId) {
        super("There is no object of this id: " + objectId);
    }

    public ObjectNotFoundException(String objectName) {
        super("There is no object with name: " + objectName);
    }
}
