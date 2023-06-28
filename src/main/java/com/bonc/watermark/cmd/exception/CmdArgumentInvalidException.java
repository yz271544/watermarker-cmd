/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:37 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;

public class CmdArgumentInvalidException extends CmdException {

    public CmdArgumentInvalidException() {
        super(CmdErrorCode.ARGUMENT_INVALID);
    }

    public CmdArgumentInvalidException(String message) {
        super(message, CmdErrorCode.ARGUMENT_INVALID);
    }

    public CmdArgumentInvalidException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.ARGUMENT_INVALID);
    }

    public CmdArgumentInvalidException(String message, CmdErrorCode eventEnum) {
        super(message, eventEnum);
    }

    public CmdArgumentInvalidException(String message, Throwable cause, CmdErrorCode eventEnum) {
        super(message, cause, eventEnum);
    }

    public CmdArgumentInvalidException(Throwable cause) {
        super(cause, CmdErrorCode.ARGUMENT_INVALID);
    }

    public CmdArgumentInvalidException(Exception e) {
        super(e.getMessage(), CmdErrorCode.ARGUMENT_INVALID);
    }
}
