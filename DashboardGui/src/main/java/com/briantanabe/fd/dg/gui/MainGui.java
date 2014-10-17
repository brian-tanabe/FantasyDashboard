package com.briantanabe.fd.dg.gui;

import com.briantanabe.fd.ap.events.AllPlayerIdsEvent;
import com.briantanabe.fd.ap.events.DatabaseUpdateCompleteEvent;
import com.briantanabe.fd.ap.events.RequestAllPlayerIdsEvent;
import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Brian on 10/3/2014.
 */
public class MainGui extends Observable implements Observer {
    private Shell shell;
    private Display display;
    private Composite parent;

    private SashForm sashForm;
    private PlayerDashWidget playerDashWidget;
    private FantasyTeamDashWidget fantasyTeamDashWidget;

    public void open() {
        configureGui();
        addListeners();
        populateGui();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }

        display.dispose();
    }

    public void createGui() {
        display = Display.getDefault();
        shell = new Shell(display, SWT.SHELL_TRIM | SWT.DIALOG_TRIM);
        parent = new Composite(shell, SWT.NONE);

        sashForm = new SashForm(parent, SWT.HORIZONTAL);
        playerDashWidget = new PlayerDashWidget(sashForm, SWT.NONE);
        fantasyTeamDashWidget = new FantasyTeamDashWidget(sashForm, SWT.NONE);
    }

    private void configureGui() {
        // Configure shell:
        shell.setText("Fantasy Dashboard");
        shell.setSize(1024, 600);
        shell.setMinimumSize(1024, 600);
        shell.setLayout(new FillLayout());

        // Configure display:
        Display.setAppName("Fantasy Dashboard");

        // Configure parent:
        FormData parentFormData = new FormData();
        parentFormData.left = new FormAttachment(0, 0);
        parentFormData.right = new FormAttachment(100, 0);
        parentFormData.top = new FormAttachment(0, 0);
        parentFormData.bottom = new FormAttachment(100, 0);
        parent.setLayoutData(parentFormData);
        parent.setLayout(new FillLayout());

        // Configure sashForm:
        sashForm.setSashWidth(7);

        // Configure playerDashWidget:
        FormData playerDashWidgetFormData = new FormData();
        playerDashWidgetFormData.left = new FormAttachment(0, 0);
        playerDashWidgetFormData.right = new FormAttachment(100, 0);
        playerDashWidgetFormData.top = new FormAttachment(0, 0);
        playerDashWidgetFormData.bottom = new FormAttachment(100, 0);
        playerDashWidget.setLayoutData(playerDashWidgetFormData);
        playerDashWidget.setLayout(new FillLayout());

        // Configure fantasyTeamDashWidget:
        FormData fantasyTeamDashWidgetFormData = new FormData();
        fantasyTeamDashWidgetFormData.left = new FormAttachment(0, 0);
        fantasyTeamDashWidgetFormData.right = new FormAttachment(100, 0);
        fantasyTeamDashWidgetFormData.top = new FormAttachment(0, 0);
        fantasyTeamDashWidgetFormData.bottom = new FormAttachment(100, 0);
        fantasyTeamDashWidget.setLayoutData(fantasyTeamDashWidgetFormData);
        fantasyTeamDashWidget.setLayout(new FillLayout());
    }

    private void addListeners() {
        shell.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                System.exit(0);
            }
        });
    }

    private void populateGui() {
        setChanged();
        notifyObservers(new RequestAllPlayerIdsEvent());
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof DatabaseUpdateCompleteEvent) {
            populateGui();
        } else if (o instanceof AllPlayerIdsEvent) {
            addPlayersToPlayerDashWidgetPlayersTable(((AllPlayerIdsEvent) o).getAllNflPlayerIds());
        }
    }

    private void addPlayersToPlayerDashWidgetPlayersTable(List<NflPlayer> allPlayers) {
        playerDashWidget.addPlayersToPlayerSelectTable(allPlayers);
    }
}
