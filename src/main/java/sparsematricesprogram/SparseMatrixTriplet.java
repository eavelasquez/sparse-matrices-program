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
            string += this.sparseMatrix[i][0] + "   " + this.sparseMatrix[i][1] + "   " + this.sparseMatrix[i][2]
                    + "\n";
        }

        System.out.println("Sparse Matrix Data: " + string);
    }

    public String showWithZeros() {
        int t = 1;
        String string = "";

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (t < this.sparseMatrix[0][2] + 1 && i == this.sparseMatrix[t][0] && j == this.sparseMatrix[t][1]) {
                    string += this.sparseMatrix[t][2] + "   ";
                    t += 1;
                } else {
                    string += "0.0   ";
                }
            }

            string += "\n\n";
        }

        return string;
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
     * This method is used to resize the sparse matrix.
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
    public void insert(int row, int column, float value) {
        if (row > rows || column > columns) {
            System.out.println("Wrong entry");
        } else {
            this.sparseMatrix[(int) this.sparseMatrix[0][2] + 1][0] = row;
            this.sparseMatrix[(int) this.sparseMatrix[0][2] + 1][1] = column;
            this.sparseMatrix[(int) this.sparseMatrix[0][2] + 1][2] = value;

            this.sparseMatrix[0][2] += 1;
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
            int apos = 1, bpos = 1;
            SparseMatrixTriplet sum = new SparseMatrixTriplet(this.rows, this.columns, 0);

            while (apos < this.sparseMatrix[0][2] + 1 && bpos < this.sparseMatrix[0][2] + 1) {
                if (this.sparseMatrix[apos][0] > B.findPosition(bpos, 0) ||
                        (this.sparseMatrix[apos][0] == B.findPosition(bpos, 0) &&
                                this.sparseMatrix[apos][1] > B.findPosition(bpos, 1))) {
                    // if B's row and column is smaller
                    // insert smaller value into result
                    sum.insert(B.findPosition(bpos, 0), B.findPosition(bpos, 1), B.findPosition(bpos, 2));

                    bpos += 1;
                } else if (this.sparseMatrix[apos][0] < B.findPosition(bpos, 0) ||
                        (this.sparseMatrix[apos][0] == B.findPosition(bpos, 0) &&
                                this.sparseMatrix[apos][1] < B.findPosition(bpos, 1))) {
                    // if sparseMatrix's row and column is smaller
                    // insert smaller value into result
                    sum.insert((int) this.sparseMatrix[apos][0], (int) this.sparseMatrix[apos][1],
                            this.sparseMatrix[apos][2]);

                    apos += 1;
                } else {
                    // add the values as row and column is same
                    float addedval = (this.sparseMatrix[apos][2] + B.findPosition(bpos, 2));

                    if (addedval != 0) {
                        sum.insert((int) this.sparseMatrix[apos][0], (int) this.sparseMatrix[apos][1], addedval);
                    }

                    apos += 1;
                    bpos += 1;
                }
            }

            // insert remaining elements of sparseMatrix and b
            while (apos < this.sparseMatrix[0][2] + 1) {
                sum.insert((int) this.sparseMatrix[apos][0], (int) this.sparseMatrix[apos][1],
                        this.sparseMatrix[apos++][2]);
            }

            while (bpos < this.sparseMatrix[0][2] + 1) {
                sum.insert(B.findPosition(bpos, 0), B.findPosition(bpos, 1), B.findPosition(bpos++, 2));
            }

            System.out.println("Sum of two matrices is: " + sum.showWithZeros());
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
            return;
        }

        // transpose b to compare row and col values and to add them at the end
        B = B.transpose();

        int apos, bpos;
        // result matrix of dimension row X b.col however b has been transposed, hence
        // row X b.row
        SparseMatrixTriplet product = new SparseMatrixTriplet(this.rows, B.columns, 0);

        // iterate over all elements of A
        for (apos = 1; apos < this.sparseMatrix[0][2] + 1;) {
            // current row of result matrix
            int r = (int) this.sparseMatrix[apos][0];

            // iterate over all elements of B
            for (bpos = 1; bpos < B.values + 1;) {

                // current column of result matrix this.sparseMatrix[][0] used as b is
                // transposed
                int c = B.findPosition(bpos, 0);

                // temporary pointers created to add all multiplied values to obtain current
                // 
                // element of result matrix
                int tempa = apos;
                int tempb = bpos;

                int sum = 0;

                // iterate over all elements with same row and col value to calculate result[r]
                while (tempa < this.sparseMatrix[0][2] + 1 && this.sparseMatrix[tempa][0] == r
                        && tempb < B.values + 1 && B.findPosition(tempb, 0) == c) {

                    if (this.sparseMatrix[tempa][1] < B.findPosition(tempb, 1))

                        // skip a
                        tempa++;

                    else if (this.sparseMatrix[tempa][1] > B.findPosition(tempb, 1))

                        // skip b
                        tempb++;
                    else

                        // same col, so multiply and increment
                        sum += this.sparseMatrix[tempa++][2] * B.findPosition(tempb++, 2);
                }

                // insert sum obtained in product[r] if its not equal to 0
                if (sum != 0) {
                    product.insert(r, c, sum);
                }

                while (bpos < B.values + 1 && B.findPosition(bpos, 0) == c) {
                    // jump to next column
                    bpos += 1;
                }
            }

            while (apos < this.sparseMatrix[0][2] + 1 && this.sparseMatrix[apos][0] == r) {
                // jump to next row
                apos++;
            }
        }

        product.show();
    }

    /**
     * Transpose the matrix
     *
     * @return
     */
    public SparseMatrixTriplet transpose() {
        SparseMatrixTriplet transpose = new SparseMatrixTriplet(this.getRows(), this.getColumns(), this.getValues());

        for (int i = 0; i < this.sparseMatrix[0][2] + 1; i++) {
            transpose.storeTriplet((int) this.sparseMatrix[i][1], (int) this.sparseMatrix[i][0],
                    this.sparseMatrix[i][2]);
        }

        return transpose;
    }
}
