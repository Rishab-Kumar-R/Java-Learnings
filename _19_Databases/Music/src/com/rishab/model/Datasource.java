package com.rishab.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\Development\\Java-Learnings\\_19_Databases\\Music\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    /*
    SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artists._id WHERE artists.name = "Carole King"
    ORDER BY albums.name COLLATE NOCASE ASC;
     */
    public static final String QUERY_ALBUMS_BY_ARTIST_START =
        "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS + " INNER JOIN " + TABLE_ARTISTS +
            " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID +
            " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " ='";
    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
        " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    /*
    SELECT artists.name, albums.name, songs.track FROM songs
    INNER JOIN albums ON songs.album = albums._id
    INNER JOIN artists ON albums.artist = artists._id
    WHERE songs.title="She's On Fire"
    ORDER BY artists.name, albums.name COLLATE NOCASE ASC;
     */
    public static final String QUERY_ARTIST_FOR_SONG_START =
        "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
            TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS + " INNER JOIN " + TABLE_ALBUMS + " ON " +
            TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + " INNER JOIN " +
            TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." +
            COLUMN_ARTIST_ID + " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";
    public static final String QUERY_ARTIST_FOR_SONG_SORT =
        " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME +
            " COLLATE NOCASE ";

    /*
    CREATE VIEW IF NOT EXISTS artist_list AS SELECT artists.name, albums.name AS album, songs.track, songs.title FROM songs
    INNER JOIN albums ON songs.album = albums._id
    INNER JOIN artists ON albums.artist = artists._id
    ORDER BY artists.name, albums.name, songs.track;
     */
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " + TABLE_ARTIST_SONG_VIEW +
        " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME +
        " AS " + COLUMN_SONG_ALBUM + ", " + TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." +
        COLUMN_SONG_TITLE + " FROM " + TABLE_SONGS + " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." +
        COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + " INNER JOIN " + TABLE_ARTISTS + " ON " +
        TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " ORDER BY " +
        TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
        TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    /*
    SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way";

    When requesting input from the user, if we use the above query, the user can enter something like:
    Go Your Own Way" or 1=1 or " and the query will become:
    SELECT name, album, track FROM artist_list WHERE title = "Go Your Own Way" or 1=1 or "";
    This will return all the rows in the table.
     */
    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ARTIST_NAME + ", " + COLUMN_SONG_ALBUM + ", " +
        COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_TITLE + " = \"";

    /*
    The above query is vulnerable to SQL injection attacks. To prevent this, we use prepared statements.
    SELECT name, album, track FROM artist_list WHERE title =?;
     */
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTIST_NAME + ", " + COLUMN_SONG_ALBUM + ", " +
        COLUMN_SONG_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONG_TITLE + " = ?";

    /*
    There a caveat with prepared statements. We can't use them to substitute table names, column names, or lists of values.
    For example, if we use the following query:
    SELECT name, album, track FROM artist_list WHERE title =? OR artist =?;
    We can't substitute more than one value for the same placeholder.
     */

    /*
    So, what we can do is, we can use a prepared statement to substitute the title, and then we can use a regular statement
    to substitute the artist name.

    1. Declare a constant for the SQL statement that contains the placeholder.
    2. Create a prepared statement instance using Connection.prepareStatement(sql).
    3. When we're ready to perform the query (INSERT, UPDATE, DELETE), we can call the appropriate setter method on the prepared
       statement instance to set the value of the placeholder.
    4. We run the statement using PreparedStatement.execute() or PreparedStatement.executeQuery().
    5. We process the results the same way we do using a regular statement.
     */

    /*
    **Transactions**: A transaction is a group of operations that either all succeed or all fail.
    For example, if we're transferring money from one account to another, we need to make sure that the money is removed from
    the first account and added to the second account. If the money is removed from the first account, but not added to the
    second account, then we have a problem.
    *
    * Note that when we're speaking about databases, we usually use the term "commit" rather than "save", when referring to
      make any changes permanent. We'll use commit from the point forward.
    *
    * Database transactions must be ACID-compliant. ACID stands for Atomic, Consistent, Isolated, and Durable.
    * 1. Atomic: A transaction must be atomic. This means that either all the operations in the transaction succeed, or none of
         them do. There's no in-between state.
    * 2. Consistent: A transaction must be consistent. This means that the database must be in a consistent state when the
         transaction begins, and it must be in a consistent state when the transaction ends.
    * 3. Isolated: A transaction must be isolated. This means that the transaction must appear to be isolated from all other
         transactions. This means that if we have two transactions, T1 and T2, then T1 must appear to be completed before T2
         begins. This is true even if T1 and T2 are running at the same time.
    * 4. Durable: A transaction must be durable. This means that once a transaction is complete, the changes made by the
         transaction must be permanent. If the database crashes after a transaction has completed, then the changes made by the
         transaction must not be lost.
    *
    * We only have to use transactions when we change the data in the database. We don't need to use transactions when we're
      only reading data from the database. SQLite uses transactions by default, and auto-commits the transaction when we make
      a change to the database. In fact, no changes can be made to the database outside a transaction. So, if we want to make
      a change to the database, we need to start a transaction, make the change, and then commit the transaction.
    *
    * The JDBC Connection class also auto-commits changes to the database by default. So, if we want to use transactions, we
      need to disable auto-commit mode. We can do this by calling Connection.setAutoCommit(false). We can then commit the
      transaction by calling Connection.commit(). If we want to roll back the transaction, we can call Connection.rollback().
    *
    * Commands that are used for transactions:
    * 1. BEGIN TRANSACTION: This starts a new transaction.
    * 2. END TRANSACTION: This ends the current transaction. Committing changes automatically ends the transaction. Also,
         ending a transaction automatically commits the changes. In other words, END TRANSACTION and COMMIT are aliases.
         We only have to use one when we want to end or commit a transaction.
    * 3. COMMIT: This commits the current transaction. This makes the changes permanent.
    * 4. ROLLBACK: This rolls back the current transaction. This undoes any uncommitted changes and ends the transaction.
         Note that it can only roll back changes that have occurred since the last COMMIT or ROLLBACK command.

    * Note that if we close a connection before we commit any outstanding changes, then the changes will be rolled back.
    *
    * How to use transactions:
    * 1. Disable auto-commit mode by calling `Connection.setAutoCommit(false)`.
    * 2. Perform SQL operations that we want to be part of the transaction.
    * 3. If there are no errors, call Connection.commit() to commit the transaction. If there are errors, call
         Connection.rollback() to roll back the transaction.
    * 4. Re-enable auto-commit mode by calling `Connection.setAutoCommit(true)`.
     */
    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS + "(" + COLUMN_ARTIST_NAME + ") VALUES(?)";
    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS + "(" + COLUMN_ALBUM_NAME + ", " +
        COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";
    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS + "(" + COLUMN_SONG_TRACK + ", " +
        COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM + ") VALUES(?, ?, ?)";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " + TABLE_ARTISTS + " WHERE " +
        COLUMN_ARTIST_NAME + " = ?";
    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE " +
        COLUMN_ALBUM_NAME + " = ?";

    private Connection conn;
    private PreparedStatement querySongInfoView;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);

            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);

            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (querySongInfoView != null) {
                querySongInfoView.close();
            }

            if (insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if (insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if (insertIntoSongs != null) {
                insertIntoSongs.close();
            }

            if (queryArtist != null) {
                queryArtist.close();
            }
            if (queryAlbum != null) {
                queryAlbum.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtists(int sortOrder) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");

            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else if (sortOrder == ORDER_BY_ASC) {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("'");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else if (sortOrder == ORDER_BY_ASC) {
                sb.append("ASC");
            }
        }

        System.out.println("SQL statement = " + sb);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<String> albums = new ArrayList<>();
            while (results.next()) {
                albums.add(results.getString(1));
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        sb.append(songName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ARTIST_FOR_SONG_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else if (sortOrder == ORDER_BY_ASC) {
                sb.append("ASC");
            }
        }

        System.out.println("SQL statement = " + sb);

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<SongArtist> songArtists = new ArrayList<>();
            while (results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void querySongsMetaData() {
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

//            getting schema information about the table
            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();

            for (int i = 1; i <= numColumns; i++) {
                System.out.printf("Column %d in the songs table is names %s\n", i, meta.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count FROM " + table;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)) {

            return results.getInt("count");
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return -1;
        }
    }

    public boolean createViewForSongArtists() {
        try (Statement statement = conn.createStatement()) {
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException e) {
            System.out.println("Create view failed: " + e.getMessage());
            return false;
        }
    }

    public List<SongArtist> querySongInfoView(String title) {
        try {
            querySongInfoView.setString(1, title);
            ResultSet results = querySongInfoView.executeQuery();

            List<SongArtist> songArtists = new ArrayList<>();
            while (results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    private int insertArtist(String name) throws SQLException {
        queryArtist.setString(1, name);
        ResultSet results = queryArtist.executeQuery();

        if (results.next()) {
            return results.getInt(1);
        } else {
//            Insert the artist
            insertIntoArtists.setString(1, name);

            int affectedRows = insertIntoArtists.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert artist!");
            }

            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist!");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {
        queryAlbum.setString(1, name);
        ResultSet results = queryAlbum.executeQuery();

        if (results.next()) {
            return results.getInt(1);
        } else {
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);

            int affectedRows = insertIntoAlbums.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert album!");
            }

            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album!");
            }
        }
    }

    public void insertSong(String title, String artist, String album, int track) {
        try {
//            Starting a transaction
            conn.setAutoCommit(false);

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);

            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);

            int affectedRows = insertIntoSongs.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("Couldn't insert song!");
            }
        } catch (Exception e) {
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Rollback failed: " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behaviour");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }
}
