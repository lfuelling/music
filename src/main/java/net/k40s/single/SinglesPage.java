package net.k40s.single;

import net.k40s.ContactPage;
import net.k40s.HomePage;
import net.k40s.Storage;
import net.k40s.album.AlbumsPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;


import java.io.Serializable;


/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class SinglesPage extends WebPage implements Serializable {
  public SinglesPage() {
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

    add(new ListView<Song>("singles", Storage.getReversedSingles()) { // getReversedSingles for correct placement on the website.
      @Override
      protected void populateItem(ListItem<Song> item) {
        final Song song = (Song) item.getModelObject();
        Link link = new Link("link") {
          @Override
          public void onClick() {
            PageParameters params = new PageParameters();
            params.add("id", song.getId());
            setResponsePage(SingleProductPage.class);
          }
        };
        link.add(new Label("name", song.getName()));
        link.add(new Image("image", new ContextRelativeResource("/single_images/" + song.getImage())));
        item.add(link);
      }
    });

  }
}
