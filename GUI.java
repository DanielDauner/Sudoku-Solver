import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

public class GUI {

    private Sudoku sudoku;
    private int dimension = 9;

    private JPanel gridPanel;
    private JTextField[][] grid;

    private JPanel buttonPanel;
    private JButton solveButton;
    private JButton clearButton;

    public GUI() {

        JFrame frame = new JFrame("Sudoku-Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setResizable(false);

        this.gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(dimension, dimension));
        this.grid = new JTextField[dimension][dimension];

        int thinLine = 1;
        int thiccLine = 3;
        
        Font fieldFont= new Font("SansSerif", Font.BOLD, 20);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                
                JTextField field = new JTextField(2);
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(fieldFont);
                
                int bottom = thinLine;
                int right = thinLine;
                if(i < dimension-1 && i % 3 == 2){
                    bottom = thiccLine;
                }
                if(j < dimension-1 && j % 3 == 2){
                    right = thiccLine;
                }
                field.setBorder(BorderFactory.createMatteBorder(
                        thinLine, thinLine, bottom, right, Color.BLACK));
                grid[i][j] = field;
                gridPanel.add(grid[i][j]);
            }
        }

        buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");
        buttonPanel.add(clearButton);
        buttonPanel.add(solveButton);

        frame.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        frame.getContentPane().add(BorderLayout.CENTER, gridPanel);
        frame.setVisible(true);

        clearButton.addActionListener((ActionEvent e) -> {
            this.clear();
        });

        solveButton.addActionListener((ActionEvent e) -> {
            this.solve();

        });
    }

    private void solve() {
        int[][] gridUnsolved = new int[this.dimension][this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                try {
                    String text = this.grid[i][j].getText();
                    gridUnsolved[i][j] = Integer.parseInt(text);
                }catch(Exception e) {
                    gridUnsolved[i][j] = 0;
                }
            }
        }
        sudoku = new Sudoku(gridUnsolved);

        int[][] gridSolved = sudoku.backtracking();
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.grid[i][j].setText(""+gridSolved[i][j]);
            }
        }
    }

    private void clear() {
        for (JTextField[] row : grid) {
            for (JTextField field : row) {
                field.setText("");
            }
        }
    }

    public static void main(String args[]) {
        GUI main = new GUI();
    }

}
