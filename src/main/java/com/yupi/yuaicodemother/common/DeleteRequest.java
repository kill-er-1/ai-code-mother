package com.yupi.yuaicodemother.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cin
 * @version 1.0
 * @date 2025/7/27 07:38
 * @className DeleteRequest
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
