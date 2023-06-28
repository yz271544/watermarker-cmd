/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:37 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;

public class UnImplementException extends CmdException {
    public UnImplementException() {
        super(CmdErrorCode.ARGUMENT_INVALID);
    }

    public UnImplementException(String message) {
        super(message, CmdErrorCode.ARGUMENT_INVALID);
    }

    public UnImplementException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.ARGUMENT_INVALID);
    }

    public UnImplementException(String message, CmdErrorCode eventEnum) {
        super(message, eventEnum);
    }

    public UnImplementException(String message, Throwable cause, CmdErrorCode eventEnum) {
        super(message, cause, eventEnum);
    }

    public UnImplementException(Throwable cause) {
        super(cause, CmdErrorCode.ARGUMENT_INVALID);
    }

    public UnImplementException(Exception e) {
        super(e.getMessage(), CmdErrorCode.ARGUMENT_INVALID);
    }
}
