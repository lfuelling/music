package net.k40s;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class Song {
  int id;
  String name;
  String description;
  String image;
  double price;
  boolean payWhatYouWant;


  /**
   * This defines a Song. If you set PayWhatYouWant to true the price will be the starting point for this.
   * @param id Song ID
   * @param name Song Name
   * @param description Song Description
   * @param image Song Image (filename only. has to be in <pre>webapp/album_images</pre>)
   * @param price Song Price
   * @param payWhatYouWant Is Song Pay What You Want?
   */
  public Song(int id, String name, String description, String image, double price, boolean payWhatYouWant) {

    this.id = id;
    this.name = name;
    this.description = description;
    this.image = image;
    this.price = price;
    this.payWhatYouWant = payWhatYouWant;
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

  public double getPrice() {

    return price;
  }

  public boolean isPayWhatYouWant() {

    return payWhatYouWant;
  }
}
