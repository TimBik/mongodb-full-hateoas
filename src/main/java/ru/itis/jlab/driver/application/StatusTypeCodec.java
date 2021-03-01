package ru.itis.jlab.driver.application;

import ru.itis.jlab.driver.models.Status;

public class StatusTypeCodec extends AbstractCodec<Status> {
    public StatusTypeCodec() {
        super();
    }

    @Override
    public Class<Status> getEncoderClass() {
        return Status.class;
    }
}
