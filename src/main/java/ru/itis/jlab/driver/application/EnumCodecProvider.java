package ru.itis.jlab.driver.application;

import ru.itis.jlab.driver.models.Status;
import ru.itis.jlab.driver.models.Type;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class EnumCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Status.class) {
            return (Codec<T>) new StatusTypeCodec();
        }
        if (clazz == Type.class) {
            return (Codec<T>) new TypeCodec();
        }
        return null; // Don't throw here, this tells Mongo this provider doesn't provide a decoder for the requested clazz
    }
}