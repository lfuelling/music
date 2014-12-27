package net.k40s.album;

import net.k40s.HomePage;
import net.k40s.Storage;
import net.k40s.single.SingleProductPage;
import net.k40s.single.SinglesPage;
import net.k40s.single.Song;
import org.apache.wicket.markup.head.CssContentHeaderItem;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class AlbumProductPage extends WebPage implements Serializable {
  Album albumToUse;

  public AlbumProductPage(PageParameters parameters) {

    super(parameters);
    if(parameters.get("id").isNull()) {
      setResponsePage(AlbumsPage.class);
    } else {

      for(Album album : Storage.getAlbums()) {
        if(album.getId() == parameters.get("id").toInt()) {
          albumToUse = album;
          break;
        }
      }
      add(new Label("name", albumToUse.getName()));
      add(new Label("description", albumToUse.getDescription()));
      add(new Image("image", new ContextRelativeResource("/album_images/" + albumToUse.getImage())));

      add(new ListView<Song>("songs", Storage.getSongsOfAlbum(albumToUse.getId())) {
        @Override
        protected void populateItem(ListItem item) {

          final Song song = (Song) item.getModelObject();
          Link link = new Link("songLink") {
            @Override
            public void onClick() {

              PageParameters params = new PageParameters();
              params.add("id", song.getId());
              setResponsePage(SingleProductPage.class, params);
            }
          };
          link.add(new Label("songName", song.getName()));
          item.add(link);
        }
      });
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
    }
  }
}
