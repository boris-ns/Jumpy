package sound;

import java.util.HashMap;

public class AudioPlayer {

	public static HashMap<String, AudioClip> audioClips;
	
	public AudioPlayer() {
		audioClips = new HashMap<String, AudioClip>();
		
		audioClips.put("Coin", new AudioClip("/coinflipCut.mp3", -10.0f));
		audioClips.put("Punch", new AudioClip("/punch.mp3", -20.0f));
		audioClips.put("Door", new AudioClip("/jail_cell_door.mp3", -10.0f));
		audioClips.put("Hurt", new AudioClip("/hurt.mp3", -10.0f));
		audioClips.put("Heal", new AudioClip("/heal.mp3", -10.0f));
		audioClips.put("BgMusic", new AudioClip("/bgMusic.mp3", -20.0f));
		audioClips.put("Pistol", new AudioClip("/pistol.mp3", -10.0f));
		audioClips.put("FredyGhost", new AudioClip("/ghost.mp3", -10.0f));
		audioClips.put("GameOver", new AudioClip("/gameOver.mp3", -5.0f));
	}
	
	public static boolean play(String audioClipName) {
		if (!audioClips.containsKey(audioClipName)) {
			return false;
		}
		
		audioClips.get(audioClipName).play();
		
		return true;
	}
	
	public static boolean loopPlay(String audioClipName) {
		if (!audioClips.containsKey(audioClipName)) {
			return false;
		}
		
		audioClips.get("BgMusic").loopPlay(-20.0f);
		
		return true;
	}
}
