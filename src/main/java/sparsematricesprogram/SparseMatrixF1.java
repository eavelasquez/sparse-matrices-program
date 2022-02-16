/******************************************************************************
 *  Compilation:  javac SparseMatrixF1.java
 *  Execution:    java SparseMatrixF1
 *  
 *  A sparse matrix, implementing form 1.
 *
 ******************************************************************************/
package sparsematricesprogram;

/**
 *  The {@code SparseMatrixF1} class represents a sparse matrix form 1.
 *
 *  @author ev
 *  @author drestrepom
 */
public class SparseMatrixF1 {

    private Node head;

    /**
     * Initializes an empty sparse matrix.
     *
     * @param rows the number of rows in the sparse matrix.
     * @param columns the number of columns in the sparse matrix.
     */
    public SparseMatrixF1(int rows, int columns) {
        this.head = createNewNode(rows, columns);
    }

    /**
     * Returns the node head of this sparse matrix.
     *
     * @return the node head of this sparse matrix.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Sets the node head of this sparse matrix.
     *
     * @param head the node head of this sparse matrix.
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * This method is used to create a new node for the sparse matrix.
     *
     * @param rows the number of rows in the sparse matrix.
     * @param columns the number of columns in the sparse matrix.
     * @return the node head of this sparse matrix.
     */
    private Node createNewNode(int rows, int columns) {
        int length = rows > columns ? rows : columns;
        Node start = new Node(rows, columns, 0), latest;

        start.setNextRow(start);
        start.setNextColumn(start);

        latest = start;

        for (int row = 0; row < length; row++) {
            Node newNode = new Node(row, row, 0);

            newNode.setNextRow(newNode);
            newNode.setNextColumn(newNode);

            latest.setNext(newNode);
            latest = newNode;
        }
        latest.setNext(start);

        return start;
    }

    /**
     * This method is used to get a string that represents the sparse matrix.
     *
     * @return the string that represents the sparse matrix.
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

        System.out.println("Sparse Matrix = \n" + string);
        return string;
    }

    /**
     * This method is used to get the value of a specific cell of the sparse matrix.
     *
     * @param row the row of the cell.
     * @param column the column of the cell.
     * @return the value of the cell.
     */
    public Node findPosition(int row, int column) {
        Node start = this.head.getNext();
        while (start != this.head) {
            if (start.getRow() == row) {
                while (start != this.head) {
                    if (start.getColumn() == column) {
                        if (start.getNextRow().getColumn() == column) {
                            return start.getNextRow();
                        }
                        return start;
                    }
                    start = start.getNextRow();
                }
            }
            start = start.getNext();
        }
        return null;
    }

    /**
     * This method is used to store the value of a specific cell of the sparse matrix.
     *
     * @param row the row of the cell.
     * @param column the column of the cell.
     * @param value the value of the cell.
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
     * This method is used to insert a new value in the sparse matrix.
     *
     * @param row the row of the cell.
     * @param column the column of the cell.
     * @param value the value of the cell.
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
     * This method is used to bind a row to a sparse matrix.
     *
     * @param node the node that represents the row to be binded.
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
     * This method is used to bind a column to a sparse matrix.
     *
     * @param node the node that represents the column to be binded.
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
     * This method is used to unbind a row from a sparse matrix.
     *
     * @param node the node that represents the row to be unbinded.
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
     * This method is used to unbind a column from a sparse matrix.
     *
     * @param node the node that represents the column to be unbinded.
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
     * This method is used to get the row with the greatest value.
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
                    System.out.println(
                            "The greatest data is in the row " + start.getRow() + " and has a value " + greatValue);
                }
            }
            start = start.getNext();
        }
    }

    /**
     * This method is used to transpose a sparse matrix of this class.
     *
     * @return the transpose of the sparse matrix.
     */
    public SparseMatrixF1 transpose() {
        if (this.head.getRow() == 0 || this.head.getColumn() == 0) {
            return null;
        }

        SparseMatrixF1 result = new SparseMatrixF1(this.getHead().getColumn(), this.getHead().getRow());
        Node start = this.head.getNext(), temp;

        while (start != this.head) {
            temp = start.getNextRow();

            while (start != temp) {
                result.insertData(temp.getColumn(), temp.getRow(), temp.getValue());
                temp = temp.getNextRow();
            }
            start = start.getNext();
        }

        return result;
    }

    /**
     * This method is used to sum this sparse matrix and specific sparse matrix of this class.
     *
     * @param matrixB the sparse matrix to add to this sparse matrix.
     * @return the sum of this sparse matrix and specific sparse matrix.
     */
    public SparseMatrixF1 sum(SparseMatrixF1 matrixB) {
        if ((this.getHead().getRow() != matrixB.getHead().getRow())
                || this.getHead().getColumn() != matrixB.getHead().getColumn()) {
            System.out.println("The dimensions of the two sparse matrix are not equal.");
            return null;
        }

        SparseMatrixF1 result = new SparseMatrixF1(this.getHead().getRow(), this.getHead().getColumn());

        for (int row = 0; row < matrixB.getHead().getRow(); row++) {
            for (int column = 0; column < matrixB.getHead().getColumn(); column++) {
                Node tempA = this.findPosition(row, column);
                Node tempB = matrixB.findPosition(row, column);

                if (tempA == null && tempB == null) {
                    continue;
                }

                if (tempA != null && tempB != null) {
                    result.insertData(row, column, tempA.getValue() + tempB.getValue());
                } else if (tempA != null && tempB == null) {
                    result.insertData(row, column, tempA.getValue());
                } else if (tempA == null && tempB != null) {
                    result.insertData(row, column, tempB.getValue());
                }
            }
        }

        return result;
    }

    /**
     * This method is used to multiply this sparse matrix and specific sparse matrix of this class.
     *
     * @param matrixB the sparse matrix to multiply to this sparse matrix.
     * @return the product of this sparse matrix and specific sparse matrix.
     */
    public SparseMatrixF1 multiply(SparseMatrixF1 matrixB) {
        if ((this.getHead().getRow() != matrixB.getHead().getRow())
                || this.getHead().getColumn() != matrixB.getHead().getColumn()) {
            System.out.println("The dimensions of the two sparse matrix are not equal.");
            return null;
        }

        SparseMatrixF1 result = new SparseMatrixF1(this.getHead().getRow(), this.getHead().getColumn());

        for (int row = 0; row < matrixB.getHead().getRow(); row++) {
            for (int column = 0; column < matrixB.getHead().getColumn(); column++) {
                int value = 0;

                for (int j = 0; j < matrixB.getHead().getRow(); j++) {
                    Node tempB = matrixB.findPosition(j, column);
                    Node tempA = this.findPosition(row, j);

                    if (tempA == null || tempB == null) {
                        continue;
                    }

                    value += tempA.getValue() * tempB.getValue();
                }

                result.insertData(row, column, value);
            }
        }

        return result;
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
         * @param row    the row.
         * @param column the column.
         * @param value  the value.
         */
        public Node(int row, int column, float value) {
            this.row = row;
            this.column = column;
            this.value = value;
            this.next = this.nextRow = this.nextColumn = null;
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
}
