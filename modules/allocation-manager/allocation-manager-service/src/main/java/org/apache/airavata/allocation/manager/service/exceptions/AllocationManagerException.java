package org.apache.airavata.allocation.manager.service.exceptions;


public class AllocationManagerException extends Exception{
    public AllocationManagerException(Throwable e) {
        super(e);
    }

    public AllocationManagerException(String message) {
        super(message, null);
    }

    public AllocationManagerException(String message, Throwable e) {
        super(message, e);
    }

}
