package object;

import skill.CharacterSkill;

//contain all character in this game//
public class CharacterList {
	public static Character[] characterList = { new Character("1", new CharacterSkill("plus_10000"), 1),
			new Character("2", new CharacterSkill("sub_10000"), 2), new Character("3", new CharacterSkill("reDeal"), 3),
			new Character("4", new CharacterSkill("addOneDice"), 4),
			new Character("5", new CharacterSkill("add_8000"), 5),
			new Character("6", new CharacterSkill("subtract_8000"), 6),
			new Character("7", new CharacterSkill("steal_4000"), 7),
			new Character("8", new CharacterSkill("stealAll"), 8) };

	public CharacterList() {
	}
}
