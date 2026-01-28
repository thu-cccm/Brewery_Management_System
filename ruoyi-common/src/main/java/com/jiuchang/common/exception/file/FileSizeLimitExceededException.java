package com.jiuchang.common.exception.file;

/**
 * 文件名大小限制异常类
 * 
 * @author jiuchang
 */
public class FileSizeLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}
