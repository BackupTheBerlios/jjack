package com.petersalomonsen.jjack.javasound;

/*
 * Project: JJack - Java bridge API for the JACK Audio Connection Kit
 * Class:   JJackLine
 * Version: 0.3
 *
 * Date:    2007-04-09
 *
 * Licensed under the GNU Lesser General Public License (LGPL).
 * This comes with NO WARRANTY. See file license.txt for details.
 *
 * Author:  Peter Johan Salomonsen
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Control.Type;

import de.gulden.framework.jjack.JJackSystem;

/**
 * Base class for JJack Lines
 * @author Peter Johan Salomonsen
 *
 */
public abstract class JJackLine implements Line {

	BlockingByteFIFO fifo;
	ByteIntConverter converter;
	AudioFormat format = new AudioFormat(JJackSystem.getSampleRate(),16,2,true,false);

	float[] floatBuffer = null;
	byte[] byteBuffer = null;
	
	protected final void checkAndAllocateBuffers(int length)
	{
		if(floatBuffer == null || floatBuffer.length!=length)
		{
			floatBuffer = new float[length];
			byteBuffer = new byte[length*(format.getSampleSizeInBits()/8)];
		}
	}

	public void addLineListener(LineListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public Control getControl(Type control) {
		// TODO Auto-generated method stub
		return null;
	}

	public Control[] getControls() {
		// TODO Auto-generated method stub
		return null;
	}

	public Info getLineInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isControlSupported(Type control) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public void open() throws LineUnavailableException {
		// TODO Auto-generated method stub
		
	}

	public void removeLineListener(LineListener listener) {
		// TODO Auto-generated method stub
		
	}

	public int getFramePosition() {
		return (int)getLongFramePosition();
	}

	public abstract long getLongFramePosition();
	
	public long getMicrosecondPosition() {
		// TODO Auto-generated method stub
		return (long)(1000000 * (getLongFramePosition() / format.getFrameRate()));
	}

}
