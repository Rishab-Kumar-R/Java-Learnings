package com.rishab;

import com.rishab.model.Artist;
import com.rishab.model.Datasource;
import com.rishab.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Datasource datasource = new Datasource();

        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_ASC);
        if (artists == null) {
            System.out.println("No artists!");
            return;
        }
        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        System.out.println("--------------------------------------------------");
        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Carole King", Datasource.ORDER_BY_NONE);
        for (String album : albumsForArtist) {
            System.out.println(album);
        }

        System.out.println("--------------------------------------------------");
        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if (songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
            return;
        }
        for (SongArtist artist : songArtists) {
            System.out.println("Artist name = " + artist.getArtistName() + ", " +
                "Album name = " + artist.getAlbumName() + ", " +
                "Track = " + artist.getTrack());
        }

        System.out.println("--------------------------------------------------");
        datasource.querySongsMetaData();

        System.out.println("--------------------------------------------------");
        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        System.out.println("--------------------------------------------------");
        boolean createViewForArtist = datasource.createViewForSongArtists();
        if (createViewForArtist) {
            System.out.println("View is created");
        } else {
            System.out.println("Couldn't create view");
        }

//        System.out.println("--------------------------------------------------");
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a song title: ");
//        String title = scanner.nextLine();
//
//        songArtists = datasource.querySongInfoView(title);
//        if (songArtists.isEmpty()) {
//            System.out.println("Couldn't find the artist for the song");
//            return;
//        }
//        for (SongArtist artist : songArtists) {
//            System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() + ", " +
//                "Album name = " + artist.getAlbumName() + ", " +
//                "Track number = " + artist.getTrack());
//        }

        System.out.println("--------------------------------------------------");
//        datasource.insertSong("Alone Again", "The Weeknd", "After Hours", 1);
//        datasource.insertSong("Attention", "Charlie Puth", "Voicenotes", 2);
        datasource.insertSong("Your Power", "Billie Eilish", "Happier Than Ever", 12);

        datasource.close();

    }
}
