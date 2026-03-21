package domain;

import java.awt.Color;
/**
 * Write a description of class ShadowMark here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShadowMark implements Thing {
    private int row;
    private int column;
    private Forest forest;

    public ShadowMark(Forest forest, int row, int column) {
        this.forest = forest;
        this.row = row;
        this.column = column;
    }

    @Override
    public void ticTac() {
        // Las marcas no hacen nada por sí solas
    }

    @Override
    public void changeSeason() {}

    @Override
    public Color getColor() {
        return Color.black;
    }

    @Override
    public int shape() {
        return Thing.SQUARE;
    }
}