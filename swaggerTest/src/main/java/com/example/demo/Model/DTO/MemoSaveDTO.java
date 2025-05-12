package com.example.demo.Model.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class MemoSaveDTO {

    private String content;
    private String writer;
}
