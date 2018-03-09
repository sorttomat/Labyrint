class TestLabyrint {
    public static void main(String[] args) {
        Labyrint labyrint = new Labyrint("labyrint5.txt");
        System.out.println(labyrint);
        labyrint.finnVeien(1,1);
    }
}