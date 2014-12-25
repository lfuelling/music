package net.k40s.album;

import net.k40s.ContactPage;
import net.k40s.HomePage;
import net.k40s.single.SinglesPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class AlbumProductPage extends WebPage implements Serializable {
  public AlbumProductPage() {
    add(new Link("homeLink") {
      @Override
      public void onClick() {
        setResponsePage(HomePage.class);
      }
    });
    add(new Link("singlesLink") {
      @Override
      public void onClick() {
        setResponsePage(SinglesPage.class);
      }
    });
    add(new Link("albumsLink") {
      @Override
      public void onClick() {
        setResponsePage(AlbumsPage.class);
      }
    });
    add(new Link("contactLink") {
      @Override
      public void onClick() {
        setResponsePage(ContactPage.class);
      }
    });
  }
}
