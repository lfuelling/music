package net.k40s;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class HeaderPanel extends Panel implements Serializable {
  public HeaderPanel(String id) {
    super(id);
    
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
