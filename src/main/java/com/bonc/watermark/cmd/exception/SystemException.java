/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:37 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class SystemException extends CmdException {

    public SystemException() {
        super(CmdErrorCode.BAD_REQUEST);
    }

    public SystemException(String message) {
        super(message, CmdErrorCode.BAD_REQUEST);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.BAD_REQUEST);
    }

    public SystemException(Throwable cause) {
        super(cause, CmdErrorCode.BAD_REQUEST);
    }

    public SystemException(Exception e) {
        super(e.getMessage(), CmdErrorCode.BAD_REQUEST);
    }
}
