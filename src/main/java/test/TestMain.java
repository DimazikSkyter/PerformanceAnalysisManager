package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestMain {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        TestSerialisedClass testSerialisedClass =  TestSerialisedClass.newInstance(1);
        String name = "test.txt";
//        FileOutputStream fileOutputStream
//                = new FileOutputStream(name);
//        ObjectOutputStream objectOutputStream
//                = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(testSerialisedClass);
//        objectOutputStream.flush();
//        objectOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream(name);
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        TestSerialisedClass testSerialisedClass2 = (TestSerialisedClass) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(testSerialisedClass2);
    }
}
