package FourTeam.player.Main;

import javax.swing.*;


import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class MainWin {
	public static void main (String []args) {
		//寻找LibVLC本地库
		new NativeDiscovery().discover();
        //System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		JFrame videoFrame = new JFrame ("�ҵĴ���");
		videoFrame.setSize (1000, 500);
		videoFrame.setLocationRelativeTo (null);
		videoFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = videoFrame.getContentPane ();
		contentPane.setLayout (new BorderLayout());
		
		//添加视频播放器组件
		EmbeddedMediaPlayerComponent player = new EmbeddedMediaPlayerComponent ();
		contentPane.add (player, BorderLayout.CENTER);
		
		//添加按钮播放、暂停、停止
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
		
		JProgressBar progressBar = new JProgressBar();
		bottomPane.add(progressBar);
		bottomPane.add (butPlay);
		bottomPane.add (butStop);
		bottomPane.add (butPause);
		
		contentPane.add (bottomPane, BorderLayout.SOUTH);
		
		
		videoFrame.setVisible (true);
		
		player.getMediaPlayer().playMedia("test1.mp4");
		
	}
}
