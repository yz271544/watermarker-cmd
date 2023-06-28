/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 14:00 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class ConflictException extends CmdException {

    public ConflictException() {
        super(CmdErrorCode.CONFLICT);
    }

    public ConflictException(String message) {
        super(message, CmdErrorCode.CONFLICT);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.CONFLICT);
    }

    public ConflictException(Throwable cause) {
        super(cause, CmdErrorCode.CONFLICT);
    }

    public ConflictException(Exception e) {
        super(e.getMessage(), CmdErrorCode.CONFLICT);
    }

}
