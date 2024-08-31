package com.towermanagement.workorderservice.utils;

import java.time.LocalDateTime;

public class CompletedDateUpdateRequest {
    
    private LocalDateTime completedDate;

    public LocalDateTime getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDateTime completedDate) {
        this.completedDate = completedDate;
    }
}