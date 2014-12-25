package net.k40s;

import org.apache.wicket.markup.html.WebPage;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class AlbumsPage extends WebPage implements Serializable{
  public AlbumsPage() {
    add(new HeaderPanel("headerPanel"));

  }
}
