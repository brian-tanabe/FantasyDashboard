package com.briantanabe.fd.dg.gui;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Created by Brian on 10/3/2014.
 */
public class FantasyTeamDashWidget extends Composite {

    public FantasyTeamDashWidget(Composite parent, int style) {
        super(parent, style);

        createWidget();
        configureWidget();
        addListeners();
    }

    private void createWidget(){

    }

    private void configureWidget(){
        setBackground(new Color(Display.getDefault(), 40, 0, 0));
    }

    private void addListeners(){

    }
}
