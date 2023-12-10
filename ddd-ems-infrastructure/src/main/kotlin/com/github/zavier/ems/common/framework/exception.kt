package com.github.zavier.ems.common.framework

class BusinessException(msg: String) : RuntimeException(msg)

class DirtyDataException(msg: String) : RuntimeException(msg)

class SystemException : RuntimeException {
    constructor(msg: String) : super(msg)
    constructor(msg: String, cause: Throwable?) : super(msg, cause)
}
