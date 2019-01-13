import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Statistics {

    private double[] marks;
    private JTextField[] marksField;
    private JLabel resultLabel;

    public Statistics()
    {
        marks = new double[4];
        marksField = new JTextField[4];
        marksField[0] = new JTextField(10);
        marksField[1] = new JTextField(10);
        marksField[2] = new JTextField(10);
        marksField[3] = new JTextField(10);
    }

    private void displayGUI()
    {
        int selection = JOptionPane.showConfirmDialog(
                null, getPanel(), "Input Form : "
                                , JOptionPane.OK_CANCEL_OPTION
                                , JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION) 
        {
        	double tp = Double.valueOf(marksField[0].getText());
        	double fp = Double.valueOf(marksField[1].getText());
        	double fn = Double.valueOf(marksField[2].getText());
        	double tn = Double.valueOf(marksField[3].getText());

            double recall = (tp) * 100.0 / (tp+fn);
            double specificity = (tn) * 100.0 / (tn+fp);
            double precision = (tp) * 100.0 / (tp+fp);
            double npv = (tn) * 100.0 / (tn+fn);
            double acc = (tp + tn) * 100.0 / (tp+tn+fp+fn);
            double f1cat = (2*tp) * 100.0 / (2*tp+fp+fn);
            double f1noncat = (2*tn) * 100.0 / (2*tn+fn+fp);
            // double 
            JOptionPane.showMessageDialog(null
                    , "Sensitivity/Recall/True Positive Rate (TPR): " + Double.toString(recall) + "%\n"
                    + "Specificity/Selectivity/True Negative Rate (TNR): " + Double.toString(specificity) + "%\n"
                    + "Precision/Positve Predictive Value (PPV): " + Double.toString(precision) + "%\n"
                    + "Negative Predictive Value (NPV): " + Double.toString(npv) + "%\n"
                    + "Miss Rate/False Negative Rate (FNR): " + Double.toString(100-recall) + "%\n"
                    + "Fall-out/False Postive Rate (FPR): " + Double.toString(100-specificity) + "%\n"
                    + "Accuracy (ACC): " + Double.toString(acc) + "%\n"
                    + "F1-Score (Cat) - Harmonic mean of Precision/PPV & Sensitivity/Recall: " + Double.toString(f1cat) + "%\n"
                    + "F1-Score (Non-Cat): " + Double.toString(f1noncat) + "%\n"
                    + "\n--------------- data -------------------------------\n"
                    + "TP (Hit): " + Double.toString(tp) + "\n"
                    + "TN (Correct rejection): " + Double.toString(tn) + "\n"
                    + "FP (False alarm/Type I error): " + Double.toString(fp) + "\n"
                    + "FN (Miss/Type II error): " + Double.toString(fn) + "\n"
                    , "Metrics : "
                    , JOptionPane.PLAIN_MESSAGE);
        }
        else if (selection == JOptionPane.CANCEL_OPTION)
        {
            System.exit(0);
        }
    }

    private JPanel getPanel()
    {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 3, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.setOpaque(true);
        centerPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Confusion Matrix");
        JLabel mLabel1 = new JLabel("Predicted Class: Cat");
        JLabel mLabel2 = new JLabel("Predicted Class: Non-Cat");
        JLabel mLabel3 = new JLabel("Actual Class: Cat");
        JLabel mLabel4 = new JLabel("Actual Class: Non-Cat");
        String textboxLabels[] = {"True positives", "False positives", "False negatives", "True negatives"};

        for (int i=0; i < 4; i++){

        final int index = i;
        marksField[i].setText(textboxLabels[i]);
		marksField[i].addFocusListener(new FocusAdapter() {
		    public void focusGained(FocusEvent e) {
		        JTextField source = (JTextField)e.getComponent();
		        source.setText("");
		        source.removeFocusListener(this);
		    }
		});
		marksField[i].addFocusListener(new FocusAdapter() {
		    public void focusLost(FocusEvent e) {
		        JTextField source = (JTextField)e.getComponent();
		        if(source.getText().isEmpty())
		        	source.setText(textboxLabels[index]);
		        source.removeFocusListener(this);
		    }
		});
    	}
        centerPanel.add(titleLabel);
        centerPanel.add(mLabel3);
        centerPanel.add(mLabel4);
        centerPanel.add(mLabel1);
        centerPanel.add(marksField[0]);
        centerPanel.add(marksField[1]);
        centerPanel.add(mLabel2);
        centerPanel.add(marksField[2]);
        centerPanel.add(marksField[3]);

        return centerPanel;
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Statistics().displayGUI();
            }
        });
    }

}
