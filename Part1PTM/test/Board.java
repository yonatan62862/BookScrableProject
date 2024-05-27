package test;

import java.util.*;


public class Board {


    private static Board board = null;
    public final Tile[][] tiles;
    
    private static final int MID = 7;
    private static final int SIZE = 15;
    private static final int ROW = 15;
    private static final int COL = 15;
   
    private Map<String, BonusType> BonusSlot;

    private Board() {
        this.tiles = new Tile[ROW][COL];
        
        
        BonusSlot = new HashMap<>();

        initBonuses();

       

    }

    enum BonusType {
        X2_LETTER_SCORE,
        X3_LETTER_SCORE,
        X2_WORD_SCORE,
        X3_WORD_SCORE,
        STAR
    }

    private void initBonuses() {
       
        BonusSlot.put("7,7", BonusType.STAR);

        BonusSlot.put("0,0", BonusType.X3_WORD_SCORE);
        BonusSlot.put("0,7", BonusType.X3_WORD_SCORE);
        BonusSlot.put("0,14", BonusType.X3_WORD_SCORE);
        BonusSlot.put("7,0", BonusType.X3_WORD_SCORE);
        BonusSlot.put("7,14", BonusType.X3_WORD_SCORE);
        BonusSlot.put("14,0", BonusType.X3_WORD_SCORE);
        BonusSlot.put("14,7", BonusType.X3_WORD_SCORE);
        BonusSlot.put("14,14", BonusType.X3_WORD_SCORE);

        BonusSlot.put("1,1", BonusType.X2_WORD_SCORE);
        BonusSlot.put("1,13", BonusType.X2_WORD_SCORE);
        BonusSlot.put("2,2", BonusType.X2_WORD_SCORE);
        BonusSlot.put("2,12", BonusType.X2_WORD_SCORE);
        BonusSlot.put("3,3", BonusType.X2_WORD_SCORE);
        BonusSlot.put("3,11", BonusType.X2_WORD_SCORE);
        BonusSlot.put("4,4", BonusType.X2_WORD_SCORE);
        BonusSlot.put("4,10", BonusType.X2_WORD_SCORE);
        BonusSlot.put("10,4", BonusType.X2_WORD_SCORE);
        BonusSlot.put("10,10", BonusType.X2_WORD_SCORE);
        BonusSlot.put("11,3", BonusType.X2_WORD_SCORE);
        BonusSlot.put("11,11", BonusType.X2_WORD_SCORE);
        BonusSlot.put("12,2", BonusType.X2_WORD_SCORE);
        BonusSlot.put("12,12", BonusType.X2_WORD_SCORE);
        BonusSlot.put("13,1", BonusType.X2_WORD_SCORE);
        BonusSlot.put("13,13", BonusType.X2_WORD_SCORE);

        BonusSlot.put("1,5", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("1,9", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("5,1", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("5,5", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("5,9", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("5,13", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("9,1", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("9,5", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("9,9", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("9,13", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("13,5", BonusType.X3_LETTER_SCORE);
        BonusSlot.put("13,9", BonusType.X3_LETTER_SCORE);

        BonusSlot.put("0,3", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("0,11", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("2,6", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("2,8", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("3,0", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("3,7", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("3,14", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("6,2", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("6,6", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("6,8", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("6,12", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("7,3", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("7,11", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("8,2", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("8,6", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("8,8", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("8,12", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("11,0", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("11,7", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("11,14", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("12,6", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("12,8", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("14,3", BonusType.X2_LETTER_SCORE);
        BonusSlot.put("14,11", BonusType.X2_LETTER_SCORE);

        

    }

    public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public Tile[][] getTiles() {
        Tile[][] tilesCopy = new Tile[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            System.arraycopy(tiles[i], 0, tilesCopy[i], 0, SIZE);
        }
        
        return tilesCopy;

    }

    private boolean VerticalWord(Word w) {
        int row = w.getRow();
        int col = w.getCol();
        int length = w.getTiles().length;

        if (row + length >= SIZE) {
            return false;
        }
        if (this.tiles[MID][MID] == null) {
            if (col == MID && row <= MID && row + length >= MID) {
                return true;
            }
        } else {
            for (int i = 0; i < length; i++) {
                if ((tiles[row + i][col - 1] != null && col - 1 >= 0)
                        || (tiles[row + i][col + 1] != null && col + 1 < COL)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean NoVerticalWord(Word w) {
        int row = w.getRow();
        int col = w.getCol();
        int length = w.getTiles().length;

        if (col + length >= SIZE) {
            return false;
        }
        if (this.tiles[MID][MID] == null) {
            if (row == MID && col <= MID && col + length >= MID) {
                return true;
            }
        } else {
            for (int i = 0; i < length; i++) {
                if ((tiles[row - 1][col + i] != null && row - 1 >= 0)
                        || (tiles[row + 1][col + i] != null && row + 1 < ROW)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean boardLegal(Word testWord) {
        int row = testWord.getRow();
        int col = testWord.getCol();
        if (row < 0 || row >= ROW || col < 0 || col >= COL) {
            return false;

        }
        if (testWord.isVertical()) {
            if (VerticalWord(testWord)) {
                return true;
            }
        }
        
        else {
            if (NoVerticalWord(testWord)) {
                return true;
            }
        }

        
        return false;
    }

   
    public boolean dictionaryLegal(Word word) {

        return true;
    }

   
    public ArrayList<Word> getWords(Word word) {
       
        int row = word.getRow();
        int col = word.getCol();
        boolean vertical = word.isVertical();
        int tr = 0;
        int up = 0;
        int j = 0;
        int tc = 0;

        int down = 0;
        int right = 0;
        int left = 0;
        int count = 0;
        int index = 0;
        int newTr = 0;
        int newTc = 0;

        boolean conTinue = false;
        

        ArrayList<Word> newWords = new ArrayList<>();
        newWords.add(word);

        if (vertical) {
            for (Word w : newWords) {

                for (index = 0, tr = row; index < w.getTiles().length && tr < row + w.getTiles().length; index++, tr++) {
                    if (w.getTiles()[index] == null && this.tiles[tr][col] != null) {
                        conTinue = true;
                        j = tr + 1;
                        break;
                        
                    }
                }
            }
            index = 0;
            if (!conTinue) {
                j = row;
            }
            while (this.tiles[j][col] != null && j < SIZE) {

                if (this.tiles[j][col + 1] != null && col + 1 < SIZE) {
                    right = col;
                    while (this.tiles[j][right] != null && right < SIZE) {
                        
                        if (tiles[j][right + 1] == null) {
                            count++;
                            break;
                        }
                        right++;
                        count++;

                    }
                }
                if (this.tiles[j][col - 1] != null && col - 1 >= 0) {
                    if (right != 0) {
                        left = col - 1;
                    } else {
                        left = col;
                    }
                    while (this.tiles[j][left] != null && left >= 0) {

                        if (tiles[j][left - 1] == null) {
                            count++;
                            break;
                        }
                        count++;
                        left--;
                    }
                }

                if (count > 1) {
                    if (left != 0) {
                        newTc = left;
                    } else {
                        newTc = col;
                    }
                    tc = newTc;
                    Tile[] t = new Tile[count];
                    for (int i = 0; i < count && newTc < tc + count; i++) {
                        t[i] = this.tiles[j][newTc];
                        newTc++;
                    }
                    Word newWord = new Word(t, j, tc, false);
                    if (newWord != null) {
                        newWords.add(newWord);
                    }

                }

                j++;
                count = 0;
                left = 0;
                right = 0;
            }

        }
        if (!vertical) {
           

            for (Word w : newWords) {

                for (index = 0, tc = col; index < w.getTiles().length && tc < col + w.getTiles().length; index++, tc++) {
                    if (w.getTiles()[index] == null && this.tiles[row][tc] != null) {
                        conTinue = true;
                        j = tc + 1;
                        break;
                       
                    }
                }

            }

            index = 0;
            if (!conTinue) {
                j = col;
            }

            while (this.tiles[row][j] != null && j < SIZE) {

                if (this.tiles[row + 1][j] != null && row + 1 < SIZE) {
                    down = row;
                    while (this.tiles[down][j] != null && down < SIZE) {
                        // count++;
                        if (tiles[down + 1][j] == null) {
                            count++;
                            break;
                        }
                        down++;
                        count++;

                    }
                }
                if (this.tiles[row - 1][j] != null && row - 1 >= 0) {
                    if (down != 0) {
                        up = row - 1;
                    } else {
                        up = row;
                    }
                    while (this.tiles[up][j] != null && up >= 0) {

                        if (tiles[up - 1][j] == null) {
                            count++;
                            break;
                        }
                        count++;
                        up--;
                    }
                }

                if (count > 1) {
                    if (up != 0) {
                        newTr = up;
                    } else {
                        newTr = row;
                    }
                    tr = newTr;
                    Tile[] t = new Tile[count];
                    for (int i = 0; i < count && newTr < tr + count; i++) {
                        t[i] = this.tiles[newTr][j];
                        newTr++;
                    }
                    Word newWord = new Word(t, tr, j, true);
                    if (newWord != null) {
                        newWords.add(newWord);
                    }

                }

                j++;
                count = 0;
                up = 0;
                down = 0;
            }

        }

        return newWords;

    }

    private BonusType getBonusType(int row, int col) {
        String key = row + "," + col;
        if (BonusSlot.containsKey(key)) {
            return BonusSlot.get(key);
        }
        return null;

    }

    public int getScore(Word word) {
        int row = word.getRow();
        int col = word.getCol();
        int lenght = word.getTiles().length;
        int score = 0;
        int MultiWord = 1;
        int baseScore = 0;
        boolean isVertical = word.isVertical();
       
        if (!isVertical) {

            for (int i = 0; i < lenght; i++) {
                if (word.getTiles()[i] == null) {

                    baseScore = this.tiles[row][col + i].getScore();
                } else {
                    baseScore = word.getTiles()[i].getScore();
                }
                
                    BonusType bonus = getBonusType(row, col + i);
                    if (bonus != null) {
                        switch (bonus) {
                            case X2_LETTER_SCORE:
                                baseScore *= 2;
                                break;
                            case X3_LETTER_SCORE:
                                baseScore *= 3;
                                break;
                            case STAR:
                                
                                MultiWord *= 2;
                                BonusSlot.remove("7,7"); 
                                break;

                            case X2_WORD_SCORE:
                                MultiWord *= 2;
                                break;
                            case X3_WORD_SCORE:
                                MultiWord *= 3;
                                break;
                        }
                    }
                

                score += baseScore;

            }
            score *= MultiWord;

        } else {
            for (int i = 0; i < lenght; i++) {
                if (word.getTiles()[i] == null) {

                    baseScore = this.tiles[row + i][col].getScore();
                } else {
                    baseScore = word.getTiles()[i].getScore();
                }
                    BonusType bonus = getBonusType(row + i, col);
                    if (bonus != null) {

                        switch (bonus) {
                            case X2_LETTER_SCORE:
                                baseScore *= 2;
                                break;
                            case X3_LETTER_SCORE:
                                baseScore *= 3;
                                break;
                            case STAR:
                                BonusSlot.remove("7,7");

                                MultiWord *= 2;
                                break;
                            case X2_WORD_SCORE:
                                MultiWord *= 2;
                                break;
                            case X3_WORD_SCORE:
                                MultiWord *= 3;
                                break;
                        }
                        
                    }
                

                score += baseScore;

            }
            score *= MultiWord;

        }
        return score;
    }

    public void placeWord(Word word) {
        int row = word.getRow();
        int col = word.getCol();
        if (!word.isVertical()) {
            int j = 0;
            int c = col;
            while (j < word.getTiles().length && c < col + word.getTiles().length) {
                while (this.tiles[row][c] != null && word.getTiles()[j] == null) {
                    j++;
                    c++;
                }
                this.tiles[row][c] = word.getTiles()[j];
                j++;
                c++;
            }
        } else {
            int i = 0;
            int r = row;
            while (i < word.getTiles().length && r < row + word.getTiles().length) {
                if (this.tiles[r][col] != null&& word.getTiles()[i] == null) {
                    i++;
                    r++;
                }
                this.tiles[r][col] = word.getTiles()[i];
                i++;
                r++;

            }
        }
    }


    public int tryPlaceWord(Word word) {

        if (!boardLegal(word)) {
            return 0;
        }
        
        placeWord(word);
        int finalScore = 0;

        ArrayList<Word> newWords = getWords(word);
        for (Word newWord : newWords) {
            finalScore += getScore(newWord);
        }

        for (Word newWord : newWords) {

            if (!dictionaryLegal(newWord)) {
                return 0;
            }
           
        }
        return finalScore;
    }

}
