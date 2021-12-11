/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparsematricesprogram;

/**
 *
 * @author ev
 * @author drestrepom
 */
public class SparseMatrixF2 {

    private Node head; // head of list

    /**
     * 
     * @param rows
     * @param columns 
     */
    public SparseMatrixF2(int rows, int columns) {
        this.head = new Node(rows, columns, 0);
        this.head.setNextRow(this.head);
        this.head.setNextColumn(this.head);
    }
    
    public void show() {
        Node startRow = this.head.getNextRow();
        Node startColumn = this.head.getNextColumn();
        
        while (startRow != this.head) {
            System.out.println("    " + startRow.getNextRow());
        }
    }
    
    public void insertNode(int row, int column, float value) {
        Node startRow = this.head.getNextRow(), previousRow = this.head, previousColumn = this.head;
        
        while (startRow != this.head) {
            
        }
    }
}

/**
 * The {@code Node} class represents a node for linked lists.
 *
 * @author ev
 */
class Node {

    private int row, column; // row and column of this node
    private float value; // value of this node
    private Node nextRow, nextColumn; // next row and column node head

    /**
     * Initializes a new node.
     *
     * @param row the row.
     * @param column the column.
     * @param value the value.
     * @throws IllegalArgumentException if {@code row} is zero or negative.
     * @throws IllegalArgumentException if {@code exponent} is zero or negative.
     */
    public Node(int row, int column, float value) {
        if (row <= 0) {
            throw new IllegalArgumentException("coefficient cannot be zero or negative" + row);
        }
        if (column <= 0) {
            throw new IllegalArgumentException("column cannot be zero or negative: " + column);
        }
        this.row = row;
        this.column = column;
        this.value = value;
        this.nextRow = null;
        this.nextColumn = null;
    }

    /**
     * Returns the row of this node
     *
     * @return the row of this node.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row of this node.
     *
     * @param row the row.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Returns the column of this node
     *
     * @return the column of this node.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Sets the column of this node.
     *
     * @param column the column.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Returns the value of this node
     *
     * @return the value of this node.
     */
    public float getValue() {
        return value;
    }

    /**
     * Sets the value of this node.
     *
     * @param value the value.
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Returns the next row node head of this node.
     *
     * @return the next row node head of this node.
     */
    public Node getNextRow() {
        return nextRow;
    }

    /**
     * Sets the next row node head of this node.
     *
     * @param nextRow the next row node head.
     */
    public void setNextRow(Node nextRow) {
        this.nextRow = nextRow;
    }

    /**
     * Returns the next column node head of this node.
     *
     * @return the next column node head of this node.
     */
    public Node getNextColumn() {
        return nextColumn;
    }

    /**
     * Sets the next column node head of this node.
     *
     * @param nextColumn the next column node head.
     */
    public void setNextColumn(Node nextColumn) {
        this.nextColumn = nextColumn;
    }
}
