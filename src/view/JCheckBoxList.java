package view;
import java.awt.Component;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;

@SuppressWarnings("serial")
public class JCheckBoxList extends JList<JCheckBox> {
  protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
  private boolean ticked = false;

  public JCheckBoxList() {
    setCellRenderer(new CellRenderer());
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        int index = locationToIndex(e.getPoint());
        if (index != -1 && e.getX() < 15) {
          JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
          checkbox.setSelected(!checkbox.isSelected());
          
          ((DefaultListModel<JCheckBox>) getModel()).removeElementAt(index);
          if (checkbox.isSelected()) { // If task done
        	  // Cross out the task
        	  @SuppressWarnings("unchecked")
        	  Map<TextAttribute, Boolean> attributes = (Map<TextAttribute, Boolean>) getFont().getAttributes();
        	  attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        	  checkbox.setFont(new Font(attributes));
        	  
        	  ((DefaultListModel<JCheckBox>) getModel()).addElement(checkbox); // Put Task last
          }
          else {
        	  // Remove crossing out the task
        	  @SuppressWarnings("unchecked")
        	  Map<TextAttribute, Boolean> attributes = (Map<TextAttribute, Boolean>) getFont().getAttributes();
        	  attributes.remove(TextAttribute.STRIKETHROUGH);
        	  checkbox.setFont(new Font(attributes));
        	  
        	  ((DefaultListModel<JCheckBox>) getModel()).add(0,checkbox); // Put Task first
          }
          ticked = true;
          repaint();
        }
      }
    });
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }

  public JCheckBoxList(ListModel<JCheckBox> model){
    this();
    setModel(model);
  }

  protected class CellRenderer implements ListCellRenderer<JCheckBox> {
    public Component getListCellRendererComponent(
        JList<? extends JCheckBox> list, JCheckBox value, int index,
        boolean isSelected, boolean cellHasFocus) {
      JCheckBox checkbox = value;

      //Drawing checkbox, change the appearance here
      checkbox.setBackground(isSelected ? Utils.ACCENT_COLOR
          : getBackground());
      checkbox.setForeground(isSelected ? Utils.BACKGROUND_COLOR
          : getForeground());
      checkbox.setEnabled(isEnabled());
      checkbox.setFont(ticked && isSelected ? checkbox.getFont() : getFont());
      checkbox.setFocusPainted(false);
      checkbox.setBorderPainted(true);
      checkbox.setBorder(isSelected ? UIManager
          .getBorder("List.focusCellHighlightBorder") : noFocusBorder);
      return checkbox;
    }
  }
}