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


public enum CmdErrorCode {

    SUCCESS(0, "成功执行", "Success executed"),
    CONTINUE(100, "继续执行", "Continue executed"),
    SWITCHING_PROTOCOLS(101, "协议切换", "Switching protocols"),
    PROCESSING(102, "处理中", "Processing"),
    EARLY_HINTS(103, "早期提示", "Early hints"),
    CREATED(201, "已创建", "Created"),
    ACCEPTED(202, "已接受", "Accepted"),
    NON_AUTHORITATIVE_INFORMATION(203, "非授权信息", "Non-authoritative information"),
    NO_CONTENT(204, "无内容", "No content"),
    RESET_CONTENT(205, "重置内容", "Reset content"),
    PARTIAL_CONTENT(206, "部分内容", "Partial content"),
    MULTI_STATUS(207, "多状态", "Multi status"),
    ALREADY_REPORTED(208, "已报告", "Already reported"),
    IM_USED(226, "IM用于", "IM used"),
    MULTIPLE_CHOICES(300, "多种选择", "Multiple choices"),
    MOVED_PERMANENTLY(301, "永久移动", "Moved permanently"),
    FOUND(302, "找到", "Found"),
    SEE_OTHER(303, "看到其他", "See other"),
    NOT_MODIFIED(304, "未修改", "Not modified"),
    USE_PROXY(305, "使用代理", "Use proxy"),
    SWITCH_PROXY(306, "切换代理", "Switch proxy"),
    TEMPORARY_REDIRECT(307, "临时重定向", "Temporary redirect"),
    PERMANENT_REDIRECT(308, "永久重定向", "Permanent redirect"),
    BAD_REQUEST(400, "错误请求", "Bad request"),
    UNAUTHORIZED(401, "未授权", "Unauthorized"),
    PAYMENT_REQUIRED(402, "付款要求", "Payment required"),
    FORBIDDEN(403, "禁止", "Forbidden"),
    NOT_FOUND(404, "未找到", "Not found"),
    METHOD_NOT_ALLOWED(405, "不允许的方法", "Method not allowed"),
    NOT_ACCEPTABLE(406, "不可接受", "Not acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "代理验证要求", "Proxy authentication required"),
    REQUEST_TIMEOUT(408, "请求超时", "Request timeout"),
    CONFLICT(409, "冲突", "Conflict"),
    GONE(410, "已经不存在", "Gone"),
    LENGTH_REQUIRED(411, "长度要求", "Length required"),
    PRECONDITION_FAILED(412, "前提失败", "Precondition failed"),
    PAYLOAD_TOO_LARGE(413, "负载太大", "Payload too large"),
    URI_TOO_LONG(414, "URI太长", "URI too long"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型", "Unsupported media type"),
    RANGE_NOT_SATISFIABLE(416, "范围不符合", "Range not satisfiable"),
    EXPECTATION_FAILED(417, "期望失败", "Expectation failed"),
    IM_A_TEAPOT(418, "IM是一杯茶", "I'm a teapot"),
    MISDIRECTED_REQUEST(421, "错误的请求", "Misdirected request"),
    UNPROCESSABLE_ENTITY(422, "不可处理的实体", "Unprocessable entity"),
    LOCKED(423, "锁定", "Locked"),
    FAILED_DEPENDENCY(424, "失败的依赖", "Failed dependency"),
    TOO_EARLY(425, "太早", "Too early"),
    UPGRADE_REQUIRED(426, "需要升级", "Upgrade required"),
    PRECONDITION_REQUIRED(428, "需要前提", "Precondition required"),
    TOO_MANY_REQUESTS(429, "太多请求", "Too many requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "请求头字段太大", "Request header fields too large"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "不可用的法律原因", "Unavailable for legal reasons"),
    INTERNAL_SERVER_ERROR(500, "内部服务器错误", "Internal server error"),
    NOT_IMPLEMENTED(501, "未实现", "Not implemented"),
    BAD_GATEWAY(502, "错误的网关", "Bad gateway"),
    SERVICE_UNAVAILABLE(503, "服务不可用", "Service unavailable"),
    GATEWAY_TIMEOUT(504, "网关超时", "Gateway timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP版本不受支持", "HTTP version not supported"),
    VARIANT_ALSO_NEGOTIATES(506, "变体也协商", "Variant also negotiates"),
    INSUFFICIENT_STORAGE(507, "存储不足", "Insufficient storage"),
    LOOP_DETECTED(508, "检测到循环", "Loop detected"),
    NOT_EXTENDED(510, "未扩展", "Not extended"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "网络验证要求", "Network authentication required"),

    ARGUMENT_INVALID(600, "命令行参数无效", "input argument invalid"),
    FILE_NOT_EXISTS_OR_CANNOT_READ(601, "文件不存在或不可读", "file not exists or cannot read"),
    UN_IMPLEMENT_FILE_TYPE(700, "未实现识别的文件类型", "unimplemented the file type"),


    TYPE_CONVERT_ERROR(1000, "类型转换错误", "Type convert error"),
    TYPE_CHECK_ERROR(1001, "类型验证错误", "Type check error"),

    SYSTEM_ERROR(9999, "系统错误", "System error"),
    SYSTEM_UNKNOWN_ERROR(-1, "系统未知错误", "System unknown error"),
    ;

    private final int errorCode;
    private final String desc;
    private final String tip;

    CmdErrorCode(int errorCode, final String desc, final String tip) {
        this.errorCode = errorCode;
        this.desc = desc;
        this.tip = tip;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDesc() {
        return desc;
    }

    public String getTip() {
        return tip;
    }

    public String toString() {
        return getTip();
    }

    public static CmdErrorCode fromEventCode(final int statusCode) {
        for (CmdErrorCode cmdErrorCode : CmdErrorCode.values()) {
            if (cmdErrorCode.errorCode == statusCode) {
                return cmdErrorCode;
            }
        }
        return null;
    }
}
