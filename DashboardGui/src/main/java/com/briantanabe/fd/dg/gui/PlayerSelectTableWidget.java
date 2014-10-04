package com.briantanabe.fd.dg.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Created by Brian on 10/3/2014.
 */
public class PlayerSelectTableWidget extends Composite {
    private Text playerSearchTextBox;

    public PlayerSelectTableWidget(Composite parent, int style){
        super(parent, style);

        createWidget();
        configureWidget();
        addListeners();
    }

    private void createWidget(){
        playerSearchTextBox = new Text(this, SWT.BORDER);
    }

    private void configureWidget(){

        // Configure playerSearchTextBox:
        FormData playerSearchTextBoxFormData = new FormData();
        playerSearchTextBoxFormData.right = new FormAttachment(100, -7);
        playerSearchTextBoxFormData.bottom = new FormAttachment(100, -7);
        playerSearchTextBoxFormData.width = 200;
        playerSearchTextBoxFormData.height = 20;
        playerSearchTextBox.setLayoutData(playerSearchTextBoxFormData);
    }

    private void addListeners(){

    }
}
