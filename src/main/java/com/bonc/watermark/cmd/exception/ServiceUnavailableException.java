/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 14:05 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class ServiceUnavailableException extends CmdException {

    public ServiceUnavailableException() {
        super(CmdErrorCode.SERVICE_UNAVAILABLE);
    }

    public ServiceUnavailableException(String message) {
        super(message, CmdErrorCode.SERVICE_UNAVAILABLE);
    }

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.SERVICE_UNAVAILABLE);
    }

    public ServiceUnavailableException(Throwable cause) {
        super(cause, CmdErrorCode.SERVICE_UNAVAILABLE);
    }

    public ServiceUnavailableException(Exception e) {
        super(e.getMessage(), CmdErrorCode.SERVICE_UNAVAILABLE);
    }
}
