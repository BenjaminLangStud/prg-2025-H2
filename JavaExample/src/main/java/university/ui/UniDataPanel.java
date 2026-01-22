package university.ui;

import javax.swing.*;
import java.awt.*;

public abstract class UniDataPanel extends JPanel {
    protected JLabel titleLabel;
    protected JButton refreshButton;
    protected JTextArea detailArea;

    public UniDataPanel(String title) {
        setLayout(new BorderLayout());
        titleLabel = new JLabel(title);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        // top: title left, refresh button right
        JPanel top = new JPanel(new BorderLayout());
        top.add(titleLabel, BorderLayout.WEST);
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 4));
        refreshButton = new JButton("Refresh");
        right.add(refreshButton);
        top.add(right, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // detail area on right side will be created when panels call createMainWithDetails
        detailArea = new JTextArea();
        detailArea.setEditable(false);
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);
    }

    // Panels implement their own component creation
    protected abstract void initComponents();

    // Helper: build a horizontal split with main component on the left and details on the right
    protected JSplitPane createMainWithDetails(JComponent mainComponent) {
        JScrollPane mainScroll = (mainComponent instanceof JScrollPane) ? (JScrollPane) mainComponent : new JScrollPane(mainComponent);
        JScrollPane detailsScroll = new JScrollPane(detailArea);
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainScroll, detailsScroll);
        split.setResizeWeight(0.6);
        return split;
    }

    protected void showDetails(String text) {
        detailArea.setText(text == null ? "" : text);
    }
}
