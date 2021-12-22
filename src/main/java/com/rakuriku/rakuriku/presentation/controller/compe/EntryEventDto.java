package com.rakuriku.rakuriku.presentation.controller.compe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryEventDto {
    private String eventId;
    private String eventName;
    private String eventCategory;
    private String eventRecode;
}
