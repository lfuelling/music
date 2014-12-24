package net.k40s;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new HeaderPanel("headerPanel"));

		// TODO Add your page's components here

    }
}
