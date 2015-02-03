package net.k40s.debug;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.time.Duration;

/**
 * @author Lukas F&uuml;ling (lukas@k40s.net)
 */
public class ProgressBarPanel extends Panel {
    private int progressLevel;
    final WebMarkupContainer progressBar = new WebMarkupContainer("progressBar");

    public ProgressBarPanel(String id, int initialLevel) {
        super(id);
        if(initialLevel > 100){
            this.progressLevel = 100;
        } else if (initialLevel < 0) {
            this.progressLevel = 0;
        } else {
            this.progressLevel = initialLevel;
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        progressBar.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(1)));
        add(progressBar);
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        progressBar.add(new AttributeModifier("style", "width: " + Integer.toString(progressLevel) + "%"));
    }

    public void setProgressLevel(int level) {
        if(level > 100){
            this.progressLevel = 100;
        } else if (level < 0) {
            this.progressLevel = 0;
        } else {
            this.progressLevel = level;
        }
    }
}
