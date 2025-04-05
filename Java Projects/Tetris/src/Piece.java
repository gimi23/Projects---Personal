package TetrisFiles;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Piece {
    private Pane root;
    private Game game;
    private Rectangle[] current;
    private Block[] members;
    private Block block4;
    public Piece(Pane root, Game game) {
        this.members = new Block[4];
        this.root = root;
        this.game = game;
        this.randomPiece();
    }

    public void randomPiece() {
        int a = (int) (Math.random() * 7);
        Block block1;
        Block block2;
        Block block3;
        switch (a) {
            case 0:
                block1 = new Block(250, 0, 0, this.root, this.game);
                this.members[0] = block1;
                block2 = new Block(250, 50, 0, this.root, this.game);
                this.members[1] = block2;
                block3 = new Block(250, 100, 0, this.root, this.game);
                this.members[2] = block3;
                this.block4 = new Block(300, 100, 0, this.root, this.game);
                this.members[3] = block4;
                break;

            case 1:
                block1 = new Block(250, 0, 1, this.root, this.game);
                this.members[0] = block1;
                block2 = new Block(200, 0, 1, this.root, this.game);
                this.members[1] = block2;
                block3 = new Block(300, 0, 1, this.root, this.game);
                this.members[2] = block3;
                this.block4 = new Block(250, 50, 1, this.root, this.game);
                this.members[3] = block4;
                break;

            case 2:
                block1 = new Block(250, 0, 2, this.root, this.game);
                this.members[0] = block1;
                block2 = new Block(200, 50, 2, this.root, this.game);
                this.members[1] = block2;
                block3 = new Block(300, 0, 2, this.root, this.game);
                this.members[2] = block3;
                block4 = new Block(250, 50, 2, this.root, this.game);
                this.members[3] = block4;
                break;

            case 3:
                block1 = new Block(250, 0, 3, this.root, this.game);
                this.members[0] = block1;
                block2 = new Block(300, 50, 3, this.root, this.game);
                this.members[1] = block2;
                block3 = new Block(200, 0, 3, this.root, this.game);
                this.members[2] = block3;
                this.block4 = new Block(250, 50, 3, this.root, this.game);
                this.members[3] = block4;
                break;

            case 4:
                block1 = new Block(250, 0, 4, this.root, this.game);
                this.members[0] = block1;
                block2 = new Block(300, 50, 4, this.root, this.game);
                this.members[1] = block2;
                block3 = new Block(300, 0, 4, this.root, this.game);
                this.members[2] = block3;
                this.block4 = new Block(250, 50, 4, this.root, this.game);
                this.members[3] = block4;
                break;

            case 5:
                block1 = new Block(250, 0, 5, this.root, this.game);
                this.members[0] = block1;
                block2 = new Block(250, 150, 5, this.root, this.game);
                this.members[1] = block2;
                block3 = new Block(250, 50, 5, this.root, this.game);
                this.members[2] = block3;
                this.block4 = new Block(250, 100, 5, this.root, this.game);
                this.members[3] = block4;
                break;

            case 6:
                block1 = new Block(300, 0, 6, this.root, this.game);
                this.members[0] = block1;
                block2 = new Block(300, 50, 6, this.root, this.game);
                this.members[1] = block2;
                block3 = new Block(300, 100, 6, this.root, this.game);
                this.members[2] = block3;
                this.block4 = new Block(250, 100, 6, this.root, this.game);
                this.members[3] = block4;
                break;
        }
    }


    public void moveLeft() {
        int count = 0;
        for (Block member : this.members) {
            if (member.cannotMoveLeft()) {
                count++;
            }
        }
        if (count == 0) {
            for (Block member : this.members) {
                member.moveLeft();
            }
        }
    }

    public void moveRight() {
        int count = 0;
        for (Block member : this.members) {
            if (member.cannotMoveRight()) {
                count++;
            }
        }
        if (count == 0) {
            for (Block member : this.members) {
                member.moveRight();
            }
        }
    }

    public void moveDown() {
        int count = 0;
        for (Block member : this.members) {
            if (member.cannotMoveDown()) {
                count++;
            }
        }
        if (count == 0) {
            for (Block member : this.members) {
                member.moveDown();
            }
        }
    }

    public boolean canMove() {
        int count = 0;
        for (Block member : this.members) {
            if (member.cannotMoveDown()) {
                count++;
            }
        }
        return count == 0;
    }

    public void setLocation() {
        for (Block member : this.members) {
            this.game.setAvailability(member, (int) ((member.getX() - 50)/50), (int) (member.getY()/50));
        }
    }

    public void rotateLeft() {
        double a = this.block4.getX();
        double b = this.block4.getY();
        int count = 0;

        for (Block member : this.members) {
            double x = member.getX();
            double y = member.getY();
            double rotateX = -(y - b) + a;
            double rotateY = (x - a) + b;

            int arrayX = Math.round((float)((rotateX - 50) / 50));
            int arrayY = Math.round((float)(rotateY / 50));

            if (arrayX >= 0 && arrayX <= 9 && arrayY >= 0 && arrayY <= 17) {
                if (this.game.isAvailable(arrayX, arrayY)) {
                    count++;
                }
            }
        }

        if (count == 4) {
            for (Block member : this.members) {
                double x = member.getX();
                double y = member.getY();
                double rotateX = -(y - b) + a;
                double rotateY = (x - a) + b;

                double changeX = Math.round((float)(rotateX - x));
                double changeY = Math.round((float)(rotateY - y));

                member.setNewLocation(changeX, changeY);
            }
        }
    }

    public void rotateRight() {
        double a = this.block4.getX();
        double b = this.block4.getY();
        int count = 0;

        for (Block member : this.members) {
            double x = member.getX();
            double y = member.getY();
            double rotateX = (y - b) + a;
            double rotateY = -(x - a) + b;

            int arrayX = Math.round((float)((rotateX - 50) / 50));
            int arrayY = Math.round((float)(rotateY / 50));

            if (arrayX >= 0 && arrayX <= 9 && arrayY >= 0 && arrayY <= 17) {
                if (this.game.isAvailable(arrayX, arrayY)) {
                    count++;
                }
            }
        }

        if (count == 4) {
            for (Block member : this.members) {
                double x = member.getX();
                double y = member.getY();
                double rotateX = (y - b) + a;
                double rotateY = -(x - a) + b;

                double changeX = Math.round((float)(rotateX - x));
                double changeY = Math.round((float)(rotateY - y));

                member.setNewLocation(changeX, changeY);
            }
        }
    }

    public void removeBlocks() {
        for (Block member : this.members) {
            member.removeBlock();
        }
    }
}
