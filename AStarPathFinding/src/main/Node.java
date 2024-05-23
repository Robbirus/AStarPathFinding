package main;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    private Node parent;
    private int col;
    private int row;
    private int gCost;
    private int hCost;
    private int fCost;
    private boolean start;
    private boolean goal;
    private boolean solid;
    private boolean open;
    private boolean checked;


    public Node(int col, int row){

        this.col = col;
        this.row = row;

        setBackground(Color.black);
        setBackground(Color.white);
        addActionListener(this);
    }

    public void setAsStart(){
        setBackground(Color.blue);
        setForeground(Color.white);
        setText("Start");
        this.start = true;
    }

    public void setAsGoal(){
        setBackground(Color.yellow);
        setForeground(Color.black);
        setText("Goal");
        this.goal = true;
    }

    public void setAsSolid(){
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        this.solid = true;
    }

    public void setAsOpen(){
        this.open = true;
    }

    public void setAsChecked(){
        if(!this.start && !this.goal){
            setBackground(Color.orange);
            setForeground(Color.BLACK);

        }
        this.checked = true;
    }

    public void setAsPath(){
        setBackground(Color.GREEN);
        setForeground(Color.BLACK);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        setBackground(Color.orange);

    }

    public int getCol(){
        return this.col;
    }

    public int getRow(){
        return this.row;
    }

    public int getGCost(){
        return this.gCost;
    }

    public int getFCost(){
        return this.fCost;
    }

    public int getHCost(){
        return this.hCost;
    }

    public void setGCost(int gCost){

        this.gCost = gCost;
    }

    public void setFCost(int fCost){

        this.fCost = fCost;
    }

    public void setHCost(int hCost){

        this.hCost = hCost;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isChecked() {
        return checked;
    }

    public Node getParentNode() {
        return parent;
    }

    public void setParentNode(Node parent) {
        this.parent = parent;
    }
}
