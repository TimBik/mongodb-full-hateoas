package ru.itis.jlab.driver.application;

import ru.itis.jlab.driver.models.Type;

public class TypeCodec extends AbstractCodec<Type> {
    public TypeCodec() {
        super();
    }

    @Override
    public Class<Type> getEncoderClass() {
        return Type.class;
    }
}
