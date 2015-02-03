package net.k40s.debug;

import java.io.Serializable;

import net.k40s.HomePage;
import net.k40s.MusicSite;
import net.k40s.Storage;
import net.k40s.album.AlbumsPage;
import net.k40s.debug.dbtest.DatabaseTestPage;
import net.k40s.single.SinglesPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;

/**
 * Debug Test Page.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class DBTestSite extends WebPage implements Serializable {
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

        add(new Label("version", Storage.getVersion()));
        WebMarkupContainer debugListItem = new WebMarkupContainer("debugLi");
        debugListItem.add(new Link("debugLink") {
            @Override
            public void onClick() {
                setResponsePage(DBTestSite.class);
            }
        });
        if (MusicSite.isDebug()) {
            add(debugListItem);
        } else {
            debugListItem.setVisible(false);
            add(debugListItem);
        }
        add(new Link("dbDebugLink") {
            @Override
            public void onClick() {
                setResponsePage(DatabaseTestPage.class);
            }
        });

    }

}
