/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:59 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class NotFoundException extends CmdException {

    public NotFoundException() {
        super(CmdErrorCode.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, CmdErrorCode.NOT_FOUND);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.NOT_FOUND);
    }

    public NotFoundException(Throwable cause) {
        super(cause, CmdErrorCode.NOT_FOUND);
    }

    public NotFoundException(Exception e) {
        super(e.getMessage(), CmdErrorCode.NOT_FOUND);
    }
}
