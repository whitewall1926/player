package com.main;

import javax.swing.*;


import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;
import java.io.IOException;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class MyMain {
	
	public static void main (String []args)  {
		new NativeDiscovery().discover();
        //System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		JFrame videoFrame = new JFrame ("�ҵĴ���");
		videoFrame.setSize (1000, 500);
		videoFrame.setLocationRelativeTo (null);
		videoFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		//�����Ƶ������
		Container contentPane = videoFrame.getContentPane ();
		contentPane.setLayout (new BorderLayout());
		
		//����һ��������
		EmbeddedMediaPlayerComponent player = new EmbeddedMediaPlayerComponent ();
		contentPane.add (player, BorderLayout.CENTER);
		
		//添加按钮播放暂停停止
		JButton butPlay = new JButton ("播放");
		butPlay.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				player.getMediaPlayer().play();
			}
		});
		JButton butStop = new JButton ("停止");
		butStop.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				player.getMediaPlayer().stop();
			}
		});
		JButton butPause = new JButton ("暂停");
		butPause.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				player.getMediaPlayer().pause();
			}
		});
		JPanel  bottomPane  = new JPanel ();
		bottomPane.add (butPlay);
		bottomPane.add (butStop);
		bottomPane.add (butPause);
		
		contentPane.add (bottomPane, BorderLayout.SOUTH);
		
		
		videoFrame.setVisible (true);
		
		player.getMediaPlayer().playMedia("test1.mp4");
		
		
	}
}
