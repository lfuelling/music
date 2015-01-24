package net.k40s.album;

import net.k40s.HomePage;
import net.k40s.single.SinglesPage;
import net.k40s.Storage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class AlbumsPage extends WebPage implements Serializable{
  public AlbumsPage() {

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

    add(new ListView<Album>("albums", Storage.getReversedAlbums()) { // getReversedSingles for correct placement on the website.
      @Override
      protected void populateItem(ListItem<Album> item) {
        final Album album = item.getModelObject();
        Link link = new Link("link") {
          @Override
          public void onClick() {
            PageParameters params = new PageParameters();
            params.add("id", album.getId());
            setResponsePage(AlbumProductPage.class, params);
          }
        };
        link.add(new Label("name", album.getName()));
        link.add(new Image("image", new ContextRelativeResource("/album_images/" + album.getImage())));
        item.add(link);
      }
    });
    add(new Label("version", Storage.getVersion()));

  }
}
