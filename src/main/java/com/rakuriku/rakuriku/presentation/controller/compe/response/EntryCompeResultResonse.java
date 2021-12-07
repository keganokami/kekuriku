package com.rakuriku.rakuriku.presentation.controller.compe.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryCompeResultResonse {
    private String compeId;
    private String successMsg;
    private String errorMsg; 
}
