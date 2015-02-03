package net.k40s.debug.dbtest;

import net.k40s.DatabaseHandler;
import net.k40s.HomePage;
import net.k40s.MusicSite;
import net.k40s.Storage;
import net.k40s.album.AlbumsPage;
import net.k40s.debug.DBTestSite;
import net.k40s.debug.ProgressBarPanel;
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
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
