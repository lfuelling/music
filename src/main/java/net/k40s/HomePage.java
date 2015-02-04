package net.k40s;

import net.k40s.album.AlbumsPage;
import net.k40s.single.SinglesPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		super(parameters);
    }

	@SuppressWarnings("rawtypes")
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
	}
}
