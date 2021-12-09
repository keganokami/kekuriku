package com.rakuriku.rakuriku.presentation.controller.compe.request;

import lombok.Data;

@Data
public class EntryEventRequest {
    private String eventId;
    private String eventName;
    private String eventCategory;
    private String eventRecode;
}
