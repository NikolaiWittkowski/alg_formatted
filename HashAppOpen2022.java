package formatted;

import java.util.Arrays;

public class HashAppOpen2022 {
    int[] hashTabelle;
    final int B = 7;
    final int LEER = -1;
    final int GELOESCHT = -2;

    public HashAppOpen2022() {
        hashTabelle = new int[B];
        Arrays.fill(hashTabelle, LEER);
    }

    int hashFunktion(int x, int i, int b) {
        return (Math.abs(x) + i) % b;
    }

    void einfuegen(int x) {
        for (int i = 0; i < hashTabelle.length; i++) {
            int index = hashFunktion(x, i, hashTabelle.length);
            if (hashTabelle[index] == LEER || hashTabelle[index] == GELOESCHT) {
                hashTabelle[index] = x;
                System.out.println("Element " + x + " eingefügt an Position " + index);
                return;
            }
        }
        System.out.println("Tabelle ist voll, Einfügen nicht möglich");
    }

    void loeschen(int x) {
        for (int i = 0; i < hashTabelle.length; i++) {
            int index = hashFunktion(x, i, hashTabelle.length);
            if (hashTabelle[index] == x) {
                hashTabelle[index] = GELOESCHT;
                System.out.println("Element " + x + " gelöscht von Position " + index);
                return;
            } else if (hashTabelle[index] == LEER) {
                System.out.println("Element " + x + " nicht gefunden");
                return;
            }
        }
        System.out.println("Element " + x + " nicht gefunden");
    }

    boolean suchen(int x) {
        for (int i = 0; i < hashTabelle.length; i++) {
            int index = hashFunktion(x, i, hashTabelle.length);
            if (hashTabelle[index] == x) {
                System.out.println("Element " + x + " gefunden an Position " + index);
                return true;
            } else if (hashTabelle[index] == LEER) {
                break;
            }
        }
        System.out.println("Element " + x + " nicht gefunden");
        return false;
    }

    public static void main(String[] args) {
        HashAppOpen2022 hashApp = new HashAppOpen2022();
        hashApp.einfuegen(8);
        hashApp.einfuegen(49);
        hashApp.einfuegen(42);
        hashApp.einfuegen(1);
        hashApp.suchen(42);
        hashApp.loeschen(42);
        hashApp.suchen(42);
    }
}
