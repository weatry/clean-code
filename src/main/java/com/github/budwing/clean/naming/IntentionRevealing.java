package com.github.budwing.clean.naming;

import java.util.ArrayList;
import java.util.List;

public class IntentionRevealing {
    public static final String SLOGAN = "USE INTENTION-REVEALING NAMES";

    private static final int FLAGGED = 4;
    private static final int STATUS_VALUE = 0;

    /**
     * By using intention-revealing names, we can rename the variable d to elapsedTimeInDays.
     * It clearly indicates what is being measured and the unit of that measurement. We can remove
     * the commented-out alternative names since they are no longer needed.
     */
    private int elapsedTimeInDays;

    /**
     * By renaming the method to getFlaggedCells, the parameter to gameBoard, and the variable to flaggedCells,
     * we make the code more intention-revealing. It becomes clear that the method retrieves flagged cells from a game board.
     * You don't need to refer to the comments to understand the purpose of the method and its variables.
     * 
     * But it's still obscure what "cell[0]" means. We can further improve the code by introducing a Cell class
     * with a method isFlagged(). This way, we encapsulate the cell's status within the Cell class, making the code even more intention-revealing.
     */
    public List<int[]> getFlaggedCellsV1(List<int[]> gameBoard) {
        List<int[]> flaggedCells = new ArrayList<int[]>();
        for (int[] cell : gameBoard)
            if (cell[STATUS_VALUE] == FLAGGED)
                flaggedCells.add(cell);
    
        return flaggedCells;
    }

    /**
     * Cell is the smallest unit of the mine sweeper game board.
     * The status of a cell is encapsulated within the Cell class.
     * By using the isFlagged() method, we make the code more intention-revealing.
     */
    public static class Cell {
        private int status;

        public boolean isFlagged() {
            return status == 4;
        }
    }

    /**
     * Now, we can further improve the getFlaggedCells method by using the Cell class.
     * The code is self-explanatory, and we don't need comments to understand its purpose.
     */
    public List<Cell> getFlaggedCells(List<Cell> gameBoard) {
        List<Cell> flaggedCells = new ArrayList<Cell>();
        for (Cell cell : gameBoard)
            if (cell.isFlagged())
                flaggedCells.add(cell);
    
        return flaggedCells;
    }

    /**
     * Exercise for intention-revealing names.
     * 
     * By defining the OrderItem class, we make the code more intention-revealing.
     */
    public class OrderItem {
        private int quantity;
        private int unitPrice;

        public OrderItem(int quantity, int unitPrice) {
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
    }

    /**
     * The method name getTotalPrice clearly indicates its purpose.
     * 
     * Can you improve it further?
     */
    public int getTotalPrice(List<OrderItem> orderItems) {
        int totalPrice = 0;
        for (OrderItem item : orderItems) {
            totalPrice += item.quantity * item.unitPrice;
            System.out.println(totalPrice);
        }

        return totalPrice;
    }

}
