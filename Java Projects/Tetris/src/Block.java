package TetrisFiles;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Block {
    private Shape individualList[];
    private Game game;
    private Pane root;
    public Block(double x, double y, int i, Pane root, Game game) {
        this.individualList = new Shape[14];
        this.root = root;
        this.game = game;
        this.createBlock(x, y, i);
    }

    public void createBlock(double x, double y, int i) {
        Color border = Color.web("#ffffff");
        Color fill = Color.web("#ffffff");
        Color outline = Color.web("#ffffff");
        switch (i) {
            case 0:
                border = Color.web("#833c3c");
                fill = Color.web("#934d4d");
                outline = Color.web("#6d2020");
                break;
            case 1:
                border = Color.web("#d26722");
                fill = Color.web("#e37834");
                outline = Color.web("#ab4301");
                break;
            case 2:
                border = Color.web("#c7a11e");
                fill = Color.web("#d5b132");
                outline = Color.web("#a27e00");
                break;
            case 3:
                border = Color.web("#509a39");
                fill = Color.web("#63ac4c");
                outline = Color.web("#236d0c");
                break;
            case 4:
                border = Color.web("#3952b0");
                fill = Color.web("#4962c0");
                outline = Color.web("#163197");
                break;
            case 5:
                border = Color.web("#a12dc2");
                fill = Color.web("#b240d3");
                outline = Color.web("#6c008c");
                break;
            case 6:
                border = Color.web("#23b865");
                fill = Color.web("#35c977");
                outline = Color.web("#009342");
                break;
        }
        Rectangle outerBlock = new Rectangle(50, 50);
        this.individualList[0] = outerBlock;
        outerBlock.setFill(border);
        outerBlock.setLayoutX(x);
        outerBlock.setLayoutY(y);
        Rectangle innerBlock = new Rectangle(36, 36);
        this.individualList[1] = innerBlock;
        innerBlock.setFill(fill);
        innerBlock.setLayoutX(x + 7);
        innerBlock.setLayoutY(y + 7);
        Line line1 = new Line(x, y, x + 7, y + 7);
        this.individualList[2] = line1;
        line1.setStroke(outline);
        Line line2 = new Line(x + 50, y + 50, x + 43, y + 43);
        this.individualList[3] = line2;
        line2.setStroke(outline);
        Line line3 = new Line(x, y + 50, x + 7, y + 43);
        this.individualList[4] = line3;
        line3.setStroke(outline);
        Line line4 = new Line(x + 50, y, x + 43, y + 7);
        this.individualList[5] = line4;
        line4.setStroke(outline);
        Line line5 = new Line(x, y, x + 50, y);
        this.individualList[6] = line5;
        line5.setStroke(outline);
        Line line6 = new Line(x , y, x, y + 50);
        this.individualList[7] = line6;
        line6.setStroke(outline);
        Line line7 = new Line(x + 50, y, x + 50, y + 50);
        this.individualList[8] = line7;
        line7.setStroke(outline);
        Line line8 = new Line(x , y + 50, x + 50, y + 50);
        this.individualList[9] = line8;
        line8.setStroke(outline);
        Line line9 = new Line(x + 7, y + 7, x + 43, y + 7);
        this.individualList[10] = line9;
        line9.setStroke(outline);
        Line line10 = new Line(x + 7, y + 7, x + 7, y + 43);
        this.individualList[11] = line10;
        line10.setStroke(outline);
        Line line11 = new Line(x + 7, y + 43, x + 43, y + 43);
        this.individualList[12] = line11;
        line11.setStroke(outline);
        Line line12 = new Line(x + 43, y + 7, x + 43, y + 43);
        this.individualList[13] = line12;
        line12.setStroke(outline);
        this.root.getChildren().addAll(outerBlock, innerBlock, line1, line2, line3, line4,
                line5, line6, line7, line8, line9, line10, line11, line12);
    }


    public void moveLeft(){
        for (Shape individualList: this.individualList) {
            individualList.setLayoutX(individualList.getLayoutX() - 50);
        }
    }

    public void moveRight(){
        for (Shape individualList: this.individualList) {
            individualList.setLayoutX(individualList.getLayoutX() + 50);
        }
    }

    public void moveDown() {
        for (Shape individualList: this.individualList) {
            individualList.setLayoutY(individualList.getLayoutY() + 50);
        }
    }

    public boolean cannotMoveLeft() {
        if (this.individualList[0].getLayoutX() < 75) {
            return true;
        }

        else {
            int x = (int) ((this.individualList[0].getLayoutX() - 100) / 50);
            int y = (int) ((this.individualList[0].getLayoutY()) / 50);
            return !this.game.isAvailable(x, y);
        }
    }

    public boolean cannotMoveRight() {
        if (this.individualList[0].getLayoutX() > 490) {
            return true;
        }
        else {
            int x = (int) ((this.individualList[0].getLayoutX()) / 50);
            int y = (int) ((this.individualList[0].getLayoutY()) / 50);
            return !this.game.isAvailable(x, y);
        }
    }

    public boolean cannotMoveDown() {
        if (this.individualList[0].getLayoutY() > 825) {
            return true;
        }
        else {
            int x = (int) ((this.individualList[0].getLayoutX() - 50) / 50);
            int y = (int) ((this.individualList[0].getLayoutY() + 50) / 50);
            return !this.game.isAvailable(x, y);
        }
    }

    public double getX() {
        return this.individualList[0].getLayoutX();
    }

    public double getY() {
        return this.individualList[0].getLayoutY();
    }

    public void setNewLocation(double x, double y) {
        for (Shape individualList : this.individualList) {
            double a = individualList.getLayoutX();
            double b = individualList.getLayoutY();
            individualList.setLayoutX(a + x);
            individualList.setLayoutY(b + y);
        }
    }

    public void removeBlock() {
        for (Shape individualList: this.individualList) {
            this.root.getChildren().remove(individualList);
        }
    }
}
