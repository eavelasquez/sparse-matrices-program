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
 */
public class SparseMatricesProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int rows, columns, values;
        Scanner scanner = new Scanner(System.in);
        SparceMatrixTriplet sparseMatrix;

        System.out.println("Enter the number of rows:");
        rows = Integer.parseInt(scanner.nextLine());

        columns = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of columns:");

        values = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the number of values:");
        
        sparseMatrix = new SparceMatrixTriplet(rows, columns, values);
        sparseMatrix.insertTriplet(rows, columns, values);
        sparseMatrix.show();

    }

}
