package com.rakuriku.rakuriku.presentation.controller.compe.request;

import java.util.List;

import lombok.Data;

@Data
public class EntryCompeRequest {
    private String name;
    private String nameKana;
    private String team;
    private String phoneNum;
    private String sex;
    private List<EntryEventRequest> compeEvent;
}
