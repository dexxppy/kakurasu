public class Cell {

    /**
     * @param coordinateY row number cell is located in
     * @param coordinateX column number cell is located in
     * @param isFilled determines whether this cell is checked
     */
    private int coordinateY;
    private int coordinateX;
    private boolean isFilled = false;


    // Constructors
    public Cell(){}
    public Cell(int coordinateX, int coordinateY){
        this.coordinateY = coordinateY;
        this.coordinateX = coordinateX;
    }

    // Getters and setters
    public int getCoordinateY() {
        return coordinateY;
    }
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }
    public int getCoordinateX() {
        return coordinateX;
    }
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }
    public boolean isFilled() {
        return isFilled;
    }
    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    @Override
    public String toString() {
        if(isFilled()){
            return " X ";
        }

        return " - ";
    }

    public void toggleFill(){
        setFilled(!isFilled());
    }

    public void fill(){
        setFilled(true);
    }

}
