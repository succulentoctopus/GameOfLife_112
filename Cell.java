// =============================================================================
/**
 * A <code>Cell</code> keeps track of its own liveness.  It also can determine
 * its own evolution by examining its neighbors and applying its survival rules.
 **/
// =============================================================================



// =============================================================================
public class Cell {
// =============================================================================

    

    // =========================================================================
    /**
     * The specialized constructor.  Create a new, initially-dead cell.
     *
     * @param grid The <code>Grid</code> that contains this cell.
     * @param row The row coordinate of this cell within its <code>Grid</code>.
     * @param column The column coordinate of this cell within its
     *               <code>Grid</code>.
     **/
    public Cell (Grid grid, int row, int column) {

	// Set the initial state to be dead.
	_isAlive = false;

	// Store the grid and the coorindates within that grid.
	_grid = grid;
	_row = row;
	_column = column;

    } // Cell()
    // =========================================================================



    // =========================================================================
    /**
     * Indicate whether this cell is currently dead or alive.
     *
     * @return <code>true</code> if the cell is alive; <code>false</code> if it
     *         is dead.
     **/
    public boolean isAlive () {

	return _isAlive;

    } // isAlive()
    // =========================================================================



    // =========================================================================
    /**
     * Set the cell to be alive.
     **/
    public void makeAlive () {

	_isAlive = true;
	
    } // makeAlive ()
    // =========================================================================



    // =========================================================================
    /**
     * Set the cell to be dead.
     **/
    public void makeDead () {

	_isAlive = false;
	
    } // makeDead ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the row coordinate of this cell in its <code>Grid</code>.
     *
     * @return The row coordinate of this cell.
     **/
    public int getRow () {

	return _row;

    } // getRow ()
    // =========================================================================




    // =========================================================================
    /**
     * Provide the column coordinate of this cell in its <code>Grid</code>.
     *
     * @return The column coordinate of this cell.
     **/
    public int getColumn () {

	return _column;

    } // getColumn ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide a textual representation of the cell's state.
     *
     * @return One particular character to represent liveness, another to
     *         represent deadness.  The characters chosen depend on the type of
     *         cell, and thus are determined by the subclasses.
     **/
    public String toString () {

	if (_isAlive) {
	    return "+";
	} else {
	    return "-";
	}
	
    }
    // =========================================================================



    // =========================================================================
    /**
     * Traverse the standard neighborhood (the surrounding 8 <code>Cell</code>s
     * in the <code>Grid</code>) and count the number of neighbors that are
     * alive.
     *
     * @return The number of live neighbors in the standard neighborhood.
     **/
    private int countLiveNeighbors () {

	// WRITE ME


        //integer that keeps track of number of live cells
        int count = 0;

        //standard neighborhood is 3x3 with cell in question in the middle.

        //loop through all neighbors
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                if (j==0 && i==0) {
                    continue;
                }
                Cell c = this._grid.getCell(this._row-i, this._column-j);
                if (c != null && c._isAlive) {
                    count++;
                }
            }
        }

        /*
        //top row of neighbors
        if (this._row != 0) {
            if (this._column != 0) {
                if (this._grid.getCell(this._row-1, this._column-1)._isAlive) {
                    count++;
                }
            }
            if (this._column != _grid.getColumns()) {
                if (this._grid.getCell(this._row-1, this._column+1)._isAlive) {
                    count++;
                }
            }
            if (this._grid.getCell(this._row-1, this._column)._isAlive) {
                count++;
            }
        }

        //neighbors adjacent to cell in its row
        if (this._column != 0) {
            if (this._grid.getCell(this._row, this._column-1)._isAlive) {
                count++;
            }
        }
        if (this._column != _grid.getColumns()) {
            if (this._grid.getCell(this._row, this._column+1)._isAlive) {
                count++;
            }
        }

        //bottom row of neighbors
        if (this._row != _grid.getRows()) {
            if (this._column != 0) {
                if (this._grid.getCell(this._row+1, this._column-1)._isAlive) {
                    count++;
                }
            }
            if (this._column != _grid.getColumns()) {
                if (this._grid.getCell(this._row+1, this._column+1)._isAlive) {
                    count++;
                }
            }
            if (this._grid.getCell(this._row+1, this._column)._isAlive) {
                count++;
            }
        }

         */


        return count;


    }


    /**
     * Based on its neighbors' states, evolve this cell by calculating and
     * adopting its state for next generation.  Here, the Conway rules are that
     * <i>a live cell with 2 or 3 live neighbors remains alive, a dead cell with
     * 3 live neighbors becomes alive, and all other cells will die</i>.
     **/
    public void evolve () {

	// WRITE ME

        //save live neighbors as an int value
        int numAliveNeighbors = this.countLiveNeighbors();

        //apply Conway rules

        //1. a live cell with 2 or 3 live neighbors remains alive
        if (this._isAlive && (numAliveNeighbors == 2 || numAliveNeighbors == 3)) {
            this._willBeAlive = true;
        }

        //2. a dead cell with 3 live neighbors becomes alive
        else if (!this._isAlive && numAliveNeighbors == 3) {
            this._willBeAlive = true;
        }
        else {
            this._willBeAlive = false;
        }

    } // evolve ()
    // =========================================================================



    // =========================================================================
    /**
     * Advance to the next generation.
     **/
    public void advance () {

	// WRITE ME

        //change state of cell into current liveness based on its calculated liveness of next generation
        if (this._willBeAlive) {
            this.makeAlive();
        }
        else {
            this.makeDead();
        }
	
    }
    // =========================================================================



    // =========================================================================
    // DATA MEMBERS

    /**
     * The current liveness.
     **/
    boolean _isAlive;

    /**
     * The liveness in the next generation.
     **/
    boolean _willBeAlive;

    /**
     * The <code>Grid</code> that contains this cell.
     **/
    Grid _grid;

    /**
     * The cell's row coordinate within its <code>Grid</code>.
     **/
    int _row;

    /**
     * The cell's column coordinate within its <code>Grid</code>.
     **/
    int _column;

    /**
     * Whether to provide debugging information.
     **/
    final static boolean _debug = false;
    // =========================================================================



// =============================================================================
} // class Cell
// =============================================================================
