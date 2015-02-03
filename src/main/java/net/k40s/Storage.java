package net.k40s;

import com.google.common.collect.Lists;

import net.k40s.album.Album;
import net.k40s.single.Song;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


// Enter your initials down there.

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class Storage {

    static String dbUsername = "example";
    static String dbPassword = "ki2jh74";
    static String dbName = "music";


    static String audioPath = "/var/audio/"; //THIS IS SO DAMN IMPORTANT!!!
    static String relativeAudioPath = "/media"; //Mountpoint
    static String version = "1.7"; //TODO Change version number according to pom.xml
    

    static List<Album> albums = Arrays.asList(
            
    );

    public static List<Song> getSingles() {
        List<Song> singles = new LinkedList<Song>();
        DatabaseHandler dbhandler = new DatabaseHandler();
        ResultSet rs = null;
        try {
            rs = dbhandler.executeQuery("SELECT * FROM songs;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Fetch each row from the result set
        try {
            while (rs.next()) {
                int songID = rs.getInt("songid");
                String songName = rs.getString("songname");
                String songDesc = rs.getString("songdescr");
                String songimag = rs.getString("songimage");
                String songRele = rs.getString("songrelease");
                String songFile = rs.getString("songfile");

                singles.add(new Song(songID, songName, songDesc, songimag, songRele, songFile));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return singles;
    }

    public static List<Song> getReversedSingles() {
        List<Song> singles = new LinkedList<Song>();
        DatabaseHandler dbhandler = new DatabaseHandler();
        ResultSet rs = null;
        try {
            rs = dbhandler.executeQuery("SELECT * FROM songs;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Fetch each row from the result set
        try {
            while (rs.next()) {
                int songID = rs.getInt("songid");
                String songName = rs.getString("songname");
                String songDesc = rs.getString("songdescr");
                String songimag = rs.getString("songimage");
                String songRele = rs.getString("songrelease");
                String songFile = rs.getString("songfile");

                singles.add(new Song(songID, songName, songDesc, songimag, songRele, songFile));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Lists.reverse(singles);
    }

    public static List<Album> getAlbums() {

        return albums;
    }

    public static List<Song> getSongsOfAlbum(int albumID) {
        Album album = albums.get(albumID - 1);
        return album.getSongs();
    }

    public static List<Album> getReversedAlbums() {
        return Lists.reverse(albums);

    }

    public static String getAudioPath() {

        return audioPath;
    }

    public static String getVersion() {
        return version;
    }

    public static String getRelativeAudioPath() {

        return relativeAudioPath + "/";
    }

    public static String getDbUsername() {
        return dbUsername;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    public static String getDbName() {
        return dbName;
    }
}



/*
            new Album(1, "Spacewalk", "Imagine a walk across endless lines of stars. And you are here, walking among them.", "spacewalk.jpg", "22 June 2014", 1.50, true, Arrays.asList(
                    singles.get(0), // Pioneer
                    singles.get(1), // Hashes
                    singles.get(2), // Struu
                    singles.get(3), // Rose
                    singles.get(4)  // Shining
            )),
            new Album(2, "Best Of 2014", "My favourite tracks from 2014. Looking forward to the next year.", "bestof14.jpg", "27 December 2014", 0.00, true, Arrays.asList(
                    singles.get(0), // Pioneer
                    singles.get(2), // Struu
                    singles.get(5), // Shining
                    singles.get(9), // Green Kingdom
                    singles.get(10), // Alliens
                    singles.get(12), // Dr0p
                    singles.get(14) // Center Of The Universe
            ))
 */