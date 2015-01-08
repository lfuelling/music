package net.k40s;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.SharedResourceReference;

import java.io.File;

public class WicketApplication extends WebApplication {
        // TODO Integrate Peer5 for downloads.
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

    super.init();

    // add your configuration here
    FolderContentResource mediaFolder = new FolderContentResource(new org.apache.wicket.util.file.File(new File(Storage.getAudioPath())));
    getSharedResources().add("mediaFolder", mediaFolder);

    mountResource(Storage.relativeAudioPath, new SharedResourceReference("mediaFolder"));
  }
}
