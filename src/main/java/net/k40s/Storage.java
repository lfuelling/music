package net.k40s;

import com.google.common.collect.Lists;

import net.k40s.album.Album;
import net.k40s.single.Song;

import java.util.Arrays;
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
  static String version = "1.6"; //TODO Change version number according to pom.xml

  

  static List<Song> singles = Arrays.asList(
          new Song(1, "Pioneer", "My first Song. I dunno why, but I think it has the best intro of all songs.", "pioneer.jpg", "19 June 2014", "pioneer.mp3"),
          new Song(2, "Hashes", "", "hashes.jpg", "20 June 2014", "hashes.mp3"),
          new Song(3, "Struu", "Dedicated to a friend", "struu.jpg", "21 June 2014", "struu.mp3"),
          new Song(4, "Rose", "Bad Wolf", "rose.jpg", "22 June 2014", "rose.mp3"),
          new Song(5, "Shining", "Bonus track from the Spacewalk album.", "noimage.png", "22 June 2014", "shining.mp3"),
          new Song(6, "Supernova", "", "supernova.jpg", "26 June 2014", "supernova.mp3"),
          new Song(7, "Ezekiel", "", "ezekiel.jpg", "08 August 2014", "ezekiel.mp3"),
          new Song(8, "Dawn Of The Ganja Queen", "", "ganjaqueen.jpg", "18 August 2014", "ganjaqueen.mp3"),
          new Song(9, "Green Kingdom", "", "kingdom.jpg", "21 August 2014", "kingdom.mp3"),
          new Song(10, "Alliens", "Got its name because of things...", "alliens.jpg", "21 September 2014", "alliens.mp3"),
          new Song(11, "Mouth", "", "mouth.jpg", "30 October 2014", "mouth.mp3"),
          new Song(12, "Dr0p", "This is really just one drop. More like a little experiment than an actual song.", "noimage.png", "04 November 2014", "dr0p.mp3"),
          new Song(13, "Hotfix", "", "noimage.png", "21 November 2014", "hotfix.mp3"),
          new Song(14, "Center Of The Universe", "End of the space-series.", "cotu.jpg", "30 November 2014", "cotu.mp3"),
          new Song(15, "Jamsession (feat. Dernerl)", "Sorry for the bad quality. The song has been recorded using my Thinkpad W510.", "jamsession.jpg", "20 December 2014", "jamsession.mp3"),
          new Song(16, "Melody", "I found this beautiful thing in my house and started playing it it a bit.", "melody.jpg", "23 January 2015", "melody.mp3"),
          new Song(17, "Jamsession 2 (feat. Dernerl)", "Jamming again in even worse quality.", "jamsession2.png", "24 January 2015", "jamsession2.mp3")
  );

  static List<Album> albums = Arrays.asList(
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
  );

  public static List<Song> getSingles() {
    return singles;
  }
  
  public static List<Song> getReversedSingles(){
    return Lists.reverse(singles);
  }

  public static List<Album> getAlbums() {

    return albums;
  }
  
  public static List<Song> getSongsOfAlbum(int albumID){
    Album album = albums.get(albumID-1);
    return album.getSongs();
  } 
  
  public static List<Album> getReversedAlbums(){
    return Lists.reverse(albums);
    
  }

  public static String getAudioPath() {

    return audioPath;
  }
  
  public static String getVersion(){
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
