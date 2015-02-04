package net.k40s.album;

import java.io.Serializable;
import java.util.List;

import net.k40s.single.Song;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class Album implements Serializable{
  int id;
  String name;
  String description;
  String image;
  String releaseDate;
  List<Song> songs;

  /**
   * This defines an Album. If you set PayWhatYouWant to true the price will be the starting point for this.
   * @param id Album ID
   * @param name Album Name
   * @param description Album Description
   * @param image Album Image (filename only. has to be in <pre>webapp/album_images</pre>)
   * @param songs Songs that are in this album. (Note to self: use <pre>Arrays.asList(</pre>)
   */
  public Album(int id, String name, String description, String image, String releaseDate, List<Song> songs) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.image = image;
    this.releaseDate = releaseDate;
    this.songs = songs;
  }

  public int getId() {

    return id;
  }

  public String getName() {

    return name;
  }

  public String getDescription() {

    return description;
  }

  public String getImage() {

    return image;
  }

  public String getReleaseDate() {

    return releaseDate;
  }

  public List<Song> getSongs() {

    return songs;
  }
}
