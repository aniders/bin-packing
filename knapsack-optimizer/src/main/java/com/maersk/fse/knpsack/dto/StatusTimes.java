package com.maersk.fse.knpsack.dto;

import lombok.Getter;

@Getter
public class StatusTimes {

    private Long submitted;

    private Long started;

    private Long completed;

    public StatusTimes() {
        this.submitted = System.currentTimeMillis() / 1000L;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

}
