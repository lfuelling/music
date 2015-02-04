package net.k40s;

import java.io.File;

import net.k40s.album.AlbumProductPage;
import net.k40s.album.AlbumsPage;
import net.k40s.backend.BackendPage;
import net.k40s.backend.BackendSession;
import net.k40s.backend.SignInPage;
import net.k40s.single.SingleProductPage;
import net.k40s.single.SinglesPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.SharedResourceReference;

public class MusicSite extends AuthenticatedWebApplication {
  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  @Override
  public Class<? extends WebPage> getHomePage() {

    return HomePage.class;
  }

  /**
   * @see org.apache.wicket.Application#init()
   */
  @Override
  public void init() {
	Logger logger = LogManager.getLogger(MusicSite.class.getName());
    super.init();
    logger.info("Music Page Deployed Successfully. Populating Lists...");
    // add your configuration here
    mountPage("/albums", AlbumsPage.class);
    mountPage("/singles", SinglesPage.class);
    mountPage("/album", AlbumProductPage.class);
    mountPage("/single", SingleProductPage.class);
    mountPage("/auth", SignInPage.class);
    mountPage("/backend", BackendPage.class);
    
    FolderContentResource mediaFolder = new FolderContentResource(new org.apache.wicket.util.file.File(new File(Storage.getAudioPath())));
    getSharedResources().add("mediaFolder", mediaFolder);

    mountResource(Storage.relativeAudioPath, new SharedResourceReference("mediaFolder"));
  }

  @Override
  protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
    return BackendSession.class;
  }

  @Override
  protected Class<? extends WebPage> getSignInPageClass() {
    return SignInPage.class;
  }

}
