package net.k40s.backend;

import net.k40s.HomePage;
import net.k40s.MusicSite;
import net.k40s.Storage;
import net.k40s.album.AlbumsPage;
import net.k40s.debug.DBTestSite;
import net.k40s.single.SinglesPage;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;


/**
 * Created by k40s on 2/4/15.
 */
public class BackendPage extends WebPage {
    @Override
    protected void onConfigure() {
        super.onConfigure();
        AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
        //if user is not signed in, redirect him to sign in page
        if(!AuthenticatedWebSession.get().isSignedIn())
            app.restartResponseAtSignInPage();
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
        add(new Link("logOut") {

            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }
        });
    }
}