package com.aniders.fse.knpsack.dto;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Task {

    private String task;

    private String status;

    private StatusTimes timestamps;

    public Task() {
        super();
        this.task = UUID.randomUUID().toString();
        this.status = Status.SUBMITTED.value;
        this.timestamps = new StatusTimes();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Task statuses - submitted, started, completed
     * 
     * @author Aniruddh
     *
     */
    public enum Status {
        SUBMITTED("submitted"), STARTED("started"), COMPLETED("completed");

        String value;

        Status(String status) {
            this.value = status;
        }

        public String getValue() {
            return value;
        }
    }

}
