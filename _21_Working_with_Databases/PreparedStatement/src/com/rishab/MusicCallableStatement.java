package com.rishab;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MusicCallableStatement {
    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {

        Map<String, Map<String, String>> albums;

        try (Stream<String> lines = Files.lines(Path.of("NewAlbums.csv"))) {
            albums = lines.map(line -> line.split(","))
                .collect(Collectors.groupingBy(token -> token[ARTIST_COLUMN],
                    Collectors.groupingBy(token -> token[ALBUM_COLUMN],
                        Collectors.mapping(token -> token[SONG_COLUMN],
                            Collectors.joining("\",\"", "[\"", "\"]")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        albums.forEach((artist, artistAlbums) -> {
            artistAlbums.forEach((album, songs) -> {
                System.out.println(album + ": " + songs);
            });
        });

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("music");

        try (Connection connection = dataSource.getConnection(System.getenv("MYSQL_USER"), System.getenv("MYSQL_PASS"))) {
            /*
            CallableStatement callableStatement = connection.prepareCall("{CALL music.addAlbumInOutCounts(?, ?, ?, ?)}");

            albums.forEach((artist, albumMap) -> {
                albumMap.forEach((album, songs) -> {
                    try {
                        callableStatement.setString(1, artist);
                        callableStatement.setString(2, album);
                        callableStatement.setString(3, songs);
                        callableStatement.setInt(4, 10);
                        callableStatement.registerOutParameter(4, Types.INTEGER);
                        callableStatement.execute();

                        System.out.printf("Added %d songs to album: %s for artist: %s%n",
                            callableStatement.getInt(4), album, artist);
                    } catch (SQLException e) {
                        System.err.println("Error adding album: " + album + " for artist: " + artist);
                        System.err.println(e.getErrorCode() + ": " + e.getMessage());
                    }
                });
            });
             */

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "Bob Dylan");
            ResultSet resultSet = preparedStatement.executeQuery();
            Main.printRecords(resultSet);

            CallableStatement callableStatement = connection.prepareCall("{ ? = CALL music.calcAlbumLength(?) }");
            callableStatement.registerOutParameter(1, Types.DOUBLE);

            albums.forEach((artist, albumMap) -> {
                albumMap.keySet().forEach(albumName -> {
                    try {
                        callableStatement.setString(2, albumName);
                        callableStatement.execute();
                        double result = callableStatement.getDouble(1);
                        System.out.printf("Album: %s by %s is %.2f minutes long%n", albumName, artist, result);
                    } catch (SQLException e) {
                        System.err.println("Error calculating album length for album: " + albumName + " by artist: " + artist);
                        System.err.println(e.getErrorCode() + ": " + e.getMessage());
                    }
                });
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
