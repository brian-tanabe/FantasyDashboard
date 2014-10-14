package com.briantanabe.fd.dg.gui;

import com.briantanabe.fd.dp.fantasy.player.NflPlayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ExpandAdapter;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

import java.util.List;

/**
 * Created by Brian on 10/3/2014.
 */
public class PlayerSelectTableWidget extends Composite {
    private ExpandBar filterExpandBar;
    private ExpandItem filterBarExpandItem;
    private Composite filterBarComposite;
    private Button quarterbackPositionFilterButton;
    private Button runningBackPositionFilterButton;

    private Table playerSearchTable;

    private StyledText playerSearchTextBox;

    public PlayerSelectTableWidget(Composite parent, int style){
        super(parent, style);

        createWidget();
        configureWidget();
        addListeners();
        configureTable();
    }

    private void createWidget(){
        playerSearchTextBox = new StyledText(this, SWT.BORDER | SWT.SINGLE);
        filterExpandBar = new ExpandBar(this, SWT.V_SCROLL);
        filterBarExpandItem = new ExpandItem(filterExpandBar, SWT.NONE);
        filterBarComposite = new Composite(filterExpandBar, SWT.NONE);
        quarterbackPositionFilterButton = new Button(filterBarComposite, SWT.CHECK);
        runningBackPositionFilterButton = new Button(filterBarComposite, SWT.CHECK);
        playerSearchTable = new Table(this, SWT.BORDER_SOLID | SWT.FULL_SELECTION);
    }

    private void configureWidget(){
        setLayout(new FormLayout());

        // Configure filterExpandBar:
        FormData filterExpandBarFormData = new FormData();
        filterExpandBarFormData.right = new FormAttachment(playerSearchTextBox, -7);
        filterExpandBarFormData.top = new FormAttachment(0, 7);
        filterExpandBarFormData.left = new FormAttachment(0, 7);
        filterExpandBar.setLayoutData(filterExpandBarFormData);

        // Configure filterBarComposite:
        filterBarComposite.setLayout(new FormLayout());

        // Configure quarterbackPositionFilterButton:
        FormData quarterbackPositionFilterButtonFormData = new FormData();
        quarterbackPositionFilterButtonFormData.top = new FormAttachment(0, 7);
        quarterbackPositionFilterButtonFormData.left = new FormAttachment(0, 7);
        quarterbackPositionFilterButton.setLayoutData(quarterbackPositionFilterButtonFormData);
        quarterbackPositionFilterButton.setText("QB");

        // Configure runningBackPositionFilterButton:
        FormData runningBackPositionFilterButtonFormData = new FormData();
        runningBackPositionFilterButtonFormData.top = new FormAttachment(quarterbackPositionFilterButton, 7);
        runningBackPositionFilterButtonFormData.left = new FormAttachment(0, 7);
        runningBackPositionFilterButton.setLayoutData(runningBackPositionFilterButtonFormData);
        runningBackPositionFilterButton.setText("RB");

        // Configure filterBarExpandItem:
        filterBarExpandItem.setText("Player Filters");
        filterBarExpandItem.setControl(filterBarComposite);
        filterBarExpandItem.setHeight(filterBarComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        filterBarExpandItem.setExpanded(true);

        // Configure playerSearchTextBox:
        FormData playerSearchTextBoxFormData = new FormData();
        playerSearchTextBoxFormData.right = new FormAttachment(100, -7);
        playerSearchTextBoxFormData.top = new FormAttachment(0, 7);
        playerSearchTextBoxFormData.width = 150;
        playerSearchTextBoxFormData.height = 20;
        playerSearchTextBox.setLayoutData(playerSearchTextBoxFormData);

        // Configure playerSearchTable:
        FormData playerSearchTableFormData = new FormData();
        playerSearchTableFormData.right = new FormAttachment(100, -7);
        playerSearchTableFormData.top = new FormAttachment(playerSearchTextBox, 7);
        playerSearchTableFormData.bottom = new FormAttachment(100, -7);
        playerSearchTableFormData.left = new FormAttachment(0, 7);
        playerSearchTable.setLayoutData(playerSearchTableFormData);
        playerSearchTable.setHeaderVisible(true);
        playerSearchTable.setLinesVisible(true);
    }

    private void addListeners(){
        filterExpandBar.addExpandListener(new ExpandAdapter() {

            @Override
            public void itemCollapsed(ExpandEvent e) {
                int height = filterBarComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
                filterBarExpandItem.setHeight(0);
                System.out.println("collapse: " + height);
                super.itemCollapsed(e);
                layout();
            }

            @Override
            public void itemExpanded(ExpandEvent e) {
                int filterBarCompositeHeight = filterBarComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
                int expandItemHeight = filterBarExpandItem.getHeight();
                int filterBarHeight = filterExpandBar.getBounds().y;
                filterBarExpandItem.setHeight(filterBarCompositeHeight);
                System.out.println("expand filterBarCompositeHeight: " + filterBarCompositeHeight + " expandItemHeight: " + expandItemHeight + " filterBarHeight: " + filterBarHeight);
                super.itemExpanded(e);
                layout();
            }
        });
//        filterExpandBar.addListener(SWT.Resize, new Listener() {
//
//            @Override
//            public void handleEvent(Event event) {
//                int height = filterBarComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//
//                System.out.println("filterBarCompositeHeight=[" + height + "]");
//                filterBarExpandItem.setHeight(filterBarComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
//            }
//        });
    }

    private void configureTable(){
        TableColumn nameColumn = new TableColumn(playerSearchTable, SWT.NULL);
        nameColumn.setText("Name");

        TableColumn espnIdColumn = new TableColumn(playerSearchTable, SWT.NULL);
        espnIdColumn.setText("ESPN ID");

        TableColumn nfIdColumn = new TableColumn(playerSearchTable, SWT.NULL);
        nfIdColumn.setText("nF ID");
    }

    public void addPlayersToTable(final List<NflPlayer> allPlayers){
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                for(NflPlayer player : allPlayers) {
                    TableItem playerRow = new TableItem(playerSearchTable, SWT.NULL);
                    playerRow.setText(0, player.getName());
                    playerRow.setText(1, Integer.toString(player.getEspnPlayerId()));
                    playerRow.setText(2, Integer.toString(player.getNumberFireId()));
                }

                for(int columnIndex = 0; columnIndex < playerSearchTable.getColumnCount(); columnIndex++){
                    playerSearchTable.getColumn(columnIndex).pack();
                }
            }
        });
    }
}
