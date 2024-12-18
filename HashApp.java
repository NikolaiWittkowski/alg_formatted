package formatted;

import javax.swing.*; 

public class HashApp {
    public static void main(String args[]) {
        final int bucketAnzahl = 5; 
        String menue = "Eingabe\n"; 
        menue += " 1) Element einfuegen\n";
        menue += " 2) Element suchen\n";
        menue += " 3) Zelle loeschen\n";
        menue += " 4) Laenge der Liste von Korb k\n";
        menue += " 5) Liste von Korb k ansehen\n";
        menue += " 6) Laenge der Listen von allen Koerben\n";
        menue += " 7) Liste zufaellig fuellen\n";

        String ausgabe = ""; 
        Liste hashTabelle[]; 
        hashTabelle = new Liste[bucketAnzahl]; 
        for (int i = 0; i < bucketAnzahl; i++) {
            hashTabelle[i] = new Liste(); 
        }

        while (true) { 
            String menueeingabe = JOptionPane.showInputDialog(menue); 
            if (menueeingabe == null) 
                break;
            String dialogEingabe; 

            if (menueeingabe.equals("1")) {
                dialogEingabe = JOptionPane.showInputDialog("Datenstring?");      
                int bucketIndex = hashTabelle[0].hashFunktion(dialogEingabe, bucketAnzahl); 
                boolean existsInHashtable = false; 
                for (int i = 1; i <= hashTabelle[bucketIndex].laenge(); i++) { 
                    if (hashTabelle[bucketIndex].inhalt(i).equals(dialogEingabe)) { 
                        ausgabe = "Element in Korb " + bucketIndex + " gefunden, daher nicht eingefuegt";
                        existsInHashtable = true;
                        break;
                    }
                }

                if (!existsInHashtable) {
                    hashTabelle[bucketIndex].einsetzenAnfang(dialogEingabe); 
                    ausgabe = "Eingabe eingefuegt in bucket " + bucketIndex;
                }
                JOptionPane.showMessageDialog(null, ausgabe); 
            }

            if (menueeingabe.equals("2")) {
                dialogEingabe = JOptionPane.showInputDialog("Welches Element soll gesucht werden?");
                ausgabe = "Element nicht gefunden.";
                for (int i = 0; i < bucketAnzahl; i++) { 
                    for (int j = 1; j <= hashTabelle[i].laenge(); j++) {
                        if (hashTabelle[i].inhalt(j).equals(dialogEingabe)) {
                            ausgabe = "Element gefunden in Bucket " + i;
                            break;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("3")) {
                dialogEingabe = JOptionPane.showInputDialog("Welches Element soll geloescht werden?");
                int h = hashTabelle[0].hashFunktion(dialogEingabe, bucketAnzahl);
                boolean gefunden = false; 
                for (int j = 1; j <= hashTabelle[h].laenge(); j++) {
                    if (hashTabelle[h].inhalt(j).equals(dialogEingabe)) {
                        hashTabelle[h].loesche(j);
                        ausgabe = "Element ist von " + h + ". Korb gelöscht!";
                        gefunden = true;
                        break; 
                    }
                }
                if (!gefunden) {
                    ausgabe = "Element nicht gefunden!";
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("4")) {
                dialogEingabe = JOptionPane.showInputDialog("Von welchem Korb soll die Laenge angezeigt werden?");
                int inputBucketIndex = Integer.parseInt(dialogEingabe); 
                int l = hashTabelle[inputBucketIndex].laenge(); 
                ausgabe = "Liste hat Länge " + l;
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("5")) {
                dialogEingabe = JOptionPane.showInputDialog("Von welchem Korb sollen Elemente angezeigt werden?");
                int bucketIndex = Integer.parseInt(dialogEingabe); 
                ausgabe = "Bucket " + bucketIndex + " enthält:\n";
                for (int i = 1; i <= hashTabelle[bucketIndex].laenge(); i++) { 
                    ausgabe += hashTabelle[bucketIndex].inhalt(i) + "\n"; 
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("6")) {
                ausgabe = "Längen der Buckets:\n";
                for (int i = 0; i < bucketAnzahl; i++) {
                    ausgabe += "Bucket " + i + ": " + hashTabelle[i].laenge() + "\n";
                }
                JOptionPane.showMessageDialog(null, ausgabe);
            }

            if (menueeingabe.equals("7")) {
                dialogEingabe = JOptionPane.showInputDialog("Anzahl?");
                int zahl = Integer.parseInt(dialogEingabe); 
                for (int j = 1; j <= zahl; j++) {
                    String s = ""; 
                    int laenge = (int) ((Math.random() * 8) + 3); 
                    for (int k = 1; k <= laenge; k++) {
                        char zufall = (char) ((Math.random() * 26) + 65); 
                        s += zufall; 
                    }
                    int h = hashTabelle[0].hashFunktion(s, bucketAnzahl); 
                    hashTabelle[h].einsetzenAnfang(s); 
                }
            }
        }
    }
}

//1. Die gegeben hashfunktion gibt b-1 zurück, was bedeutet das elemente immer in den letzten bucket gesetzt werden
//2. die random generierten Strings sind sind strings bestehend aus großbuchstaben von A-Z, der String kann eine länge von 3 bis 10 zeichen haben
//3. Die neue hashfunction verteilt die elemente gleichmäßiger und nicht wie bei der alten funktion immer zum letzten bucket.
//Vorteile: Bessere performance
