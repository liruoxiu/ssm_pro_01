package com.lrx.Common;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Captcha {
	private BufferedImage bufferImg;
	private Graphics2D g;
	private int CAPTCHA_WIDTH;
	private int CAPTCHA_HEIGHT;
	public Captcha(int Width, int Height)
	{
		CAPTCHA_WIDTH = Width;
		CAPTCHA_HEIGHT = Height;
		bufferImg = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
		g = bufferImg.createGraphics();
	}
	public BufferedImage GetCaptcha(String value)
	{
		SetBackground();
		SetText(value);
		return this.bufferImg;
	}
	private void SetBackground()
	{
		Random ro = new Random();
		Color sC = new Color(ro.nextInt(255),ro.nextInt(255), ro.nextInt(255));
		Color eC = new Color(ro.nextInt(255),ro.nextInt(255), ro.nextInt(255));
		//GradientPaint paint = new GradientPaint(0, 0, Color.WHITE, CAPTCHA_WIDTH, CAPTCHA_HEIGHT, Color.GRAY);
		GradientPaint paint = new GradientPaint(0, 0, sC, CAPTCHA_WIDTH, CAPTCHA_HEIGHT, eC);
        g.setPaint(paint);
        g.fillRect(0, 0, CAPTCHA_WIDTH, CAPTCHA_HEIGHT);
	}
	private void SetText(String value)
	{
		Random ro = new Random();
		g.setFont(new Font("黑体", Font.BOLD, CAPTCHA_HEIGHT));
		int strX = 5, strY = CAPTCHA_HEIGHT - 5;
		for(int i = 0; i < value.length(); i++)
		{
			Color roColor = new Color(ro.nextInt(255),ro.nextInt(255), ro.nextInt(255));
			String str = String.valueOf(value.charAt(i));
			PaintText(str, strX, strY, ro.nextInt(15) - ro.nextInt(15), roColor);
			strX += CAPTCHA_HEIGHT / 2;
		}
	}
	private void PaintText(String val, int x, int y, int rotate, Color color)
	{
        g.rotate(rotate * (Math.PI / 180), CAPTCHA_HEIGHT / 2 + x, CAPTCHA_HEIGHT / 2 + y);
        g.setColor(Color.BLACK);
        g.drawString(val, x + 1.5f, y + 1.5f);
        
        g.setColor(color);
        g.drawString(val, x, y);
        g.rotate((0 - rotate) * (Math.PI / 180), CAPTCHA_HEIGHT / 2 + x, CAPTCHA_HEIGHT / 2 + y);
	}
}