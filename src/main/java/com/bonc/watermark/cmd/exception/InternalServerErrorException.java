/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 14:03 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class InternalServerErrorException extends CmdException {

    public InternalServerErrorException() {
        super(CmdErrorCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message) {
        super(message, CmdErrorCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause, CmdErrorCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(Exception e) {
        super(e.getMessage(), CmdErrorCode.INTERNAL_SERVER_ERROR);
    }
}
