package net.k40s.backend;

import net.k40s.HomePage;
import net.k40s.album.AlbumsPage;
import net.k40s.single.SinglesPage;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

/**
 * Created by k40s on 2/4/15.
 */
public class SignInPage extends WebPage {
    private String username;
    private String password;
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
        StatelessForm form = new StatelessForm("signInForm"){
            @Override
            protected void onSubmit() {
                if(Strings.isEmpty(username)) {
                    return;
                }

                boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
                if(authResult) {
                    setResponsePage(BackendPage.class);
                }
            }
        };

        form.setDefaultModel(new CompoundPropertyModel(this));

        form.add(new TextField("username"));
        form.add(new PasswordTextField("password"));

        add(form);
    }
}
