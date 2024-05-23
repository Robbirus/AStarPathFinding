package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

public class DemoPanel extends JPanel {

    // SCREEN SETTING
    private final int maxCol = 15;
    private final int maxRow = 10;
    private final int nodeSize = 70;
    private final int screenWidth = this.nodeSize * this.maxCol;
    private final int screenHeight = this.nodeSize * this.maxRow;

    // NODE
    private Node[][] node = new Node[this.maxCol][this.maxRow];
    private Node startNode;
    private Node goalNode;
    private Node currentNode;
    private ArrayList<Node> openList = new ArrayList<>();
    private ArrayList<Node> checkedList = new ArrayList<>();

    // OTHERS
    private boolean goalReached = false;
    private int step;

    public DemoPanel(){

        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(this.maxRow, this.maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        // PLACE NODES
        int col = 0;
        int row = 0;

        while(col < this.maxCol && row < this.maxRow){

            this.node[col][row] = new Node(col, row);
            this.add(this.node[col][row]);

            col++;
            if(col == this.maxCol){
                col = 0;
                row++;
            }

        }

        // SET START AND GOAL NODE
        setStartNode(3,6);
        setGoalNode(11,3);

        // PLACE SOLID NODES
        setSolidNode(10, 2);
        setSolidNode(10, 3);
        setSolidNode(10, 4);
        setSolidNode(10, 5);
        setSolidNode(10, 6);
        setSolidNode(10, 7);
        setSolidNode(6, 2);
        setSolidNode(7, 2);
        setSolidNode(8, 2);
        setSolidNode(9, 2);
        setSolidNode(11, 7);
        setSolidNode(12, 7);
        setSolidNode(6, 1);

        // SET COST
        setCostOnNodes();
    }

    private void setStartNode(int col, int row){
        this.node[col][row].setAsStart();
        this.startNode = this.node[col][row];
        this.currentNode = this.startNode;
    }

    private void setGoalNode(int col, int row){
        this.node[col][row].setAsGoal();
        this.goalNode = this.node[col][row];
    }

    private void setSolidNode(int col, int row){
        node[col][row].setAsSolid();
    }

    private void setCostOnNodes(){

        int col = 0;
        int row = 0;

        while(col < this.maxCol && row < this.maxRow){

            getCost(node[col][row]);
            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }
    }

    private void getCost(Node node){

        // GET G COST (The distance from the start node)
        int xDistance = Math.abs(node.getCol() - this.startNode.getCol());
        int yDistance = Math.abs(node.getRow() - this.startNode.getRow());
        node.setGCost(xDistance + yDistance);

        // GET H COST (The distance from the goal node)
        xDistance = Math.abs(node.getCol() - this.goalNode.getCol());
        yDistance = Math.abs(node.getRow() - this.goalNode.getRow());
        node.setHCost(xDistance + yDistance);

        // GET F COST (The total cost)
        node.setFCost(node.getGCost() + node.getHCost());

        // DISPLAY THE COST ON NODE
        if(node != this.startNode && node != this.goalNode){
            node.setText("<html>F:" + node.getFCost() + "<br>G:" + node.getGCost() + "</html>");
        }
    }

    public void search(){

        if(!this.goalReached){

            int col = this.currentNode.getCol();
            int row = this.currentNode.getRow();

            this.currentNode.setAsChecked();
            this.checkedList.add(this.currentNode);
            this.openList.remove(this.currentNode);

            // OPEN THE UP NODE
            if(row - 1 >= 0){
                this.openNode(this.node[col][row-1]);
            }
            // OPEN THE LEFT NODE
            if(col - 1 >= 0){
                this.openNode(this.node[col-1][row]);
            }
            // OPEN THE DOWN NODE
            if(row + 1 < this.maxRow){
                this.openNode(this.node[col][row+1]);
            }
            // OPEN THE RIGHT NODE
            if(col + 1 < this.maxCol){
                this.openNode(this.node[col+1][row]);

            }

            // FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for(int i = 0; i < this.openList.size(); i++){

                // Check if this node's F cost is better
                if(this.openList.get(i).getFCost() < bestNodeFCost){
                    bestNodeIndex = i;
                    bestNodeFCost = this.openList.get(i).getFCost();

                }
                // if F cost is equal, check the G cost
                else if(this.openList.get(i).getFCost() < this.openList.get(bestNodeIndex).getGCost()){
                    if(this.openList.get(i).getGCost() < this.openList.get(bestNodeIndex).getGCost()){
                        bestNodeIndex = i;
                    }
                }
            }
            // After the loop, we get the best node which is our next step
            this.currentNode = this.openList.get(bestNodeIndex);

            if(this.currentNode == this.goalNode){
                this.goalReached = true;
            }
        }
    }

    public void autoSearch(){

        while(!this.goalReached && this.step < 300){

            int col = this.currentNode.getCol();
            int row = this.currentNode.getRow();

            this.currentNode.setAsChecked();
            this.checkedList.add(this.currentNode);
            this.openList.remove(this.currentNode);

            // OPEN THE UP NODE
            if(row - 1 >= 0){
                this.openNode(this.node[col][row-1]);
            }
            // OPEN THE LEFT NODE
            if(col - 1 >= 0){
                this.openNode(this.node[col-1][row]);
            }
            // OPEN THE DOWN NODE
            if(row + 1 < this.maxRow){
                this.openNode(this.node[col][row+1]);
            }
            // OPEN THE RIGHT NODE
            if(col + 1 < this.maxCol){
                this.openNode(this.node[col+1][row]);

            }

            // FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for(int i = 0; i < this.openList.size(); i++){

                // Check if this node's F cost is better
                if(this.openList.get(i).getFCost() < bestNodeFCost){
                    bestNodeIndex = i;
                    bestNodeFCost = this.openList.get(i).getFCost();

                }
                // if F cost is equal, check the G cost
                else if(this.openList.get(i).getFCost() < this.openList.get(bestNodeIndex).getGCost()){
                    if(this.openList.get(i).getGCost() < this.openList.get(bestNodeIndex).getGCost()){
                        bestNodeIndex = i;
                    }
                }
            }
            // After the loop, we get the best node which is our next step
            this.currentNode = this.openList.get(bestNodeIndex);

            if(this.currentNode == this.goalNode){
                this.goalReached = true;
                trackThePath();
            }
        }
        this.step++;
        System.out.println(this.step);
    }

    private void openNode(Node node){

        if(!node.isOpen() && !node.isChecked() && !node.isSolid()){

            // If the node is not opened yet, add it to the open list
            node.setAsOpen();
            node.setParentNode(this.currentNode);
            this.openList.add(node);

        }
    }

    private void trackThePath(){

        // Backtrack and draw the best path
        Node current = this.goalNode;

        while(current != this.startNode){

            current = current.getParentNode();

            if(current != this.startNode){
                current.setAsPath();
            }

        }
    }
}
