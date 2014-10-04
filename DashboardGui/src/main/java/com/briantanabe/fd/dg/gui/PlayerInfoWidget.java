package com.briantanabe.fd.dg.gui;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * Created by Brian on 10/3/2014.
 */
public class PlayerInfoWidget extends Composite {

    public PlayerInfoWidget(Composite parent, int style){
        super(parent, style);

        createWidget();
        configureWidget();
        addListeners();
    }

    private void createWidget(){

    }

    private void configureWidget(){
        setBackground(new Color(Display.getDefault(), 0, 0, 80));
    }

    private void addListeners(){

    }
}
