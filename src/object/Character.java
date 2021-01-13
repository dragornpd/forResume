package object;

import javafx.scene.image.Image;
import skill.CharacterSkill;

public class Character {
	private String charaterName;
	private CharacterSkill characterSkill;
	private int characterID;
	private Image imageIcon;

	public Character(String characterName, CharacterSkill characterSkill, int charaterID) {
		this.setCharaterName(characterName);
		this.setCharacterSkill(characterSkill);
		this.setCharacterID(charaterID);
		imageIcon = new Image(
				ClassLoader.getSystemResource("img/" + "CharacterIcon" + this.getCharacterID() + ".png").toString());
	}
	
	//call this method to use skill
	public void useSkill() {
		this.getCharacterSkill().activateMySkill();
	}
	//---------------------------------Getter&Setter-------------------------------//
	public String getCharaterName() {
		return charaterName;
	}

	public void setCharaterName(String charaterName) {
		this.charaterName = charaterName;
	}

	public CharacterSkill getCharacterSkill() {
		return characterSkill;
	}

	public void setCharacterSkill(CharacterSkill characterSkill) {
		this.characterSkill = characterSkill;
	}

	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	public Image getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(Image imageIcon) {
		this.imageIcon = imageIcon;
	}

}
