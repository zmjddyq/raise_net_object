package com.raise.crowd.exception;

/**
 * @author zmj
 * @date 2020/5/25 18:37
 * @Description 账号密码格式后端校验失败异常
 */
public class AddAdminException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AddAdminException() {
        super();
    }

    public AddAdminException(String message) {
        super(message);
    }

    public AddAdminException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddAdminException(Throwable cause) {
        super(cause);
    }

    protected AddAdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
