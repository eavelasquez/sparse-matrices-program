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

    /**
     *
     */
    public void show() {
        Node startRow = this.head.getNextRow();
        // Node startColumn = this.head.getNextColumn();

        while (startRow != this.head) {
            int column = startRow.getColumn();
            int row = startRow.getRow();
            Float value = startRow.getValue();
            String print = String.format("%s %s %f", row, column, value);
            System.out.println(print);
            startRow = startRow.getNextRow();
        }
    }

    /**
     * This method is used to store a data in the sparse matrix f2.
     *
     * @param row
     * @param column
     * @param value
     */
    public void storeData(int row, int column, float value) {
        Node start = this.head.getNextRow(), previousRow = this.head, previousColumn = this.head;

        while (start != this.head && start.getRow() < row) {
            previousRow = start;
            start = start.getNextRow();
        }

        while (start != this.head && start.getRow() <= row && start.getColumn() < column) {
            previousRow = start;
            start = start.getNextRow();
        }

        start = this.head.getNextColumn();

        while (start != this.head && start.getColumn() < column) {
            previousColumn = start;
            start = start.getNextColumn();
        }

        if (start != this.head && start.getRow() == row && start.getColumn() == column) {
            System.out.println("");
        } else {
            Node newNode = new Node(row, column, value);

            newNode.setNextRow(previousRow.getNextRow());
            previousRow.setNextRow(newNode);

            newNode.setNextColumn(start);
            previousColumn.setNextColumn(newNode);
        }
    }

    /**
     * This method is used to insert a data in the sparse matrix f2.
     *
     * @param row    the row to be inserted.
     * @param column the column to be inserted.
     * @param value  the value to be inserted.
     */
    public void insertNode(int row, int column, float value) {
        Node start = this.head.getNextRow(), previousRow = this.head, previousColumn = this.head;

        while (start != this.head && start.getRow() < row) {
            previousRow = start;
            start = start.getNextRow();
        }

        start = this.head.getNextColumn();

        while (start != this.head && start.getColumn() < column) {
            previousColumn = start;
            start = start.getNextColumn();
        }

        while (start != this.head && start.getRow() < row && start.getColumn() <= column) {
            previousColumn = start;
            start = start.getNextColumn();
        }

        if (start != this.head && start.getRow() == row && start.getColumn() == column) {
            float sum = start.getValue() + value;

            if (sum != 0) {
                start.setValue(sum);
            } else {
                previousRow.setNextRow(start.getNextRow());
                previousColumn.setNextColumn(start.getNextColumn());
            }
        } else {
            Node newNode = new Node(row, column, value);

            newNode.setNextRow(newNode);
            previousRow.setNextRow(newNode);

            newNode.setNextColumn(previousColumn.getNextColumn());
            previousColumn.setNextColumn(newNode);
        }
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public Node findPosition(int row, int column) {
        Node result = null, nextRow = this.head.getNextRow();

        while (nextRow != this.head && nextRow.getRow() < row) {
            nextRow = nextRow.getNextRow();
        }

        while (nextRow != this.head && nextRow.getRow() == row && nextRow.getColumn() < column) {
            nextRow = nextRow.getNextRow();
        }

        if (nextRow != this.head && nextRow.getRow() == row && nextRow.getColumn() == column) {
            result = nextRow;
        }

        return result;
    }

    /**
     *
     * @param node
     */
    public void insertRow(Node node) {
        Node startRow = this.head.getNextRow(), previous = null, temp;

        while (startRow != this.head && startRow.getRow() < node.getRow()) {
            previous = startRow;
            startRow = startRow.getNextRow();
        }

        while (startRow != this.head && startRow.getRow() == node.getRow() && startRow.getColumn() < node.getColumn()) {
            previous = startRow;
            startRow = startRow.getNextRow();
        }

        if (startRow == this.head || previous != null && startRow.getRow() != node.getRow()
                || startRow.getColumn() != node.getColumn() || startRow == this.head) {
            previous.setNextRow(node);
            node.setNextRow(startRow);
        }
    }

    /**
     *
     * @param node
     */
    public void insertColumn(Node node) {
        Node startColumn = this.head.getNextColumn(), previous = null, temp;

        while (startColumn != this.head && startColumn.getColumn() < node.getColumn()) {
            previous = startColumn;
            startColumn = startColumn.getNextColumn();
        }

        while (startColumn != this.head && startColumn.getColumn() == node.getColumn()
                && startColumn.getRow() < node.getRow()) {
            previous = startColumn;
            startColumn = startColumn.getNextColumn();
        }

        if (startColumn == this.head || previous != null && startColumn.getRow() != node.getRow()
                || startColumn.getColumn() != node.getColumn()) {
            previous.setNextColumn(node);
            node.setNextColumn(startColumn);
        }
    }

    /**
     *
     */
    public void columnWithGreatestValue() {
        Node startColumn = this.head.getNextColumn(), temp;
        int column = 0;
        float greatValue = 0;

        while (startColumn != this.head) {
            column = startColumn.getColumn();

            while (startColumn != this.head && startColumn.getColumn() == column) {
                if (startColumn.getValue() > greatValue) {
                    greatValue = startColumn.getValue();
                }

                startColumn = startColumn.getNextColumn();
            }

            System.out.println(column + "   " + greatValue);
        }
    }

    /**
     *
     * @param node
     */
    public void unbindRow(Node node) {
        Node startRow = node.getNextRow(), previousRow = node;

        while (startRow != node) {
            previousRow = startRow;
            startRow = startRow.getNextRow();
        }

        previousRow.setNextRow(node.getNextRow());
    }

    /**
     *
     * @param node
     */
    public void unbindColumn(Node node) {
        Node startColumn = node.getNextColumn(), previousColumn = node;

        while (startColumn != previousColumn) {
            previousColumn = startColumn;
            startColumn = startColumn.getNextColumn();
        }

        previousColumn.setNextColumn(node.getNextColumn());
    }

    /**
     *
     * @param row
     * @param column
     * @return
     */
    public boolean removeValue(int row, int column) {
        boolean result = false;
        Node temp = findPosition(row, column);

        if (temp != null) {
            result = true;
            unbindRow(temp);
            unbindColumn(temp);
        }

        return result;
    }

    public SparseMatrixF2 sum(SparseMatrixF2 matrixB) {
        if ((this.getHead().getRow() != matrixB.getHead().getRow())
                || this.getHead().getColumn() != matrixB.getHead().getColumn()) {
            return null;
        }
        SparseMatrixF2 result = new SparseMatrixF2(this.getHead().getRow(), this.getHead().getColumn());

        for (int row = 1; row <= matrixB.getHead().getRow(); row++) {
            for (int column = 1; column <= matrixB.getHead().getColumn(); column++) {
                Node tempA = this.findPosition(row, column);
                Node tempB = matrixB.findPosition(row, column);
                if (tempA == null && tempB == null) {
                    continue;
                }
                if (tempA != null && tempB != null) {
                    result.storeData(row, column, tempA.getValue() + tempB.getValue());
                } else if (tempA != null && tempB == null) {
                    result.storeData(row, column, tempA.getValue());
                } else if (tempA == null && tempB != null) {
                    result.storeData(row, column, tempB.getValue());
                }
            }
        }
        return result;
    }

}

/**
 * The {@code Node} class represents a node for linked lists.
 *
 * @author ev
 * @author drestrepom
 */
class Node {

    private int row, column; // row and column of this node
    private float value; // value of this node
    private Node nextRow, nextColumn; // next row and column node head

    /**
     * Initializes a new node.
     *
     * @param row    the row.
     * @param column the column.
     * @param value  the value.
     * @throws IllegalArgumentException if {@code row} is zero or negative.
     * @throws IllegalArgumentException if {@code column} is zero or negative.
     */
    public Node(int row, int column, float value) {
        if (row <= 0) {
            throw new IllegalArgumentException("row cannot be zero or negative" + row);
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
