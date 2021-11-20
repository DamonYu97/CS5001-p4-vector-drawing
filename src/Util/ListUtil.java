package Util;

import java.io.*;
import java.util.List;

/**
 * ListUtil class provides common methods for List object.
 * The stored object in list can be customized, and recognized.
 * @author 200011181
 * @version 1.0
 */
public class ListUtil<T> {

    /**
     * Serialize the list instance with T type.
     * @param list the list instance to be serialized
     * @return an array of bytes represents the list content after serialized
     */
    public byte[] serialize(List<T> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deserialize the array of bytes to a list with T type.
     * @param content the array of bytes to be deserialized
     * @return the list instance with T type deserialized from content.
     */
    public List<T> deserialize(byte[] content) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            List<T> result = (List<T>) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
