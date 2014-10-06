package com.briantanabe.fd.dg.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by Brian on 10/3/2014.
 */
public class MainGui {
    private Shell shell;
    private Display display;
    private Composite parent;

    private SashForm sashForm;
    private PlayerDashWidget playerDashWidget;
    private FantasyTeamDashWidget fantasyTeamDashWidget;

    public void open(){
        Thread guiThread = new Thread(new Runnable(){
            @Override
            public void run() {
                createGui();
                configureGui();
                addListeners();

                shell.open();
                shell.layout();
                while(!shell.isDisposed()){
                    if(!display.readAndDispatch())
                        display.sleep();
                }

                display.dispose();
            }
        });
        guiThread.start();
    }

    private void createGui(){
        display = Display.getDefault();
        shell = new Shell(display, SWT.SHELL_TRIM | SWT.DIALOG_TRIM);

        parent = new Composite(shell, SWT.NONE);

        sashForm = new SashForm(parent, SWT.HORIZONTAL);
        playerDashWidget = new PlayerDashWidget(sashForm, SWT.NONE);
        fantasyTeamDashWidget = new FantasyTeamDashWidget(sashForm, SWT.NONE);
    }

    private void configureGui(){
        // Configure shell:
        shell.setText("Fantasy Dashboard");
        shell.setSize(1024, 600);
        shell.setMinimumSize(1024, 600);
        shell.setLayout(new FillLayout());

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

    private void addListeners(){

    }
}