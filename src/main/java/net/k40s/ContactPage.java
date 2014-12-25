package net.k40s;

import org.apache.wicket.markup.html.WebPage;

import java.io.Serializable;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class ContactPage extends WebPage implements Serializable {
  public ContactPage() {
    add(new HeaderPanel("headerPanel"));
  }
}
