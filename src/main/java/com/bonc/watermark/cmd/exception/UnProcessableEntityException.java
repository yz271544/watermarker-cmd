/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 14:03 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class UnProcessableEntityException extends CmdException {

    public UnProcessableEntityException() {
        super(CmdErrorCode.UNPROCESSABLE_ENTITY);
    }

    public UnProcessableEntityException(String message) {
        super(message, CmdErrorCode.UNPROCESSABLE_ENTITY);
    }

    public UnProcessableEntityException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.UNPROCESSABLE_ENTITY);
    }

    public UnProcessableEntityException(Throwable cause) {
        super(cause, CmdErrorCode.UNPROCESSABLE_ENTITY);
    }


    public UnProcessableEntityException(Exception e) {
        super(e.getMessage(), CmdErrorCode.UNPROCESSABLE_ENTITY);
    }
}
