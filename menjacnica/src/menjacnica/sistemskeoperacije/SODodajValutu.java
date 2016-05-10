package menjacnica.sistemskeoperacije;

import java.util.LinkedList;

import menjacnica.Valuta;

public class SODodajValutu {

	public static void izvrsi(Valuta v, LinkedList<Valuta> kursnaLista) {
		if (v == null || kursnaLista.contains(v)) {
			throw new RuntimeException("Greska! Neuspesan unos valute.");
		} else {
			kursnaLista.add(v);
		}
	}
}
