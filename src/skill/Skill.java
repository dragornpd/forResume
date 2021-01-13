package skill;

public abstract class Skill {
	public static boolean enable = true;
	private boolean isUsed = false;
	private String skillName;

	public Skill(String skillName) {
		this.setSkillName(skillName);
	}

	public abstract void activateMySkill();

	// ----------------------------------------------Getter&Setter-------------------------------------------

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		Skill.enable = enable;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
