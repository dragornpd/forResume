package card;

import skill.CardSkill;

public class SkillCard{
	private String name;
	private CardSkill cardSkill;

	public SkillCard(String name, CardSkill cardSkill) {
		this.setName(name);
		this.setCardSkill(cardSkill);
	}

	public void useSkill() {
		this.getCardSkill().activateMySkill();
	}

	//-------------------------GETTER&SETTER----------------------------//
	public CardSkill getCardSkill() {
		return cardSkill;
	}

	public void setCardSkill(CardSkill cardSkill) {
		this.cardSkill = cardSkill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
