package net.k40s;

import net.k40s.album.AlbumProductPage;
import net.k40s.album.AlbumsPage;
import net.k40s.debug.DBTestSite;
import net.k40s.debug.dbtest.DatabaseTestPage;
import net.k40s.single.SingleProductPage;
import net.k40s.single.SinglesPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.SharedResourceReference;

import java.io.File;

public class MusicSite extends WebApplication {
	private static boolean debug = true;
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
	if(getConfigurationType().equals(RuntimeConfigurationType.DEVELOPMENT)){
    	mountPage("/debug", DBTestSite.class);
        mountPage("/dbtest", DatabaseTestPage.class);
    	setDebug(true);
        logger.info("Running in Debug mode");
    } else {
    	setDebug(false);
      logger.trace("Running in Deployment mode");
    }
    logger.info("Music Page Deployed Successfully. Populating Lists...");
    // add your configuration here
    mountPage("/albums", AlbumsPage.class);
    mountPage("/singles", SinglesPage.class);
    mountPage("/album", AlbumProductPage.class);
    mountPage("/single", SingleProductPage.class);
    
    FolderContentResource mediaFolder = new FolderContentResource(new org.apache.wicket.util.file.File(new File(Storage.getAudioPath())));
    getSharedResources().add("mediaFolder", mediaFolder);

    mountResource(Storage.relativeAudioPath, new SharedResourceReference("mediaFolder"));
  }


  public static boolean isDebug() {
	return debug;
}



public void setDebug(boolean debug) {
	this.debug = debug;
}
}
