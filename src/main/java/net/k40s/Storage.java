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
  static List<Song> singles = Arrays.asList(
      new Song(1, "Pioneer", "My first Song", "pioneer.jpg", "19 June 2014", 0.00, true),
      new Song(2, "Hashes", "", "hashes.jpg", "20 June 2014", 0.00, true),
      new Song(3, "Struu", "Dedicated to a friend", "struu.jpg", "21 June 2014", 0.75, true),
      new Song(4, "Rose", "Bad Wolf", "rose.jpg", "22 June 2014", 0.75, true),
      new Song(5, "Shining", "Bonus track from the Spacewalk album.", "noimage.png", "22 June 2014", 0.00, true)
  );

  static List<Album> albums = Arrays.asList(
      new Album(1, "Spacewalk", "Imagine a walk across endless lines of stars. And your\'e here, walking among them.", "spacewalk.jpg", "22 June 2014", 1.50, true, Arrays.asList(
          singles.get(0), // Pioneer
          singles.get(1), // Hashes
          singles.get(2), // Struu
          singles.get(3), // Rose
          singles.get(4)  // Shining
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
}
