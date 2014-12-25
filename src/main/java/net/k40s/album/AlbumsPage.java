package net.k40s.album;

import net.k40s.ContactPage;
import net.k40s.HomePage;
import net.k40s.single.SinglesPage;
import net.k40s.Storage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.resource.ContextRelativeResource;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class AlbumsPage extends WebPage implements Serializable{
  public AlbumsPage() {
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

    add(new ListView<Album>("albums", Storage.getReversedAlbums()) { // getReversedSingles for correct placement on the website.
      @Override
      protected void populateItem(ListItem<Album> item) {
        final Album album = (Album) item.getModelObject();
        item.add(new Image("image", new ContextRelativeResource("/album_images/" + album.getImage())));
        Link link = new Link("link") {
          @Override
          public void onClick() {
            setResponsePage(HomePage.class); //TODO
          }
        };
        link.add(new Label("name", album.getName()));
        item.add(link);
      }
    });
    
  }
}
