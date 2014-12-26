package net.k40s.single;

import net.k40s.ContactPage;
import net.k40s.HomePage;
import net.k40s.Storage;
import net.k40s.album.AlbumsPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.wicketstuff.html5.media.MediaSource;
import org.wicketstuff.html5.media.audio.Html5Audio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class SingleProductPage extends WebPage implements Serializable {
  Song songToUse;
  public SingleProductPage(PageParameters parameters) {
    
    super(parameters);

    if(parameters.get("id").isNull()) {
      setResponsePage(SinglesPage.class);
    } else {

      for(Song song : Storage.getSingles()) {
        if(song.getId() == parameters.get("id").toInt()) {
          songToUse = song;
          break;
        }
      }
      add(new Label("name", songToUse.getName()));
      add(new Label("description", songToUse.getDescription()));
      add(new Image("image", new ContextRelativeResource("/single_images/" + songToUse.getImage())));

    }

    final List mm = new ArrayList();
    mm.add(new MediaSource("/audio/" + songToUse.getAudio(), "audio/mp3"));

    IModel<List<MediaSource>> mediaSourceList = new AbstractReadOnlyModel<List<MediaSource>>() {
      public List getObject() {
        return mm;
      }
    };
    

    
    add(new Html5Audio("player", mediaSourceList){
      @Override
      protected boolean isControls() {
        return true;
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
    add(new Link("contactLink") {
      @Override
      public void onClick() {

        setResponsePage(ContactPage.class);
      }
    });
  }
}
