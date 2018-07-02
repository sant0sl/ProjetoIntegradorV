package com.jogodavelha.model;

import java.io.Serializable;

public class Played  implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean leaveGame;
    private Integer row;
    private Integer column;


    public Played(int row, int column) {
    	this.leaveGame = false;
        this.row = row;
        this.column = column;
    }

    public Played(boolean leaveGame) {
        this.setLeaveGame(leaveGame);
    }

    public Integer getRow() {
        return row;
    }
    public void setRow(Integer row) {
        this.row = row;
    }
    public Integer getColumn() {
        return column;
    }
    public void setColumn(Integer column) {
        this.column = column;
    }
    public boolean isLeaveGame() {
        return leaveGame;
    }
    public void setLeaveGame(boolean leaveGame) {
        this.leaveGame = leaveGame;
    }
}