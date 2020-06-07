package com.example.diary.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class teacherCommentForm {
    @NotBlank(groups = ValidGroup1.class)
    @Length(max = 30, groups = ValidGroup2.class)
    private String teacherComment;
}