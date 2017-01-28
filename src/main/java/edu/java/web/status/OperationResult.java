package edu.java.web.status;

/**
 * Created by edwin on 25/01/17.
 */

public class OperationResult {

    private OperationStatus status;
    private Exception ex;
    private Object resultObject;

    public OperationResult(OperationStatus status) {
        this.status=status;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public Exception getException() {
        return ex;
    }

    public void setException (Exception ex) {
        this.ex=ex;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject=resultObject;
    }

    public Object getResultObject() {
        return resultObject;
    }

}
