package net.sf.anathema.lib.workflow.container.factory;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.container.TitledPanel;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.SwingTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.AreaTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class MigPanelBuilder {
  private static final int COLUMN_COUNT = 45;
  private final JPanel panel;

  public MigPanelBuilder() {
    this(2);
  }

  public MigPanelBuilder(int elementsPerRow) {
    panel = new JPanel(new MigLayout(new LC().wrapAfter(elementsPerRow).fill().insets("2")));
  }

  public ITextView addLineTextView(String labelName) {
    return addLabelledTextView(labelName, new LineTextView(COLUMN_COUNT));
  }

  public ITextView addSpanningLineTextView(String label, int columnCount) {
    LineTextView textView = new LineTextView(columnCount);
    LabelTextView labelTextView = new LabelTextView(label, textView);
    labelTextView.addToMigPanelSpanning(panel);
    return textView;
  }

  public ITextView addAreaTextView(String labelName, int rowCount) {
    return addLabelledTextView(labelName, new AreaTextView(rowCount, COLUMN_COUNT));
  }

  public void addComponent(JComponent component, CC constraint) {
    panel.add(component, constraint);
  }

  public void addView(AdditiveView view, CC data) {
    view.addTo(panel, data);
  }

  public JComponent getUntitledContent() {
    return panel;
  }

  public JComponent getTitledContent(String title) {
    return new TitledPanel(title, panel);
  }

  private ITextView addLabelledTextView(String labelText, SwingTextView textView) {
    LabelTextView labelTextView = new LabelTextView(labelText, textView);
    labelTextView.addToMigPanel(panel);
    return textView;
  }
}