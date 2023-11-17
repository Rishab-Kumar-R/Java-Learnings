package com.rishab.nestedClass;

import java.util.ArrayList;
import java.util.LinkedList;

// Demonstration of inner class (non-static nested class)
public class Album {
    private String name;
    private String artist;
    private SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public boolean addSong(String songTitle, double duration) {
        return this.songs.add(new Song(songTitle, duration));
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        Song checkedSong = this.songs.findSong(trackNumber);
        if (checkedSong != null) {
            playList.add(checkedSong);
            System.out.println("Added " + checkedSong.getTitle() + " to playlist");
            return true;
        } else {
            System.out.println("This album does not have a track " + trackNumber);
            return false;
        }
    }

    public boolean addToPlayList(String songTitle, LinkedList<Song> playList) {
        Song checkedSong = this.songs.findSong(songTitle);
        if (checkedSong != null) {
            playList.add(checkedSong);
            System.out.println("Added " + checkedSong.getTitle() + " to playlist");
            return true;
        } else {
            System.out.println("The song " + songTitle + " is not in this album");
            return false;
        }
    }

    public static class SongList {
        private ArrayList<Song> songs;

        public SongList() {
            this.songs = new ArrayList<Song>();
        }

        private boolean add(Song song) {
            if (findSong(song.getTitle()) == null) {
                this.songs.add(song);
                return true;
            }
            return false;
        }

        private Song findSong(String songTitle) {
            for (Song song : this.songs) {
                if (song.getTitle().equals(songTitle)) {
                    return song;
                }
            }
            return null;
        }

        private Song findSong(int trackNumber) {
            int index = trackNumber - 1;
            if (index >= 0 && index < this.songs.size()) {
                return this.songs.get(index);
            }
            return null;
        }
    }
}
