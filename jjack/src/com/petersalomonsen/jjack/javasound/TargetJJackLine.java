package com.petersalomonsen.jjack.javasound;
/*
 * Project: JJack - Java bridge API for the JACK Audio Connection Kit
 * Class:   TargetJJackLine
 * Version: 0.3
 *
 * Date:    2007-04-09
 *
 * Licensed under the GNU Lesser General Public License (LGPL).
 * This comes with NO WARRANTY. See file license.txt for details.
 *
 * Author:  Peter Johan Salomonsen
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import de.gulden.framework.jjack.JJackSystem;

/**
 * JJack TargetDataLine implementation
 * @author Peter Johan Salomonsen
 *
 */
public class TargetJJackLine extends JJackLine implements TargetDataLine {

	AudioFormat audioFormat = new AudioFormat(JJackSystem.getSampleRate(),16,2,true,false);
	
	public void open() throws LineUnavailableException
	{
		open(null);
	}
	
	public void open(AudioFormat format) throws LineUnavailableException {
		open(format,65536);
		
	}

	public void open(AudioFormat format, int bufferSize) throws LineUnavailableException {
		fifo = new BlockingByteFIFO(bufferSize);
		
	}

	public int read(byte[] b, int off, int len) {
		return fifo.read(b, off, len);
	}

	public int available() {
		return fifo.availableRead();
	}

	public void drain() {
		// TODO Auto-generated method stub
		
	}

	public void flush() {
		// TODO Auto-generated method stub
		
	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public AudioFormat getFormat() {
		return audioFormat;
	}

	public int getFramePosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getLongFramePosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getMicrosecondPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	float[] floatBuffer = null;
	ByteBuffer byteBuffer = null;
	ShortBuffer shortBuffer;
	
	/**
	 * Used by JJackMixer to get a buffer to write float values
	 * @param length
	 * @return
	 */
	float[] getFloatBuffer(int length) {
		if(floatBuffer == null || floatBuffer.length!=length)
		{
			floatBuffer = new float[length];
			byteBuffer = ByteBuffer.allocate(length*2);
			byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
			shortBuffer = byteBuffer.asShortBuffer();
		}	
		return floatBuffer;
	}

	/**
	 * Used by JJackMixer to write the float buffer retrieved using getFloatBuffer()
	 *
	 */
	void writeFloatBuffer()
	{
		for(int n=0;n<floatBuffer.length;n++)
			shortBuffer.put(n,(short)(floatBuffer[n]*32768));
		
		fifo.write(byteBuffer.array(), 0, byteBuffer.capacity());
	}
	
	boolean canWriteFloat(int length)
	{
		return fifo.availableWrite() >= length*2;
	}

}
