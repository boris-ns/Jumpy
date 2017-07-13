package sound;

import java.io.IOException;
import javax.sound.sampled.*;

public class AudioClip 
{
	private Clip clip;
	private float volume;
	
	public AudioClip(String path, float volume) {
		try 
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			
			//Decoded ais
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
		} 
		catch (UnsupportedAudioFileException e) 
		{		
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		catch (LineUnavailableException e) 
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		this.volume = volume;
	}
	
	public void play(float volume) {
		playClip(volume);
		clip.start();
	}
	
	public void play() {
		playClip(this.volume);
		clip.start();
	}
	
	public void loopPlay(float volume) {
		playClip(volume);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		if(clip.isRunning())
			clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
	// Metoda koja sluzi za podesavanje jacine zvuka sound effect-a
	private void playClip(float volume) {
		if(clip == null)
			return;
		
		stop();
		
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(volume);
		clip.setFramePosition(0);
	}
}
