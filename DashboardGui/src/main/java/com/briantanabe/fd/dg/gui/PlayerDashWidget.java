package com.briantanabe.fd.dg.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by Brian on 10/3/2014.
 */
public class PlayerDashWidget extends Composite {
    private SashForm sashForm;
    private PlayerSelectTableWidget playerSelectTable;
    private PlayerInfoWidget playerInfo;

    public PlayerDashWidget(Composite parent, int style) {
        super(parent, style);

        createWidget();
        configureWidget();
        addListeners();
    }

    private void createWidget(){
        sashForm = new SashForm(this, SWT.NONE);

        playerSelectTable = new PlayerSelectTableWidget(sashForm, SWT.BORDER);
        playerInfo = new PlayerInfoWidget(sashForm, SWT.BORDER);
    }

    private void configureWidget(){
        sashForm.setOrientation(SWT.VERTICAL);
        setLayout(new FillLayout());

        // Configure playerSelectTable:
        FormData playerSelectTableFormData = new FormData();
        playerSelectTableFormData.left = new FormAttachment(0, 0);
        playerSelectTableFormData.right = new FormAttachment(100, 0);
        playerSelectTableFormData.top = new FormAttachment(100, 0);
        playerSelectTableFormData.bottom = new FormAttachment(100, 0);
        playerSelectTable.setLayoutData(playerSelectTableFormData);
//        setBackground(new Color(Display.getDefault(), 80, 80, 80));
    }

    private void addListeners(){

    }
}
