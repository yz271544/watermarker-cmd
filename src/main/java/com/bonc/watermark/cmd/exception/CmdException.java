/**
 * Copyright © 2016-2019 The CPS Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bonc.watermark.cmd.exception;

public class CmdException extends Exception {

    private static final long serialVersionUID = 1L;

    private CmdErrorCode errorCode;

    public CmdException() {
        super();
    }

    public CmdException(CmdErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CmdException(String message, CmdErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CmdException(String message, Throwable cause, CmdErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public CmdException(Throwable cause, CmdErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public CmdException(Exception e, CmdErrorCode errorCode) {
        super(e.getMessage(), e.getCause());
        this.errorCode = errorCode;
    }

    public CmdErrorCode getErrorCode() {
        return errorCode;
    }



}
