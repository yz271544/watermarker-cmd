/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:37 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class SystemUnknowtException extends CmdException {

    public SystemUnknowtException() {
        super(CmdErrorCode.SYSTEM_UNKNOWN_ERROR);
    }

    public SystemUnknowtException(String message) {
        super(message, CmdErrorCode.SYSTEM_UNKNOWN_ERROR);
    }

    public SystemUnknowtException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.SYSTEM_UNKNOWN_ERROR);
    }

    public SystemUnknowtException(Throwable cause) {
        super(cause, CmdErrorCode.SYSTEM_UNKNOWN_ERROR);
    }

    public SystemUnknowtException(Exception e) {
        super(e.getMessage(), CmdErrorCode.SYSTEM_UNKNOWN_ERROR);
    }
}
