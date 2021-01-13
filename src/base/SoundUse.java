package base;

import javafx.scene.media.AudioClip;

public class SoundUse {
	public static AudioClip mouseClick = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "MouseClickSoundEff.mp3").toString());
	public static AudioClip mouseEnter = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "MouseOverSoundEff.mp3").toString());
	public static AudioClip mouseEnterDice = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "MouseOverSoundEff2.mp3").toString());
	public static AudioClip rollDice = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "Shake And Roll Dice-SoundBible.com-591494296.mp3").toString());
	public static AudioClip dealSkillCard = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "DealCardShort.mp3").toString());
	public static AudioClip diceClick = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "trained.mp3").toString());
	public static AudioClip useSkill = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "useskill.mp3").toString());
	public static AudioClip startSound = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "startSound.mp3").toString());
	public static AudioClip playingSound = new AudioClip(
			ClassLoader.getSystemResource("sound/" + "david-luong_wet-martini.mp3").toString());
	public static AudioClip endingSound = new AudioClip(ClassLoader
			.getSystemResource("sound/" + "david-luong_photographs-of-you-signature-edition.mp3").toString());

	public SoundUse() {
		mouseClick.setVolume(0.3);
		mouseEnter.setVolume(0.3);
		mouseEnterDice.setVolume(0.3);
		startSound.setVolume(0.2);
		playingSound.setVolume(0.3);
		endingSound.setVolume(0.5);
		useSkill.setVolume(0.3);
		diceClick.setVolume(0.3);
		rollDice.setVolume(0.3);
		dealSkillCard.setVolume(0.3);
	}
}
