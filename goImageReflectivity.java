//David Norvell
//Advanced Java OCCC Fall 2020 Online
//goImageReflectivity.class
//Final Project "WXProwler"

//...The Beginning of the End, at last.

package WXApp;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.MultiPixelPackedSampleModel;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;


import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;



public class goImageReflectivity extends goNEXRADCrawlerQueryDemo implements Serializable, Runnable
{
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -2037695135256897838L;
	
	//General I/O 
	private byte[] imageData;
	private int WIDTH;
	private int HEIGHT;
	//Image Handling
	private DataBuffer zeBuffer;
	private WritableRaster zeRenderer;
	private ComponentColorModel defaultRGB;
	private BufferedImage tempFrame;
	
	
	public goImageReflectivity()
	{
		super();
		
	}
	
	public goImageReflectivity(goNEXRADCrawlerQueryDemo e)
	{
		super();
		this.awsFinalREQ = super.awsFinalREQ;
		this.path = super.path;
		this.data = super.data;
		this.filtered = super.filtered;
	}
	
	public goImageReflectivity(byte[] z, int x, int y)
	{
		super();
		this.setImageData(z);
		this.WIDTH = x;
		this.HEIGHT = y;
	}
	
	public void setImageData(byte[] x)
	{
		this.imageData = new byte[x.length];
		
	}
	
	public void setWidth(int x)
	{
		this.WIDTH = x;
	}
	
	public void setHeight(int y)
	{
		this.HEIGHT = y;
	}
	

    public void createImage(byte[] x, String imgPath)
	{
		/*
		 * This all works on the assumption that each byte has three bytes per pixel, and the range of values are 0-255
		 * So, some conversion might be needed before calling this. The NOAA Control Documents should be VERY helpful here.
		 * What we would call a "Pixel" they call a "Gate", and thus from which comes terms like "Gate-To-Gate Shear"
		 */
    	
    	this.zeBuffer = new DataBufferByte(x, this.imageData.length);
		this.zeRenderer = Raster.createInterleavedRaster(this.zeBuffer, this.WIDTH, this.HEIGHT, 3*this.WIDTH, 3, new int[] {0,1,2},(Point)null);
	    this.defaultRGB = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE); 
		this.tempFrame = new BufferedImage(this.defaultRGB, this.zeRenderer, true, null);
		try {
		ImageIO.write(this.tempFrame, "png",new File(imgPath));
		}catch(IOException e)
		{
			System.err.println(e);
		}
	}
	
	
	public Graphics displayZeImage()
	{
		return this.tempFrame.getGraphics();
	}

	@Override
	public void run() 
	{
		
		
	}

}
