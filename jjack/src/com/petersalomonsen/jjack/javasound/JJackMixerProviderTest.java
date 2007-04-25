package com.petersalomonsen.jjack.javasound;

/*
 * Project: JJack - Java bridge API for the JACK Audio Connection Kit
 * Class:   JJackMixerProviderTest
 * Version: 0.3
 *
 * Date:    2007-04-09
 *
 * Licensed under the GNU Lesser General Public License (LGPL).
 * This comes with NO WARRANTY. See file license.txt for details.
 *
 * Author:  Peter Johan Salomonsen
 */

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.Mixer.Info;

/**
 * Simple test that reads the input and writes to the output - the default buffer size of 64KB will create a delay effect
 * 
 * @author Peter Johan Salomonsen
 *
 */
public class JJackMixerProviderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// Scan mixers
		Info jackMixerInfo = null;
		
		for(Info info : AudioSystem.getMixerInfo())
		{
			if(info.getName().equals("JJack"))
				jackMixerInfo = info;
		}
		
		Mixer mixer = AudioSystem.getMixer(jackMixerInfo);
		SourceDataLine lineOut = (SourceDataLine) mixer.getLine(mixer.getSourceLineInfo()[0]);
		lineOut.open();
		lineOut.start();
		TargetDataLine lineIn = (TargetDataLine) mixer.getLine(mixer.getTargetLineInfo()[0]);
		lineIn.open();
		lineIn.start();
		
		byte[] buf = new byte[128];
		while(true)
		{
			lineIn.read(buf,0,buf.length);
			lineOut.write(buf,0,buf.length);
		}
			
	}
}
