package TetrisFiles;

import javafx.scene.layout.Pane;


public class Game {
    private Pane root;
    private Block[][] gameBoard;
    private Piece current;
    private PaneOrganizer organizer;
    public Game(Pane root, PaneOrganizer organizer) {
        this.organizer = organizer;
        this.root = root;
        this.setKeyPress();
        this.gameBoard = new Block[10][18];
        this.gameStart();
    }

    public void gameStart() {
        this.current = new Piece(this.root, this);
    }

    public void setKeyPress() {
        this.root.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case J:
                    this.moveCurrentPiece(0);
                    break;
                case K:
                    this.moveCurrentPiece(1);
                    break;
                case L:
                    this.moveCurrentPiece(2);
                    break;
                case I:
                    this.moveCurrentPiece(3);
                    break;
                case W:
                    this.moveCurrentPiece(4);
                    break;
                case SPACE:
                    this.speedDown();
                    break;
            }
        });
    }

    public void speedDown() {
        while (this.current.canMove()) {
            this.current.moveDown();
        }
    }

    public void moveCurrentPiece(int i) {
        switch (i) {
            case 0:
                this.current.moveLeft();
                break;

            case 1:
                this.current.moveDown();
                break;

            case 2:
                this.current.moveRight();
                break;

            case 3:
                this.current.rotateLeft();
                break;

            case 4:
                this.current.rotateRight();
                break;
        }
    }

    public void moveCurrentDown() {
        if (this.current.canMove()) {
            this.current.moveDown();
        }

        else {
            this.current.setLocation();
            this.current = null;
            if (!this.checkGameOver()) {
                this.current = new Piece(this.root, this);
            }
        }
    }

    public void setAvailability(Block block, int x, int y) {
        this.gameBoard[x][y] = block;
    }

    public boolean isAvailable(int x, int y) {
        return this.gameBoard[x][y] == null;
    }

    public void clearRow() {
        int count;
        for (int j = 17; j >= 0; j--) {
            count = 0;
            for (int i = 0; i <= 9; i++) {
                if (this.gameBoard[i][j] == null) {
                    count++;
                }
            }
            if (count == 0) {
                for (int k = 0; k <= 9; k++) {
                    this.gameBoard[k][j].removeBlock();
                    this.gameBoard[k][j] = null;
                }
                this.shiftDown(j);
                this.updateScore();
            }
        }
    }

    public void shiftDown(int j) {
        for (int x = j - 1; x >= 0; x--) {
            for (int y = 0; y <= 9; y++) {
                if (this.gameBoard[y][x] != null) {
                    this.gameBoard[y][x].setNewLocation(0, 50);
                }
                this.gameBoard[y][x + 1] = this.gameBoard[y][x];
                this.gameBoard[y][x] = null;
            }
        }
    }

    public void updateScore() {
        this.organizer.updateScore();
    }

    public boolean checkGameOver() {
        int count = 0;
        if (this.gameBoard[5][0] != null || this.gameBoard[5][1] != null || this.gameBoard[5][2] != null
        || this.gameBoard[6][0] != null || this.gameBoard[6][1] != null || this.gameBoard[6][2] != null
        || this.gameBoard[5][3] != null || this.gameBoard[6][3] != null || this.gameBoard[6][4] != null
        || this.gameBoard[5][4] != null) {
            count++;
        }
        return count > 0;
    }

    public void clearBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 18; j++) {
                if (this.gameBoard[i][j] != null) {
                    this.gameBoard[i][j].removeBlock();
                }
                this.gameBoard[i][j] = null;
            }
        }
    }

    public void removeCurrentPiece() {
        if (!(this.current == null)) {
            this.current.removeBlocks();
        }
    }
}
