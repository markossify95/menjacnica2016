package menjacnica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;


public interface MenjacnicaInterface {
	
	public void dodajValutu(Valuta valuta);
	public void obrisiValutu(Valuta valuta);
	public double izvrsiTransakciju(Valuta valuta, boolean prodaja, double iznos);
	public LinkedList<Valuta> vratiKursnuListu();
	
	public void ucitajIzFajla(String putanja) throws FileNotFoundException, ClassNotFoundException, IOException;
	public void sacuvajUFajl(String putanja) throws FileNotFoundException, IOException;

}
