package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {

	private static MenjacnicaGUI glavni;
	private static MenjacnicaInterface menjacnica;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menjacnica = new Menjacnica();
					glavni = new MenjacnicaGUI();
					glavni.setVisible(true);
					glavni.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void otvoriDodajKursGUI() {
		DodajKursGUI dodajKursProzor = new DodajKursGUI();
		dodajKursProzor.setVisible(true);
		dodajKursProzor.setLocationRelativeTo(null);
	}

	public static void otvoriObrisiKursGUI(Valuta v) {

		ObrisiKursGUI obrisiKursProzor = new ObrisiKursGUI(v);
		obrisiKursProzor.setVisible(true);
		obrisiKursProzor.setLocationRelativeTo(null);
	}

	public static void otvoriIzvrsiIzmenuGUI(Valuta v) {
		IzvrsiZamenuGUI izvrsiIzmenuProzor = new IzvrsiZamenuGUI(v);
		izvrsiIzmenuProzor.setVisible(true);
		izvrsiIzmenuProzor.setLocationRelativeTo(null);
	}

	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(glavni.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				menjacnica.ucitajIzFajla(file.getAbsolutePath());
				glavni.prikaziSveValute();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavni.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(glavni.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				menjacnica.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavni.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void prikaziSveValute() {
		glavni.prikaziSveValute();
	}

	public static List<Valuta> vratiKursnuListu() {
		return menjacnica.vratiKursnuListu();
	}

	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(glavni.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak", JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public static void prikaziAboutProzor() {
		JOptionPane.showMessageDialog(glavni.getContentPane(), "Autor: Bojan Tomic, Verzija 1.0",
				"O programu Menjacnica", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void obrisiValutu(Valuta v) {
		try {
			menjacnica.obrisiValutu(v);
			GUIKontroler.prikaziSveValute();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavni.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void dodajValutu(Valuta valuta) {
		menjacnica.dodajValutu(valuta);
		GUIKontroler.prikaziSveValute();
	}

	public static double izvrsiZamenu(Valuta valuta, boolean selected, double iznos) {
		double konacniIznos = 0;
		try {
			konacniIznos = menjacnica.izvrsiTransakciju(valuta, selected, iznos);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavni.getContentPane(), e1.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
		}
		return konacniIznos;
	}
}
