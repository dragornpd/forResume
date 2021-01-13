package gui;

import java.util.HashMap;

import application.Main;
import base.SoundUse;
import javafx.scene.layout.HBox;

import object.Casino;

public class CasinoPane extends HBox {
	private CasinoInformation Casino1 = new CasinoInformation(Main.casinoList.get(0));
	private CasinoInformation Casino2 = new CasinoInformation(Main.casinoList.get(1));
	private CasinoInformation Casino3 = new CasinoInformation(Main.casinoList.get(2));
	private CasinoInformation Casino4 = new CasinoInformation(Main.casinoList.get(3));
	private CasinoInformation Casino5 = new CasinoInformation(Main.casinoList.get(4));
	private CasinoInformation Casino6 = new CasinoInformation(Main.casinoList.get(5));
	public static HashMap<Casino, CasinoInformation> casinoInHash = new HashMap<Casino, CasinoInformation>();

	//pack all casino information pane into one pane//
	public CasinoPane() {
		this.setPrefSize(1200, 250);
		this.getChildren().addAll(this.Casino1, this.Casino2, this.Casino3, this.Casino4, this.Casino5, this.Casino6);
		CasinoPane.casinoInHash.put(Main.casinoList.get(0), Casino1);
		CasinoPane.casinoInHash.put(Main.casinoList.get(1), Casino2);
		CasinoPane.casinoInHash.put(Main.casinoList.get(2), Casino3);
		CasinoPane.casinoInHash.put(Main.casinoList.get(3), Casino4);
		CasinoPane.casinoInHash.put(Main.casinoList.get(4), Casino5);
		CasinoPane.casinoInHash.put(Main.casinoList.get(5), Casino6);

	}

	//highlight with gold every casino but only mouseenter get it red
	public static void highlight() {
		for (Casino casino : Main.casinoList) {
			CasinoPane.casinoInHash.get(casino).highlight();
			CasinoPane.casinoInHash.get(casino).setOnMouseEntered(event -> {
				SoundUse.mouseEnter.play();
				CasinoPane.casinoInHash.get(casino).pickedhighlight();
			});
			CasinoPane.casinoInHash.get(casino).setOnMouseExited(event -> {
				CasinoPane.casinoInHash.get(casino).highlight();
			});
		}
	}

	// -----------------------------------getter$setter----------------------------------
	public CasinoInformation getCasino1() {
		return Casino1;
	}

	public void setCasino1(CasinoInformation casino1) {
		Casino1 = casino1;
	}

	public CasinoInformation getCasino2() {
		return Casino2;
	}

	public void setCasino2(CasinoInformation casino2) {
		Casino2 = casino2;
	}

	public CasinoInformation getCasino3() {
		return Casino3;
	}

	public void setCasino3(CasinoInformation casino3) {
		Casino3 = casino3;
	}

	public CasinoInformation getCasino4() {
		return Casino4;
	}

	public void setCasino4(CasinoInformation casino4) {
		Casino4 = casino4;
	}

	public CasinoInformation getCasino5() {
		return Casino5;
	}

	public void setCasino5(CasinoInformation casino5) {
		Casino5 = casino5;
	}

	public CasinoInformation getCasino6() {
		return Casino6;
	}

	public void setCasino6(CasinoInformation casino6) {
		Casino6 = casino6;
	}

}
