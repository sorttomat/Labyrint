import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Labyrint {
    Celle[][] labyrint;
    String[][] fotavtrykk;
    int antRader;
    int antKolonner;
    String filename;

    public Labyrint(String fName) {
        filename = fName;
        lesInnFil();
    }

    public void lesInnFil() {
        try {
            File inputFile = new File(filename);
            Scanner in = new Scanner(inputFile);
            String[] lineOne = in.nextLine().split(" ");

            antRader = Integer.parseInt(lineOne[0]);
            antKolonner = Integer.parseInt(lineOne[1]);
            labyrint = new Celle[antRader][antKolonner];
            fotavtrykk = new String[antRader][antKolonner];

            for (int i = 0; i < antRader; i++) {
                String[] line = in.nextLine().split("");
                for (int j = 0; j < antKolonner; j++) {
                    if (line[j].equals("#")) {
                        labyrint[i][j] = new Celle(i, j, false);
                    } else {
                        labyrint[i][j] = new Celle(i, j, true);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
        }
    }

    private boolean canGoLeft(int fraX, int fraY) {
        return fraX - 1 >= 0 && (labyrint[fraX - 1][fraY].erÅpen());
    }

    private boolean canGoRight(int fraX, int fraY) {
        return fraX + 1 < antRader && (labyrint[fraX + 1][fraY].erÅpen());
    }

    private boolean canGoUp(int fraX, int fraY) {
        return fraY - 1 >= 0 && (labyrint[fraX][fraY - 1].erÅpen());
    }

    private boolean canGoDown(int fraX, int fraY) {
        return fraY + 1 < antKolonner && (labyrint[fraX][fraY + 1].erÅpen());
    }

    private boolean gotOut(int x, int y) {
        return x == 0 || x == antRader - 1 || y == 0 || y == antKolonner - 1;
    }

    public void finnVeien(int startX, int startY) {
        labyrint[startX][startY].settVært();
        fotavtrykk[startX][startY] = "O";
        if (gotOut(startX, startY)) {
            System.out.println("Gratulerer! Du fant veien ut. Vi avsluttet her: " + startX + " " + startY);
            markExitPrint(startX, startY);
        }

        if (canGoRight(startX, startY)) {
            int nyX = startX + 1;
            if (labyrint[nyX][startY].harVært() == false) {
                finnVeien(nyX, startY);
            }
        }

        if (canGoLeft(startX, startY)) {
            int nyX = startX - 1;
            if (labyrint[nyX][startY].harVært() == false) {
                finnVeien(nyX, startY);
            }
        }

        if (canGoUp(startX, startY)) {
            int nyY = startY - 1;
            if (labyrint[startX][nyY].harVært() == false) {
                finnVeien(startX, nyY);
            }
        }

        if (canGoDown(startX, startY)) {
            int nyY = startY + 1;
            if (labyrint[startX][nyY].harVært() == false) {
                finnVeien(startX, nyY);
            }
        }
    }

    public void markExitPrint(int exitX, int exitY) {
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                if (fotavtrykk[i][j] == "O") {
                    System.out.print("O");
                } else if (i == exitX && j == exitY) {
                    System.out.print("X");
                } else if (labyrint[i][j].erÅpen()) {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        String print = "Antall rader: " + antRader + " Antall kolonner: " + antKolonner + "\n";
        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                if (labyrint[i][j].erÅpen()) {
                    print += ".";
                } else {
                    print += "#";
                }
            }
            print += "\n";
        }
        return print;
    }
}