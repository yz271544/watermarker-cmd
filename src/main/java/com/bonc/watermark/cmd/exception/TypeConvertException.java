/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:37 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;


public class TypeConvertException extends CmdException {

    public TypeConvertException() {
        super(CmdErrorCode.TYPE_CONVERT_ERROR);
    }

    public TypeConvertException(String message) {
        super(message, CmdErrorCode.TYPE_CONVERT_ERROR);
    }

    public TypeConvertException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.TYPE_CONVERT_ERROR);
    }

    public TypeConvertException(Throwable cause) {
        super(cause, CmdErrorCode.TYPE_CONVERT_ERROR);
    }

    public TypeConvertException(Exception e) {
        super(e.getMessage(), CmdErrorCode.TYPE_CONVERT_ERROR);
    }
}
