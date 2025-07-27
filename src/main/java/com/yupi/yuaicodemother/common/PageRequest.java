package com.yupi.yuaicodemother.common;

import lombok.Data;

/**
 * @author cin
 * @version 1.0
 * @date 2025/7/27 07:36
 * @className PageRequest
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int pageNum = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = "descend";
}

