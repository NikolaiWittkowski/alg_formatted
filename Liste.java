// Liste
package formatted;

public class Liste {
    Zelle anfang;
    Zelle cursor;

    int laenge() {
        Zelle cur = anfang; 
        int l = 0; 
        while (cur != null) { 
            l++; 
            cur = cur.next; 
        }
        return l; 
    }

    /* int hashFunktion (String x,int b ) { // konstante hashfunktion
       return b-1; // unabhängig vom eingabestring x gibt es immer b-1 zurück
    }*/ // somit werden alle elemente in den gleichen bucket gefügt

    int hashFunktion(String x, int b) { // polynomiales hashing
        int hash = 0; 
        for (int i = 0; i < x.length(); i++) { 
            hash = (hash * 31 + x.charAt(i)) % b; // primzahl 31 für gleichmäßigere verteilung
        }
        return hash;
    }

    boolean istGueltigePosition(int p) {
        return (p >= 1) && (p <= laenge()); 
    }

    void setzeCursor(int p) { 
        cursor = null; 
        if (istGueltigePosition(p)) { 
            Zelle cur = anfang; 
            for (int i = 1; i < p; i++) 
                cur = cur.next;
            cursor = cur; 
        }
    }

    void einsetzenAnfang(String e) {
        Zelle z = new Zelle(e, anfang); 
        anfang = z; 
    }

    void loesche(int p) {
        if (istGueltigePosition(p)) { 
            if (p == 1) 
                anfang = anfang.next;
            else { 
                setzeCursor(p - 1); 
                cursor.next = cursor.next.next;
            }
        }
    }

    void loescheElem(String e) {
        for (int i = 1; i <= laenge(); i++) { 
            if (inhalt(i).equals(e)) 
                loesche(i); 
        } 
    }

    String inhalt(int p) {
        setzeCursor(p); 
        return cursor.inhalt; 
    }
}
