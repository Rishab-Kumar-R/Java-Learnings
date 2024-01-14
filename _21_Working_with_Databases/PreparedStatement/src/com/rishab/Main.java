package com.rishab;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String ARTIST_INSERT = "INSERT INTO music.artists (artist_name) VALUES (?)";
    private static final String ALBUM_INSERT = "INSERT INTO music.albums (artist_id, album_name) VALUES (?, ?)";
    private static final String SONG_INSERT = "INSERT INTO music.songs (album_id, track_number, song_title) VALUES (?, ?, ?)";

    public static void main(String[] args) {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("music");

        try {
            dataSource.setContinueBatchOnError(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"))) {

            addDataFromFile(connection);

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Bob Dylan");
            ResultSet resultSet = preparedStatement.executeQuery();
            printRecords(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean printRecords(ResultSet resultSet) throws SQLException {
        boolean found = false;
        ResultSetMetaData metaData = resultSet.getMetaData();

        System.out.println("---------------------------------");
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.printf("%-15s", metaData.getColumnName(i).toUpperCase());
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.printf("%-15s", resultSet.getString(i));
            }
            System.out.println();
            found = true;
        }
        return found;
    }

    private static int addArtist(Connection connection, PreparedStatement preparedStatement,
                                 String artistName) throws SQLException {
        int artistId = -1;
        preparedStatement.setString(1, artistName);
        int insertedCount = preparedStatement.executeUpdate();

        if (insertedCount > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                artistId = resultSet.getInt(1);
                System.out.println("Artist ID: " + artistId);
            }
        }
        return artistId;
    }

    private static int addAlbum(Connection connection, PreparedStatement preparedStatement,
                                int artistId, String albumName) throws SQLException {
        int albumId = -1;
        preparedStatement.setInt(1, artistId);
        preparedStatement.setString(2, albumName);
        int insertedCount = preparedStatement.executeUpdate();

        if (insertedCount > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                albumId = resultSet.getInt(1);
                System.out.println("Album ID: " + albumId);
            }
        }
        return albumId;
    }

    private static void addSong(Connection connection, PreparedStatement preparedStatement,
                               int albumId, int trackNumber, String songTitle) throws SQLException {
        preparedStatement.setInt(1, albumId);
        preparedStatement.setInt(2, trackNumber);
        preparedStatement.setString(3, songTitle);
        preparedStatement.addBatch();
    }

    private static void addDataFromFile(Connection connection) throws SQLException {
        List<String> records;

        try {
            records = Files.readAllLines(Path.of("NewAlbums.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String lastArtist = null;
        String lastAlbum = null;
        int artistId = -1;
        int albumId = -1;

        try (PreparedStatement preparedArtist = connection.prepareStatement(ARTIST_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedAlbum = connection.prepareStatement(ALBUM_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedSong = connection.prepareStatement(SONG_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            for (String record : records) {
                String[] columns = record.split(",");

//                assuming that artists are new and not already in the database
                if (lastArtist == null || !lastArtist.equals(columns[0])) {
                    lastArtist = columns[0];
                    artistId = addArtist(connection, preparedArtist, lastArtist);
                }

                if (lastAlbum == null || !lastAlbum.equals(columns[1])) {
                    lastAlbum = columns[1];
                    albumId = addAlbum(connection, preparedAlbum, artistId, lastAlbum);
                }

                addSong(connection, preparedSong, albumId, Integer.parseInt(columns[2]), columns[3]);
            }

            int[] inserts = preparedSong.executeBatch();
            int totalInserts = Arrays.stream(inserts).sum();
            System.out.printf("%d song records added%n", inserts.length);

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }
}
