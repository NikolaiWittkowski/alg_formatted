package formatted;

import javax.swing.JOptionPane;

public class HashOpenApp2018 {
    static String hashTabelle[];

    static int hashFunktion(String x, int b) {
        return Math.abs(x.hashCode() % b);
    }

    public static void main(String[] args) {
        final int B = 5;
        hashTabelle = new String[B];

        String menue = "Eingabe\n";
        menue += " 1) Element einfuegen\n";
        menue += " 2) Element suchen\n";
        menue += " 3) Zelle loeschen\n";

        while (true) {
            String menueeingabe = JOptionPane.showInputDialog(menue);
            if (menueeingabe == null) break;

            if (menueeingabe.equals("1")) {
                einfuegen();
            } else if (menueeingabe.equals("2")) {
                suchen(B);
            } else if (menueeingabe.equals("3")) {
                loeschen(B);
            } else {
                JOptionPane.showMessageDialog(null, "Ungueltige Auswahl");
            }
        }
    }

    static void einfuegen() {
        String dialogEingabe = JOptionPane.showInputDialog("Datenstring?");
        if (dialogEingabe == null)
            return;

        int h = hashFunktion(dialogEingabe, hashTabelle.length);
        for (int i = 0; i < hashTabelle.length; i++) {
            int index = (h + i) % hashTabelle.length; // Lineares Sondieren
            if (hashTabelle[index] == null) {
                hashTabelle[index] = dialogEingabe;
                JOptionPane.showMessageDialog(null, "String eingefuegt in Bucket " + index);
                return;
            } else if (hashTabelle[index].equals(dialogEingabe)) {
                JOptionPane.showMessageDialog(null, "Element bereits vorhanden in Bucket " + index);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Tabelle ist voll, Einfuegen nicht moeglich");
    }

    static void suchen(int B) {
        String dialogEingabe = JOptionPane.showInputDialog("Welches Element soll gesucht werden?");
        if (dialogEingabe == null)
            return;

        int h = hashFunktion(dialogEingabe, B);
        for (int i = 0; i < B; i++) {
            int index = (h + i) % B; // Lineares Sondieren
            if (hashTabelle[index] == null) break;
            if (hashTabelle[index].equals(dialogEingabe)) {
                JOptionPane.showMessageDialog(null, "Element gefunden in Bucket " + index);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Element nicht gefunden");
    }

    static void loeschen(int B) {
        String dialogEingabe = JOptionPane.showInputDialog("Welches Element soll geloescht werden?");
        if (dialogEingabe == null) return;

        int h = hashFunktion(dialogEingabe, B);
        for (int i = 0; i < B; i++) {
            int index = (h + i) % B; // Lineares Sondieren
            if (hashTabelle[index] == null) break;
            if (hashTabelle[index].equals(dialogEingabe)) {
                hashTabelle[index] = null;
                JOptionPane.showMessageDialog(null, "Element geloescht aus Bucket " + index);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Element nicht gefunden");
    }
}
