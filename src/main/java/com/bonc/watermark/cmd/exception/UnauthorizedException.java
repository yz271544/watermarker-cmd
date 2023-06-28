/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:57 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class UnauthorizedException extends CmdException {

    public UnauthorizedException() {
        super(CmdErrorCode.UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
        super(message, CmdErrorCode.UNAUTHORIZED);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.UNAUTHORIZED);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause, CmdErrorCode.UNAUTHORIZED);
    }

    public UnauthorizedException(Exception e) {
        super(e.getMessage(), CmdErrorCode.UNAUTHORIZED);
    }
}
