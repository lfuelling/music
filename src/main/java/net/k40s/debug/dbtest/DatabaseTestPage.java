package net.k40s.debug.dbtest;

import net.k40s.DatabaseHandler;
import net.k40s.HomePage;
import net.k40s.MusicSite;
import net.k40s.Storage;
import net.k40s.album.AlbumsPage;
import net.k40s.debug.DBTestSite;
import net.k40s.debug.ProgressBarPanel;
import net.k40s.single.SingleProductPage;
import net.k40s.single.SinglesPage;
import net.k40s.single.Song;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.util.time.Duration;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lukas F&uuml;lling (lukas@k40s.net)
 */
public class DatabaseTestPage extends WebPage implements Serializable {
    Logger logger = LogManager.getLogger(DatabaseTestPage.class.getName());
    final ProgressBarPanel progressPanel = new ProgressBarPanel("progressPanel", 0);

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

        ///////////////////////////////////////////////////////////////////////////

        inputSongModal();
        inputAlbumModal();
        addSinglesListView();
        add(progressPanel);
        add(new Link("initDBBtn"){
            @Override
            public void onClick() {
                initDB();
                progressPanel.setProgressLevel(100);
            }
        });

    }

    
    public void inputSongModal() {
        
        final TextField<String> songName = new TextField<String>("inputSongName",
                Model.of(""));
        songName.setRequired(true);
        final TextField<String> songDescription = new TextField<String>("inputSongDescription",
                Model.of(""));
        songDescription.setRequired(true);
        final TextField<String> songImage = new TextField<String>("inputSongImage",
                Model.of(""));
        songImage.setRequired(true);
        final TextField<String> songDate = new TextField<String>("inputSongDate",
                Model.of(""));
        songDate.setRequired(true);
        final TextField<String> songFilename = new TextField<String>("inputSongFilename",
                Model.of(""));
        songFilename.setRequired(true);

        Form<?> songForm = new Form<Void>("inputSongForm") {

            @Override
            protected void onSubmit() {
                DatabaseHandler dbhandler = new DatabaseHandler();
                final String songNameValue = songName.getModelObject();
                final String songDescValue = songDescription.getModelObject();
                final String songImagValue = songImage.getModelObject();
                final String songDateValue = songDate.getModelObject();
                final String songFileValue = songFilename.getModelObject();

                try {
                    dbhandler.executeStatement("INSERT INTO songs (songname,songdescr,songimage,songrelease,songfile) VALUES ('"+ songNameValue +"','"+ songDescValue +"','"+ songImagValue +"','"+ songDateValue +"','"+ songFileValue + "');");
                } catch (Exception e) {
                    logger.warn(e);
                }
                progressPanel.setProgressLevel(100);
            }

        };

        add(songForm);
        songForm.add(songName);
        songForm.add(songDescription);
        songForm.add(songImage);
        songForm.add(songDate);
        songForm.add(songFilename);
    }
    
    public void initDB(){
        List<Song> songList = Storage.getSingles();
        int i = 0;
        progressPanel.setProgressLevel(i);
        for(Song song : songList){
            DatabaseHandler dbhandler = new DatabaseHandler();
            final String songIDValue = Integer.toString(song.getId());
            final String songNameValue = song.getName();
            final String songDescValue = song.getDescription();
            final String songImagValue = song.getImage();
            final String songDateValue = song.getReleaseDate();
            final String songFileValue = song.getAudio();
            i++;
            i++;
            try {
                dbhandler.executeStatement("INSERT INTO songs (songid,songname,songdescr,songimage,songrelease,songfile) VALUES ('" + songIDValue + "','" + songNameValue +"','"+ songDescValue +"','"+ songImagValue +"','"+ songDateValue +"','"+ songFileValue + "');");
            } catch (Exception e) {
                logger.warn(e);
            }
            progressPanel.setProgressLevel(i);
        }
        progressPanel.setProgressLevel(100);
    }
    
    public void inputAlbumModal(){
        // INSERT INTO albums VALUES (1, "benis", "foo", "bar", "01.01.0001");
        // INSERT INTO albums_has_songs VALUES (1, 1);
        // INSERT INTO albums_has_songs VALUES (1, 2);

        final TextField<String> albumName = new TextField<String>("inputAlbumName",
                Model.of(""));
        albumName.setRequired(true);
        final TextField<String> albumDescription = new TextField<String>("inputAlbumDescription",
                Model.of(""));
        albumDescription.setRequired(true);
        final TextField<String> albumImage = new TextField<String>("inputAlbumImage",
                Model.of(""));
        albumImage.setRequired(true);
        final TextField<String> albumDate = new TextField<String>("inputAlbumDate",
                Model.of(""));
        albumDate.setRequired(true);
        final TextField<String> albumSongs = new TextField<String>("inputAlbumSongs",
                Model.of(""));
        albumSongs.setRequired(true);

        Form<?> albumForm = new Form<Void>("inputAlbumForm") {

            @Override
            protected void onSubmit() {
                DatabaseHandler dbhandler = new DatabaseHandler();
                final String albumNameValue = albumName.getModelObject();
                final String albumDescValue = albumDescription.getModelObject();
                final String albumImagValue = albumImage.getModelObject();
                final String albumDateValue = albumDate.getModelObject();
                final String albumSongValue = albumSongs.getModelObject();
                
                final String[] albumSongsArray = albumSongValue.split(",");

                try {
                    dbhandler.executeStatement("INSERT INTO albums (albumname,albumdescr,albumimage,albumrelease) VALUES ('"+ albumNameValue +"','"+ albumDescValue +"','"+ albumImagValue +"','"+ albumDateValue +"');");
                } catch (Exception e) {
                    logger.warn(e);
                }
                int albumid = 0;
                try {
                    ResultSet rs = dbhandler.executeQuery("SELECT * FROM albums WHERE albumname = '" + albumNameValue + "';");
                    rs.first(); // There shall be no second
                    albumid = rs.getInt("albumid");
                } catch (Exception e) {
                    logger.warn(e);
                }
                
                

                for(String songID : albumSongsArray){
                    try {
                        dbhandler.executeStatement("INSERT INTO albums_has_songs VALUES ('" + albumid + "','" + songID + "');");
                    } catch (SQLException e) {
                        logger.warn(e);
                    } catch (ClassNotFoundException e) {
                        logger.warn(e);
                    }
                }
                
                progressPanel.setProgressLevel(100);
            }

        };

        add(albumForm);
        albumForm.add(albumName);
        albumForm.add(albumDescription);
        albumForm.add(albumImage);
        albumForm.add(albumDate);
        albumForm.add(albumSongs);
    }
    
    public void addSinglesListView(){
        add(new ListView<Song>("singlesTable", Storage.getSingles()) {
            @Override
            protected void populateItem(ListItem<Song> item) {
                final Song song = item.getModelObject();
                item.add(new Label("songid", Integer.toString(song.getId())));
                item.add(new Label("songname", song.getName()));
                item.add(new Label("songdesc", song.getDescription()));
                item.add(new Label("songimag", song.getImage()));
                item.add(new Label("songdate", song.getReleaseDate()));
                item.add(new Label("songfile", song.getAudio()));
            }
        });
    }
}
