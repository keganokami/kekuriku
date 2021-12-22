package com.rakuriku.rakuriku.presentation.controller.compe.request;

import java.util.List;

import com.rakuriku.rakuriku.presentation.controller.compe.EntryEventDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryCompeRequest {
    private String compeId;
    private String name;
    private String nameKana;
    private String team;
    private String phoneNum;
    private String sex;
    private List<EntryEventDto> compeEvent;
}
