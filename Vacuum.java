public class Vacuum {
    private int rreshti = 0;
    private int shtylla = 0;
    private int totali = -1000;
    private int[] pengesat = new int[100];
    private boolean numro = false;
    private int kontrolloPastrimin = 0;

    public static int random() {
        return (int) (Math.random() * 100);
    }

    public boolean kontrollo(int c) {

        boolean rez = false;
        if (c == 0 || c == 2) {
            rez = true;
        }
        return rez;
    }

    public int[][] vendos(int a[][], int u) {
        for (int i = 0; i < 33; i++) {
            while (true) {
                int e = random();
                if (a[e / 10][e % 10] == 0 && e != 0) {
                    a[e / 10][e % 10] = u;
                    break;
                }
            }
        }
        return a;
    }

    public static boolean KontrolloMurin(int i, int j) {
        boolean rez = false;
        if (i < 0 || i > 9 || j < 0 || j > 9) {
            rez = true;
        }
        return rez;
    }

    public boolean Djathtas(int[][] a, int random) {
        for (int j = shtylla; j <= random; j++) {
            if (kontrollo(a[rreshti][j])) {
                if (a[rreshti][j] == 2) {
                    a[rreshti][j] = 0;
                    totali += 10;
                    kontrolloPastrimin += 1;
                    numro = false;
                } else {
                    numro = true;
                }
                shtylla = j;
            } else {
                numro = true;
                ruajPengesat(rreshti, j);
                return false;
            }
        }
        return true;
    }

    public boolean Majtas(int[][] a, int random) {
        for (int j = shtylla; j >= random; j--) {
            if (kontrollo(a[rreshti][j])) {
                if (a[rreshti][j] == 2) {
                    a[rreshti][j] = 0;
                    totali += 10;
                    kontrolloPastrimin += 1;
                    numro = false;
                } else {
                    numro = true;
                }
                shtylla = j;
            } else {
                numro = true;
                ruajPengesat(rreshti, j);
                return false;
            }
        }
        return true;
    }

    public boolean Nalt(int[][] a, int random) {
        for (int i = rreshti; i >= random; i--) {
            if (kontrollo(a[i][shtylla])) {
                if (a[i][shtylla] == 2) {
                    a[i][shtylla] = 0;
                    totali += 10;
                    kontrolloPastrimin += 1;
                    numro = false;
                } else {
                    numro = true;
                }
                rreshti = i;
            } else {
                numro = true;
                ruajPengesat(i, shtylla);
                return false;
            }
        }
        return true;
    }

    public boolean Posht(int[][] a, int random) {
        for (int i = rreshti; i <= random; i++) {
            if (kontrollo(a[i][shtylla])) {
                if (a[i][shtylla] == 2) {
                    a[i][shtylla] = 0;
                    totali += 10;
                    kontrolloPastrimin += 1;
                    numro = false;
                } else {
                    numro = true;
                }
                rreshti = i;
            } else {
                numro = true;
                ruajPengesat(i, shtylla);
                return false;
            }
        }
        return true;
    }

    public boolean kontrolloPengesen(int m, int n) {
        boolean rez = false;
        int i = m * 10 + n;
        if (pengesat[i] == i && i != 0)
            rez = true;

        return rez;
    }

    public void ruajPengesat(int i, int j) {
        if (!kontrolloPengesen(i, j)) {
            int k = i * 10 + j;
            pengesat[k] = k;
        }
    }

    public int[][] hec(int a[][]) {
        Begin:
        for (int i = 0; i < 1500; i++) {
            int u = (int) (Math.random() * 4);
            int s = (int) (Math.random() * 4) + 1;

            if ((a[0][1] == a[1][0] && a[1][0] == 1) || (a[0][2] == a[1][1] && a[1][1] == a[2][0] && a[2][0] == 1)) {
                break Begin;
            }
            if (u == 0) {
                int k = shtylla + s;
                if (!KontrolloMurin(rreshti, k)) {
                    Djathtas(a, k);
                    if (numro)
                        totali -= 1;
                }
            } else if (u == 1) {
                int k = s + rreshti;
                if (!KontrolloMurin(k, shtylla)) {
                    Posht(a, k);
                    if (numro)
                        totali -= 1;
                }
            } else if (u == 2) {
                int k = shtylla - s;
                if (!KontrolloMurin(rreshti, k)) {
                    Majtas(a, k);
                    if (numro)
                        totali -= 1;
                }
            } else {
                int k = rreshti - s;
                if (!KontrolloMurin(k, shtylla)) {
                    Nalt(a, k);
                    if (numro)
                        totali -= 1;
                }
            }
            if (kontrolloPastrimin == 33) {
                System.out.println("U patrua pas " + i + " iterimeve");
                break Begin;
            }
        }
        return a;
    }

    public void kthehu(int a[][]) {
        Begin:
        for (int i = 0; i < 1000; i++) {
            int r = (int) (Math.random() * 6);
            int s = (int) (Math.random() * 3);

            if (rreshti == 0 && shtylla == 1) {
                r = 5;
            }
            if (rreshti == 1 && shtylla == 0) {
                r = 3;
            }
            if (rreshti < 3 && shtylla < 3) {
                s = 1;
            }
            if (r == 0) {
                if (!KontrolloMurin(rreshti + s, shtylla)) {
                    if (!kontrolloPengesen(rreshti + s, shtylla)) {
                        Posht(a, rreshti + s);
                    }
                }
            } else if (r == 1) {
                if (!KontrolloMurin(rreshti, shtylla + s)) {
                    if (!kontrolloPengesen(rreshti, shtylla + s)) {
                        Djathtas(a, shtylla + s);
                    }
                }
            } else if (r == 2 || r == 3) {
                if (!KontrolloMurin(rreshti - s, shtylla)) {
                    if (!kontrolloPengesen(rreshti - s, shtylla)) {
                        Nalt(a, rreshti - s);
                    }
                }
            } else {
                if (!KontrolloMurin(rreshti, shtylla - s)) {
                    if (!kontrolloPengesen(rreshti, shtylla - s)) {
                        Majtas(a, shtylla - s);
                    }
                }
            }
            if (shtylla == 0 && rreshti == 0) {
                System.out.println("U kthye ne vend me " + i + " iterime");
                totali -= i;
                totali += 1000;
                break Begin;
            }
        }
    }

    public static void main(String[] args) {
        int p = 0;
        int m = 0;
        int n = 0;
        Vacuum v = new Vacuum();
        int a[][] = new int[10][10];
        a = v.vendos(a, 1);
        a = v.vendos(a, 2);
        System.out.println("Dhoma :");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        int e[][] = v.hec(a);
        System.out.println("Dhoma e fshire");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(e[i][j] + " ");
            }
            System.out.println();
        }
        v.kthehu(e);
        System.out.println(" U kthye ne Rreshtin " + v.rreshti + " dhe ne Shtyllen " + v.shtylla);
        System.out.println("Totali " + v.totali);

        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e.length; j++) {
                if (e[i][j] == 1) {
                    p++;
                } else if (e[i][j] == 2) {
                    m++;
                } else {
                    n++;
                }
            }
        }
        System.out.println("Pengesa =" + p + " Mbeturina= " + m + " ,null= " + n);
    }
}