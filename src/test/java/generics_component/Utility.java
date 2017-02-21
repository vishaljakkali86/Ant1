package generics_component;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class Utility {
	
	public static String getFormatedDateTime()
	{
		SimpleDateFormat dt = new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss");
		return dt.format(new Date());		
	}
	
	
	public static String getScreenshot(WebDriver driver, String imgFolderPath)
	{
		String imgPath = imgFolderPath+"/"+getFormatedDateTime()+".png";
		
		try
		{
			EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
			File srcFile = eDriver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(imgPath));			
		} catch(Exception e)
		{
			e.printStackTrace();
			
		}		
		return imgPath;		
	}

public static void getDesktopScreenshot(String folder)
{
	
	String timeStamp = getFormatedDateTime();
	
	try
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRect = new Rectangle(d);
		
		Robot r = new Robot();
		BufferedImage img = r.createScreenCapture(screenRect);
		
		File output = new File(folder +timeStamp +".png");
		ImageIO.write(img, "png", output);		
		
	}catch (Exception e)
	{
		e.printStackTrace();
	}
  }	

}
