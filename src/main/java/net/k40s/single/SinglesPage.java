package net.k40s.single;


import java.io.Serializable;

import net.k40s.HomePage;
import net.k40s.Storage;
import net.k40s.album.AlbumsPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;


/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class SinglesPage extends WebPage implements Serializable {
  public SinglesPage() {


  }

  @Override
  protected void onInitialize() {
    super.onInitialize();
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
    add(new ExternalLink("contactLink", "http://k40s.net"));

    add(new ListView<Song>("singles", Storage.getReversedSingles()) { // reversed -> New songs up.
      @Override
      protected void populateItem(ListItem<Song> item) {
        final Song song = item.getModelObject();
        Link link = new Link("link") {
          @Override
          public void onClick() {
            PageParameters params = new PageParameters();
            params.add("id", song.getId());
            setResponsePage(SingleProductPage.class, params);
          }
        };
        Link link2 = new Link("link2") {
          @Override
          public void onClick() {
            PageParameters params = new PageParameters();
            params.add("id", song.getId());
            setResponsePage(SingleProductPage.class, params);
          }
        };
        link2.add(new Label("name", song.getName()));
        link.add(new Image("image", new ContextRelativeResource("/single_images/" + song.getImage())));
        item.add(link);
        item.add(link2);
      }
    });
    add(new Label("version", Storage.getVersion()));
    
  }
}
