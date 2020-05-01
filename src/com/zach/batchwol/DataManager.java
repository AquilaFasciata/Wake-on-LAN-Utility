package com.zach.batchwol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataManager {

    public static void save(Serializable data, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static Object load(String fileName) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            return ois.readObject();
        } catch (IOException e) {
            System.out.println(e.toString());
            throw new Exception("Object doesn't exist");
        }
    }

}
