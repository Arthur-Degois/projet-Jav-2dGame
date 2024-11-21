package com.DontDieDude.game;

public class Constants {

    public static class PlayerConstants {
        public static final int moveDown = 0;
        public static final int moveUp = 1;
        public static final int moveRight = 2;
        public static final int moveLeft = 3;

        public static int getSpriteAmount(int nbr){
            switch(nbr) {
                case moveDown:
                    return 6;
                case moveUp:
                    return 6;
                case moveRight:
                    return 6;
                case moveLeft:
                    return 6;
                default:
                    return 6;
            }
        }
    }
}