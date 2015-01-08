package net.k40s;

import com.google.common.collect.Lists;
import net.k40s.album.Album;
import net.k40s.single.Song;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class Storage {

  static String audioPath = "/var/audio/"; //THIS IS SO DAMN IMPORTANT!!!
  static String relativeAudioPath = "/media"; //Mountpoint

  
  static List<Song> singles = Arrays.asList(
      new Song(1, "Pioneer", "My first Song. I don't know why, but I think it has the best intro of all songs.", "pioneer.jpg", "19 June 2014", "pioneer.mp3", 0.00, true),
      new Song(2, "Hashes", "", "hashes.jpg", "20 June 2014", "hashes.mp3", 0.00, true),
      new Song(3, "Struu", "Dedicated to a friend", "struu.jpg", "21 June 2014", "struu.mp3", 0.75, true),
      new Song(4, "Rose", "Bad Wolf", "rose.jpg", "22 June 2014", "rose.mp3", 0.75, true),
      new Song(5, "Shining", "Bonus track from the Spacewalk album.", "noimage.png", "22 June 2014", "shining.mp3", 0.00, true),
      new Song(6, "Supernova", "", "supernova.jpg", "26 June 2014", "supernova.mp3", 1.00, true),
      new Song(7, "Ezekiel", "", "ezekiel.jpg", "08 August 2014", "ezekiel.mp3", 0.00, true),
      new Song(8, "Dawn Of The Ganja Queen", "", "ganjaqueen.jpg", "18 August 2014", "ganjaqueen.mp3", 0.00, true),
      new Song(9, "Green Kingdom", "", "kingdom.jpg", "21 August 2014", "kingdom.mp3", 0.00, true),
      new Song(10, "Alliens", "Got it's name because of things...", "alliens.jpg", "21 September 2014", "alliens.mp3", 0.00, true),
      new Song(11, "Mouth", "", "mouth.jpg", "30 October 2014", "mouth.mp3", 0.00, true),
      new Song(12, "Dr0p", "It's really just one drop. More like a little experiment than an actual song.", "noimage.png", "04 November 2014", "dr0p.mp3", 0.00, true),
      new Song(13, "Hotfix", "", "noimage.png", "21 November 2014", "hotfix.mp3", 0.00, true),
      new Song(14, "Center Of The Universe", "End of the space-series.", "cotu.jpg", "30 November 2014", "cotu.mp3", 0.00, true),
      new Song(15, "Jamsession (feat. Dernerl)", "Sorry for the bad quality. It's been recorded using my Thinkpad W510.", "jamsession.jpg", "20 December 2014", "jamsession.mp3", 0.00, true)
  );

  static List<Album> albums = Arrays.asList(
      new Album(1, "Spacewalk", "Imagine a walk across endless lines of stars. And you're here, walking among them.", "spacewalk.jpg", "22 June 2014", 1.50, true, Arrays.asList(
          singles.get(0), // Pioneer
          singles.get(1), // Hashes
          singles.get(2), // Struu
          singles.get(3), // Rose
          singles.get(4)  // Shining
      )),
      new Album(2, "Best Of 2014", "My favourite tracks from 2014. Looking forward for next year.", "bestof14.jpg", "27 December 2014", 0.00, true, Arrays.asList(
          singles.get(0),
          singles.get(2),
          singles.get(5),
          singles.get(9),
          singles.get(10),
          singles.get(12),
          singles.get(14)
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

  public static String getRelativeAudioPath() {

    return relativeAudioPath + "/";
  }
}
