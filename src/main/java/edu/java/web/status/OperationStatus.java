package edu.java.web.status;

/**
 * Created by edwin on 25/01/17.
 */
public enum OperationStatus  {

    SUCCESS(0), FAILED(1), ROLLBACK(2), NOT_FOUND(3);

    OperationStatus(int errorCode) {
        this.errorCode=errorCode;
    }

    private int errorCode;


    public int getErrorCode() {
        return this.errorCode;
    }

}
