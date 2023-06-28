/**
 * @project cps
 * @description
 * @errorcode SystemException</ br>
 * @author 2022 13:37 huzhengyang Create 1.0 <br>
 * @copyright ©2022-2023
 */

package com.bonc.watermark.cmd.exception;

public class FileNotExistsOrCannotReadException extends CmdException {

    public FileNotExistsOrCannotReadException() {
        super(CmdErrorCode.FILE_NOT_EXISTS_OR_CANNOT_READ);
    }

    public FileNotExistsOrCannotReadException(String message) {
        super(message, CmdErrorCode.FILE_NOT_EXISTS_OR_CANNOT_READ);
    }

    public FileNotExistsOrCannotReadException(String message, Throwable cause) {
        super(message, cause, CmdErrorCode.FILE_NOT_EXISTS_OR_CANNOT_READ);
    }

    public FileNotExistsOrCannotReadException(String message, CmdErrorCode eventEnum) {
        super(message, eventEnum);
    }

    public FileNotExistsOrCannotReadException(String message, Throwable cause, CmdErrorCode eventEnum) {
        super(message, cause, eventEnum);
    }

    public FileNotExistsOrCannotReadException(Throwable cause) {
        super(cause, CmdErrorCode.FILE_NOT_EXISTS_OR_CANNOT_READ);
    }

    public FileNotExistsOrCannotReadException(Exception e) {
        super(e.getMessage(), CmdErrorCode.FILE_NOT_EXISTS_OR_CANNOT_READ);
    }
}
