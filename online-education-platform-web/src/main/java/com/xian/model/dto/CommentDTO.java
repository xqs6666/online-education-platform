package com.xian.model.dto;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@Getter
@Setter
@ApiModel(value = "CommentDTO对象", description = "")
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;
    private Integer courseId;

}
