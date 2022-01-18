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
public class SparseMatrixF1 {

    private Node head;

    /**
     * 
     * @param rows
     * @param columns 
     */
    public SparseMatrixF1(int rows, int columns) {
        this.head = createNewNode(rows, columns);
    }

    /**
     * 
     * @return 
     */
    public Node getHead() {
        return head;
    }

    /**
     * 
     * @param head 
     */
    public void setHead(Node head) {
        this.head = head;
    }

    private Node createNewNode(int rows, int columns) {
        int length = rows > columns ? rows : columns;
        Node start = new Node(rows, columns, 0), latest;

        start.setNextRow(start);
        start.setNextColumn(start);

        latest = start;

        for (int i = 0; i < length; i++) {
            Node newNode = new Node(i, i, 0);

            newNode.setNextRow(newNode);
            newNode.setNextColumn(newNode);

            latest.setNext(newNode);
            latest = newNode;
        }
        latest.setNext(start);

        return start;
    }

    /**
     * 
     * @return 
     */
    public String show() {
        Node start = this.head.getNext(), temp;
        String string = "";

        while (start != this.head) {
            temp = start.getNextRow();

            while (start != temp) {
                string += temp.getRow() + "    " + temp.getColumn() + "    " + temp.getValue() + "\n";
                temp = temp.getNextRow();
            }
            start = start.getNext();
        }

        System.out.println("Sparse Matrix = " + string);
        return string;
    }

    /**
     *
     * @param row the row.
     * @param column the column.
     * @return
     */
    public Node findPosition(int row, int column) {
        Node start = this.head.getNext(), temp, result = null;

        while (start.getRow() < row) {
            start = start.getNext();
        }

        temp = start.getNextRow();

        while (temp != start && temp.getColumn() < column) {
            temp = temp.getNextRow();
        }

        if (start != temp && temp.getRow() == row && temp.getColumn() == column) {
            result = temp;
        }

        return result;
    }

    /**
     * 
     * @param row
     * @param column
     * @param value 
     */
    public void storeData(int row, int column, float value) {
        Node start = this.head.getNext(), temp, previousRow, previousColumn;

        while (start != this.head && start.getRow() < row) {
            start = start.getNext();
        }

        previousRow = start;
        temp = start.getNextRow();

        while (temp != start && start.getColumn() < column) {
            previousRow = temp;
            temp = temp.getNextRow();
        }

        start = this.head.getNext();

        while (start != this.head && start.getColumn() < column) {
            start = start.getNext();
        }

        previousColumn = start;
        temp = start.getNextColumn();

        while (temp != start && start.getRow() < row) {
            previousColumn = temp;
            temp = temp.getNextColumn();
        }

        if (temp != start && temp.getRow() == row && temp.getColumn() == column) {
            System.out.println("");
        } else {
            Node newNode = new Node(row, column, value);

            newNode.setNextRow(previousRow.getNextRow());
            previousRow.setNextRow(newNode);

            newNode.setNextColumn(previousColumn.getNextColumn());
            previousColumn.setNextColumn(newNode);
        }
    }

    /**
     * 
     * @param row
     * @param column
     * @param value 
     */
    public void insertData(int row, int column, float value) {
        Node start = this.head.getNext(), temp, previousRow, previousColumn;

        while (start != this.head && start.getRow() < row) {
            start = start.getNext();
        }

        previousRow = start;
        temp = start.getNextRow();

        while (temp != start && start.getColumn() < column) {
            previousRow = temp;
            temp = temp.getNextRow();
        }

        start = this.head.getNext();

        while (start != this.head && start.getColumn() < column) {
            start = start.getNext();
        }

        previousColumn = start;
        temp = start.getNextColumn();

        while (temp != start && temp.getRow() < row) {
            previousColumn = temp;
            temp = temp.getNextColumn();
        }

        if (temp != start && temp.getRow() == row && temp.getColumn() == column) {
            float sum = temp.getValue() + value;

            if (sum != 0) {
                temp.setValue(sum);
            } else {
                previousRow.setNextRow(temp.getNextRow());
                previousColumn.setNextColumn(temp.getNextColumn());
            }
        } else {
            Node newNode = new Node(row, column, value);

            newNode.setNextRow(previousRow.getNextRow());
            previousRow.setNextRow(newNode);

            newNode.setNextColumn(previousColumn.getNextColumn());
            previousColumn.setNextColumn(newNode);
        }
    }

    /**
     * 
     * @param node 
     */
    public void bindRow(Node node) {
        Node start = this.head.getNext(), temp, previous;

        while (start.getRow() < node.getRow()) {
            start = start.getNext();
        }

        temp = start.getNextRow();
        previous = start;

        while (temp != start && temp.getColumn() < node.getColumn()) {
            previous = temp;
            temp = temp.getNextRow();
        }

        if (temp != start && temp.getColumn() > node.getColumn()) {
            previous.setNextRow(node);
            node.setNextRow(temp);
        }
    }

    /**
     * 
     * @param node 
     */
    public void bindColumn(Node node) {
        Node start = this.head.getNext(), temp, previous;

        while (start.getColumn() < node.getColumn()) {
            start = start.getNext();
        }

        temp = start.getNextColumn();
        previous = start;

        while (temp != start && temp.getRow() < node.getRow()) {
            previous = temp;
            temp = temp.getNextColumn();
        }

        if (temp != start && temp.getRow() > node.getRow()) {
            previous.setNextColumn(node);
            node.setNextColumn(temp);
        }
    }

    /**
     * 
     * @param node 
     */
    public void unbindRow(Node node) {
        Node start = node.getNextRow(), previousRow = node.getNextRow();

        while (start != node) {
            previousRow = node;
            start = start.getNextRow();
        }

        previousRow.setNextRow(node.getNextRow());
    }

    /**
     * 
     * @param node 
     */
    public void unbindColumn(Node node) {
        Node start = node.getNextRow(), previousColumn = node.getNextColumn();

        while (start != node) {
            previousColumn = node;
            start = start.getNextColumn();
        }

        previousColumn.setNextColumn(node.getNextColumn());
    }

    /**
     * 
     */
    public void rowWithGreatestValue() {
        Node start = this.head.getNext(), temp;
        float greatValue = 0;

        while (start != this.head) {
            if (start.getRow() <= this.head.getRow()) {
                temp = start.getNextRow();

                if (start == temp) {
                    System.out.println("The row is empty: " + start.getRow());
                } else {
                    temp = start.getNextRow();
                    greatValue = temp.getValue();

                    while (temp != start) {
                        if (temp.getValue() > greatValue) {
                            greatValue = temp.getValue();
                        }

                        temp = start.getNextRow();
                    }
                    System.out.println("The greatest data is in the row " + start.getRow() + " and has a value " + greatValue);
                }
            }
            start = start.getNext();
        }
    }
}

/**
 * The {@code Node} class represents a node for linked lists.
 *
 * @author ev
 * @author drestrepod
 */
class Node {

    private int row, column; // row and column of this node
    private float value; // value of this node
    private Node next, nextRow, nextColumn; // next node head (main, row, column)

    /**
     * Initializes a new node.
     *
     * @param row the row.
     * @param column the column.
     * @param value the value.
     * @throws IllegalArgumentException if {@code row} is zero or negative.
     * @throws IllegalArgumentException if {@code column} is zero or negative.
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
        this.next = null;
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
     * Returns the next node head of this node.
     *
     * @return the next node head of this node.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next node head of this node.
     *
     * @param next the next node head.
     */
    public void setNext(Node next) {
        this.next = next;
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
