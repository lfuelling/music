package net.k40s;

import org.apache.wicket.markup.html.WebPage;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class SinglesPage extends WebPage implements Serializable {
  public SinglesPage() {
    add(new HeaderPanel("headerPanel"));

  }
}
