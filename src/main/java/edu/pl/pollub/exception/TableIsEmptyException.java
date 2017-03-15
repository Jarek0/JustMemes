package edu.pl.pollub.exception;

/**
 * Created by Dell on 2017-03-15.
 */
public class TableIsEmptyException extends Exception {
    public TableIsEmptyException(String tableName) {
        super("Table: " + tableName + " is empty, problem with connection(?) or you've just dropped it");
    }
}
