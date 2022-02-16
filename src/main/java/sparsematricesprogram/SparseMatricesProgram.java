/******************************************************************************
 *  Compilation:  javac SparseMatricesProgram.java
 *  Execution:    java SparseMatricesProgram
 *  
 *  A sparse matrix, implementing with triplet, form 1 and form 2.
 *
 ******************************************************************************/
package sparsematricesprogram;

import java.util.Scanner;

/**
 *  The {@code SparseMatricesProgram} class represents a sparse matrix program.
 *
 *  @author ev
 *  @author drestrepom
 */
public class SparseMatricesProgram {

    /**
     * Unit tests the {@code SparseMatricesProgram} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int rows, columns, values;
        Scanner scanner = new Scanner(System.in);
        SparseMatrixTriplet sparseMatrix, sparseMatrixTranspose;

        System.out.println("Enter the number of rows of the sparse matrix:");
        rows = Integer.parseInt(scanner.nextLine());

        columns = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of columns of the sparse matrix:");

        values = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of values of the sparse matrix:");
        
        sparseMatrix = new SparseMatrixTriplet(rows, columns, values);
        sparseMatrix.insertTriplet(rows, columns, values);
        // sparseMatrix.insertData(values);
        sparseMatrix.show();

        sparseMatrixTranspose = sparseMatrix.transpose();

        System.out.println("\n\nThe sparse matrix is:" + "\n");
        sparseMatrix.show();
        System.out.println("\n\nThe transpose of the sparse matrix is:" + "\n");
        sparseMatrixTranspose.show();    
    }
}
