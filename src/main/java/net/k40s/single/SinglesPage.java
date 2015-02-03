package net.k40s.single;


import com.google.common.collect.Lists;
import net.k40s.DatabaseHandler;
import net.k40s.HomePage;
import net.k40s.MusicSite;
import net.k40s.Storage;
import net.k40s.album.AlbumsPage;
import net.k40s.debug.DBTestSite;

import org.apache.wicket.markup.html.WebMarkupContainer;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


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
        link.add(new Label("name", song.getName()));
        link.add(new Image("image", new ContextRelativeResource("/single_images/" + song.getImage())));
        item.add(link);
      }
    });
    add(new Label("version", Storage.getVersion()));
    WebMarkupContainer debugListItem = new WebMarkupContainer("debugLi");
	debugListItem.add(new Link("debugLink"){
		@Override
		public void onClick(){
			setResponsePage(DBTestSite.class);
		}
	});
	if(MusicSite.isDebug()){
		add(debugListItem);
	} else {
		debugListItem.setVisible(false);
		add(debugListItem);
	}
  }
}
