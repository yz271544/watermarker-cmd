/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:58 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class ForbiddenException extends CmdException {

    public ForbiddenException() {
        super(CmdErrorCode.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message, CmdErrorCode.FORBIDDEN);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.FORBIDDEN);
    }

    public ForbiddenException(Throwable cause) {
        super(cause, CmdErrorCode.FORBIDDEN);
    }

    public ForbiddenException(Exception e) {
        super(e.getMessage(), CmdErrorCode.FORBIDDEN);
    }

}
