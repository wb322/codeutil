package com.bitvalue.edgecache.annotation.log;

/**
 * 业务操作类型
 */
public enum LogType {
    /**
     * 其它
     */
    OTHER,

    /**
     * 查询
     */
    SELECT,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN,

    /** 鉴权 */
    VALIDATE,

    /**
     * 生成配置
     */
    UPDATE_CONFIG,
}
