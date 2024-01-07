package com.rishab.dataStreams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Player implements Serializable {
    private final static long serialVersionUID = 1L;
    private final static int version = 2;
    private String name;
    private long topScore;
    private long bigScore; // added after serialization, so it will be 0
//    transient modifier will make sure that this field is not serialized
    private final transient long accountId;
    private List<String> collectedWeapons = new LinkedList<>(); // changed from ArrayList to LinkedList after serialization, but as it's not a primitive type, it will be populated with proper values

    public Player(long accountId, String name, int topScore, List<String> collectedWeapons) {
        this.accountId = accountId;
        this.name = name;
        this.topScore = topScore;
        this.collectedWeapons = collectedWeapons;
    }

    @Override
    public String toString() {
        return "Player{" +
            "id=" + accountId + ", " +
            "name='" + name + '\'' +
            ", topScore=" + topScore +
            ", collectedWeapons=" + collectedWeapons +
            '}';
    }

//    special method to be called by JVM when deserializing an object
    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
//        stream.defaultReadObject();
//        bigScore = (bigScore == 0) ? 100_000_000_000L : bigScore;

        int serializedVersion = stream.readInt();
        collectedWeapons = (List<String>) stream.readObject();
        name = stream.readUTF();
        topScore = (serializedVersion == 1) ? stream.readInt() : stream.readLong();
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        System.out.println("writeObject() called");
        stream.writeInt(version);
        stream.writeObject(collectedWeapons);
        stream.writeUTF(name);
        stream.writeLong(topScore);
    }
}

public class Main {
    public static void main(String[] args) {
//        Path path = Path.of("data.dat");
//        writeDate(path);
//        readData(path);

        Player player = new Player(101, "Alex", 100_000_010, List.of("Knife", "Machete", "Pistol"));
        System.out.println(player);

        Path playerFile = Path.of("player.dat");
//        writeObject(playerFile, player);
        Player deserializedPlayer = readObject(playerFile);
        System.out.println(deserializedPlayer);

        Player player2 = new Player(107, "Bob", 75, List.of("Crossbow", "Rifle", "Pickaxe"));
        Path playerFile2 = Path.of("player2.dat");
        writeObject(playerFile2, player2);
        Player deserializedPlayer2 = readObject(playerFile2);
        System.out.println(player2);
        System.out.println(deserializedPlayer2);
    }

    private static void writeDate(Path path) {
        try (DataOutputStream dataStream = new DataOutputStream(
            new BufferedOutputStream(
                new FileOutputStream(path.toFile())))) {
            int myInt = 17;
            long myLong = 100_000_000_000L;
            boolean myBoolean = true;
            char myChar = 'Z';
            float myFloat = 3.14F;
            double myDouble = 3.14159265359;
            String myString = "I love Java!";

            long position = 0;
            dataStream.writeInt(myInt);
            System.out.println("writeInt writes: " + (dataStream.size() - position) + " bytes");
            position = dataStream.size();

            dataStream.writeLong(myLong);
            System.out.println("writeLong writes: " + (dataStream.size() - position) + " bytes");
            position = dataStream.size();

            dataStream.writeBoolean(myBoolean);
            System.out.println("writeBoolean writes: " + (dataStream.size() - position) + " bytes");
            position = dataStream.size();

            dataStream.writeChar(myChar);
            System.out.println("writeChar writes: " + (dataStream.size() - position) + " bytes");
            position = dataStream.size();

            dataStream.writeFloat(myFloat);
            System.out.println("writeFloat writes: " + (dataStream.size() - position) + " bytes");
            position = dataStream.size();

            dataStream.writeDouble(myDouble);
            System.out.println("writeDouble writes: " + (dataStream.size() - position) + " bytes");
            position = dataStream.size();

            dataStream.writeUTF(myString);
            System.out.println("writeUTF writes: " + (dataStream.size() - position) + " bytes");
            position = dataStream.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readData(Path path) {
        try (DataInputStream dataStream = new DataInputStream(Files.newInputStream(path))) {
            System.out.println("myInt = " + dataStream.readInt());
            System.out.println("myLong = " + dataStream.readLong());
            System.out.println("myBoolean = " + dataStream.readBoolean());
            System.out.println("myChar = " + dataStream.readChar());
            System.out.println("myFloat = " + dataStream.readFloat());
            System.out.println("myDouble = " + dataStream.readDouble());
            System.out.println("myString = " + dataStream.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeObject(Path path, Player player) {
        try (ObjectOutputStream objectStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            objectStream.writeObject(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Player readObject(Path path) {
        try (ObjectInputStream objectStream = new ObjectInputStream(Files.newInputStream(path))) {
            return (Player) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
