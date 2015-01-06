package net.k40s.album;

import net.k40s.FileUtils;
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
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;

import java.io.*;
import java.util.zip.ZipOutputStream;

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
      add(new ExternalLink("donateButton", "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=5Y8QNG65M2892"));
      add(new Label("name2", albumToUse.getName()));
      add(new Label("name3", albumToUse.getName()));

      IModel<File> zipFileModel = new AbstractReadOnlyModel<File>() {
        public File getObject() {

          File zfm = null;
          try {
            zfm = generateFile();
          } catch(IOException e) {
            e.printStackTrace();
          }
          return zfm;
        }
      };

      String filename = albumToUse.getName().replaceAll(" ", "_") + ".zip";
      DownloadLink dllink = new DownloadLink("dllink", zipFileModel, filename);
      dllink.setDeleteAfterDownload(true);
      add(dllink);
      
    }
  }

  private File generateFile() throws IOException {

    File tempFile = File.createTempFile("foo", "zip");
    tempFile.deleteOnExit();

    FileOutputStream fos = new FileOutputStream(tempFile);
    ZipOutputStream zos = new ZipOutputStream(fos);
    for(Song song : albumToUse.getSongs()) {
      FileUtils.addToZipFile(Storage.getAudioPath() + song.getAudio(), zos);
    }
    zos.close();
    
    fos.flush();
    fos.close();
    
    return tempFile;
  }
}
