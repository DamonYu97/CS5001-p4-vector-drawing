package Util;/*
 * Copyright 2021 Damon Yu
 */

import java.io.*;
import java.util.List;

/**
 * @author 200011181
 * @version 1.0
 */
public class ListUtil<T> {

    public byte[] serialize(List<T> list) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(list);
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public List<T> deserialize(byte[] content) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        List<T> result = (List<T>) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return result;
    }
}
