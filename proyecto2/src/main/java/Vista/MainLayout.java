package Vista.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "AulaVirtual", shortName = "AulaVirtual", enableInstallPrompt = false)
@Push
@Theme(themeFolder = "myapp")
@PageTitle("")
public class MainLayout extends AppLayout {

    public MainLayout() {

    }
}
