/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparsematricesprogram;

import java.util.Scanner;

/**
 *
 * @author ev
 * @author drestrepom
 */
public class SparseMatrixTriplet {

    private int rows, columns; // dimensions of sparse matrix
    private int values; // total number of elements in matrix

    // Array representation of sparse matrix
    // [][0] represents rows
    // [][1] represents columns
    // [][2] represents values
    float sparseMatrix[][];

    /**
     *
     * @param rows
     * @param columns
     * @param values
     */
    public SparseMatrixTriplet(int rows, int columns, int values) {
        this.rows = rows; // initialize rows
        this.columns = columns; // initialize columns
        this.values = values + 1; // initialize values

        this.sparseMatrix = new float[this.values][3];
        this.sparseMatrix[0][0] = this.rows;
        this.sparseMatrix[0][1] = this.columns;
        this.sparseMatrix[0][2] = this.values;
    }

    /**
     *
     * @return
     */
    public int getRows() {
        return rows;
    }

    /**
     *
     * @param rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     *
     * @return
     */
    public int getColumns() {
        return columns;
    }

    /**
     *
     * @param columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     *
     * @return
     */
    public int getValues() {
        return values;
    }

    /**
     *
     * @param values
     */
    public void setValues(int values) {
        this.values = values;
    }

    /**
     *
     */
    public void show() {
        String string = "";
        for (int i = 0; i < this.sparseMatrix[0][2] + 1; i++) {
            string += this.sparseMatrix[i][0] + "   " + this.sparseMatrix[i][1] + "    " + this.sparseMatrix[i][2];
        }
        System.out.println("Sparse Matrix Data: " + string);
    }

    /**
     *
     * @param size
     */
    public void enterTripletData(int size) {
        int row, column;
        float value;
        boolean answer = false;
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i <= size; i++) {
            System.out.println("Enter the number of rows");
            row = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter the number of columns");
            column = Integer.parseInt(scanner.nextLine());

            if (row > 0 && row < this.rows && column >= 0 && column < this.columns) {

            }
        }
    }

    /**
     *
     * @param row the row.
     * @param column the column.
     * @return
     */
    public int findPosition(int row, int column) {
        int i = 1;

        while (i < this.sparseMatrix[0][2] + 1
                && this.sparseMatrix[i][0] < row
                && this.sparseMatrix[i][2] != 0) {
            i += 1;
        }
        while (i < this.sparseMatrix[0][2] + 1
                && this.sparseMatrix[i][0] <= row
                && this.sparseMatrix[i][1] < column
                && this.sparseMatrix[i][2] != 0) {
            i += 1;
        }

        return i;
    }

    /**
     *
     */
    public void resize() {
        this.values = this.values + 1;
        float newSparseMatrix[][] = new float[this.values][3];

        for (int i = 0; i < this.sparseMatrix[0][2] + 1; i++) {
            newSparseMatrix[i][0] = this.sparseMatrix[i][0];
            newSparseMatrix[i][1] = this.sparseMatrix[i][1];
            newSparseMatrix[i][2] = this.sparseMatrix[i][2];
        }

        this.sparseMatrix = newSparseMatrix;
    }

    /**
     *
     * @param row
     * @param column
     * @param value
     */
    public void insertTriplet(int row, int column, float value) {
        int i = findPosition(row, column);

        if (i < this.sparseMatrix[0][2] + 1
                && this.sparseMatrix[i][0] == row
                && this.sparseMatrix[i][1] == column) {
            float newValue = this.sparseMatrix[i][2] + value;

            if (newValue != 0) {
                this.sparseMatrix[i][2] = newValue;
            } else {
                for (int j = i; j < this.sparseMatrix[0][2] - 1; j++) {
                    this.sparseMatrix[j][0] = this.sparseMatrix[j + 1][0];
                    this.sparseMatrix[j][1] = this.sparseMatrix[j + 1][1];
                    this.sparseMatrix[j][2] = this.sparseMatrix[j + 1][2];
                }

                this.sparseMatrix[0][2] -= 1;
            }
        } else {
            if (this.sparseMatrix[0][2] + 1 == this.values) {
                this.resize();
            }

            for (int k = (int) this.sparseMatrix[0][2] - 1; k > i; k--) {
                this.sparseMatrix[k + 1][0] = this.sparseMatrix[k][0];
                this.sparseMatrix[k + 1][1] = this.sparseMatrix[k][1];
                this.sparseMatrix[k + 1][2] = this.sparseMatrix[k][2];
            }

            this.sparseMatrix[i][0] = row;
            this.sparseMatrix[i][1] = column;
            this.sparseMatrix[i][2] = value;
        }
    }

    /**
     *
     * @param row
     * @param column
     * @param value
     * @return
     */
    public boolean storeTriplet(int row, int column, float value) {
        boolean isExist = true;
        int i = findPosition(row, column);

        if (i < this.sparseMatrix[0][2] + 1
                && this.sparseMatrix[i][0] == row
                && this.sparseMatrix[i][1] == column) {
            System.out.println("A value already exists in that position.");
            isExist = false;
        } else {
            for (int j = (int) this.sparseMatrix[0][2]; j > i; j--) {
                this.sparseMatrix[j][0] = this.sparseMatrix[j - 1][0];
                this.sparseMatrix[j][1] = this.sparseMatrix[j - 1][1];
                this.sparseMatrix[j][2] = this.sparseMatrix[j - 1][2];
            }
            this.sparseMatrix[i][0] = row;
            this.sparseMatrix[i][1] = column;
            this.sparseMatrix[i][2] = value;
        }

        return isExist;
    }

    /**
     * 
     * @return 
     */
    public int addValues() {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += (int) this.sparseMatrix[i][3];
        }

        return result;
    }

    public void rowWithLeastSum() {
        int i = 2, j = 1, row = 0, rowLessSum = 0, lessSum = 0, currentSum = 0, previousSum = 0;

        while (i <= this.sparseMatrix[1][3] + 1) {
            currentSum = 0;
            row = (int) this.sparseMatrix[i][1];

            while (j <= this.sparseMatrix[1][3] + 1 && row == this.sparseMatrix[i][1]) {
                currentSum += (int) this.sparseMatrix[i][3];
                j += 1;
            }

            if (row != rowLessSum) {
                if (currentSum < previousSum) {
                    previousSum = currentSum;
                    rowLessSum = row;
                }
            }
            row = rowLessSum;

            if (currentSum < previousSum) {
                previousSum = currentSum;
                rowLessSum = row;
            } else {
                previousSum = 0;
            }
        }
        System.out.println("The " + row + " has the least sum:" + lessSum);
    }

    /**
     * 
     */
    public void rowWithLeastSum() {
        int i = 2, j = 1, row = 0, rowLessSum = 0, lessSum = 0, currentSum = 0, previousSum = 0;

        while (i <= this.sparseMatrix[1][3] + 1) {
            currentSum = 0;
            row = (int) this.sparseMatrix[i][1];

            while (j <= this.sparseMatrix[1][3] + 1 && row == this.sparseMatrix[i][1]) {
                currentSum += (int) this.sparseMatrix[i][3];
                j += 1;
            }

            if (row != rowLessSum) {
                if (currentSum < previousSum) {
                    previousSum = currentSum;
                    rowLessSum = row;
                }
            }
            row = rowLessSum;

            if (currentSum < previousSum) {
                previousSum = currentSum;
                rowLessSum = row;
            } else {
                previousSum = 0;
            }
        }
        System.out.println("The " + row + " has the least sum:" + lessSum);
    }

    /**
     * Adding Two matrices
     *
     * @param B
     */
    public void add(SparseMatrixTriplet B) {
        // if matrices don't have same dimensions
        if (this.rows != B.rows || this.columns != B.columns) {
            System.out.println("Matrices can't be added.");
        } else {
            SparseMatrixTriplet sum = new SparseMatrixTriplet(this.rows, this.columns, 0);

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    // sum[i][j] = this.sparseMatrix[i][j] + B[i][j];  
                }
            }

            System.out.println("Sum of two matrices is: ");
        }
    }

    /**
     * Mutliplying Two matrices
     *
     * @param B
     */
    public void multiply(SparseMatrixTriplet B) {
        if (this.columns != B.rows) {
            System.out.println("Matrices can't be multiplied.");
        } else {
            SparseMatrixTriplet product = new SparseMatrixTriplet(this.rows, B.columns, 0);

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < B.columns; j++) {
                    for (int k = 0; k < this.columns; k++) {
                        // product[i][j] += this.sparseMatrix[i][k] * B[k][j];
                    }
                }
            }

            System.out.println("Multiplication of two matrices is: ");
        }
    }
    
    /**
     * Transpose the matrix
     *
     * @return
     */
    public SparseMatrixTriplet transpose() {
        SparseMatrixTriplet transpose = new SparseMatrixTriplet(this.getRows(), this.getColumns(), this.getValues());

        for (int i = 0; i < this.sparseMatrix[0][2] + 1; i++) {
            transpose.storeTriplet((int) this.sparseMatrix[i][1], (int) this.sparseMatrix[i][0], this.sparseMatrix[i][2]);
        }

        return transpose;
    }
}
