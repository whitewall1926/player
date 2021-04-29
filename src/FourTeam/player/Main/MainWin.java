package FourTeam.player.Main;

import javax.swing.*;


import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.sql.SQLException;

import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import FourTeam.player.Main.List.HistoryList;
import FourTeam.player.Main.Utils.*;
import javax.swing.event.ChangeListener;

import org.eclipse.swt.SWT;

import javax.swing.event.ChangeEvent;

public class MainWin {
	
	private static final MouseEvent MouseEvent = null;
	private static JProgressBar progressBar = new JProgressBar();
	private static JLabel lblNewLabel_2 = new JLabel("New label");
	private static JPanel panel_3 = new JPanel();//菜单栏
	private static JMenuBar menuBar = null ;
	private static JFrame videoFrame;
	private static JSlider volumnControlSlider;
	private static EmbeddedMediaPlayerComponent embeddedMediaPlayerComponent;
	private static JButton btnNewButton;
	private static JButton btnNewButton_5;
	private static Timer mouseTime;
	private static JList videoList ;
	public static void main (String []args) throws InterruptedException {
		
		//寻找LibVLC本地库
		new NativeDiscovery().discover();
        //System.out.println(LibVlc.INSTANCE.libvlc_get_version());
		//给组件添加键盘监听事件
		embeddedMediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		
		
		
		
		JFrame videoFrame = new JFrame ("播放器");
		videoFrame.setTitle("播放器");
		videoFrame.setSize (1219, 824);
		videoFrame.setLocationRelativeTo (null);
		videoFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = videoFrame.getContentPane ();
		videoFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel  bottomPane  = new JPanel ();
		
		//设置放置按钮组件大小及其位置
		bottomPane.setPreferredSize (new Dimension (100, 100));
		videoFrame.getContentPane().add(bottomPane, BorderLayout.SOUTH);
		bottomPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		bottomPane.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {57, 0, 57, 0, 57, 0, 57, 0, 0, 30, 30, 30, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		//设置播放按钮及其事件
	    btnNewButton = new JButton("暂停");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				if (btnNewButton.getText () == "暂停") {
					embeddedMediaPlayerComponent.getMediaPlayer().pause();
					btnNewButton.setText ("播放");
				}
				else {
					embeddedMediaPlayerComponent.getMediaPlayer().play();
					btnNewButton.setText ("暂停");
				}
			}
		});
		
		//键盘监听事件
		
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		
	
		//设置停止按钮及其事件
		JButton btnNewButton_2 = new JButton("停止");
		
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 1;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
		
		
		//添加后退按键和后退监听事件
		JButton btnNewButton_4 = new JButton("<<");
		
		
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				float to = (float) ((progressBar.getPercentComplete() * progressBar.getWidth() - 100) / progressBar.getWidth());
				embeddedMediaPlayerComponent.getMediaPlayer().setTime((long)(to * embeddedMediaPlayerComponent.getMediaPlayer().getLength()));
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 6;
		gbc_btnNewButton_4.gridy = 1;
		panel_1.add(btnNewButton_4, gbc_btnNewButton_4);
		
		//添加快进按钮和事件
		JButton btnNewButton_3 = new JButton(">>");
		
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//currentTime = 进度条长度占 进度条总长度百分比 * 视频事件长度
				float to = (float) ((progressBar.getPercentComplete() * progressBar.getWidth() + 100) / progressBar.getWidth());
				embeddedMediaPlayerComponent.getMediaPlayer().setTime((long)(to * embeddedMediaPlayerComponent.getMediaPlayer().getLength()));
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_3.gridx = 9;
		gbc_btnNewButton_3.gridy = 1;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
		
		//增加全屏功能
		btnNewButton_5 = new JButton("全屏");
		
		
		
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			
			String tx = "全屏";
			public void mouseClicked(MouseEvent e) {
				if (tx == "全屏") {
					embeddedMediaPlayerComponent.getMediaPlayer().setFullScreenStrategy(new DefaultAdaptiveRuntimeFullScreenStrategy(videoFrame));
					//progressBar.setVisible(false);
					//panel_1.setVisible (false);
					panel_3.setVisible (false);
					embeddedMediaPlayerComponent.getMediaPlayer().setFullScreen(true);
					tx = "窗口";
					btnNewButton_5.setText (tx);
				}
				else {
					//progressBar.setVisible(true);
					embeddedMediaPlayerComponent.getMediaPlayer().setFullScreen(false);
				//	panel_3.setVisible(true);
					panel_3.setVisible (true);
					tx = "全屏";
					btnNewButton_5.setText (tx);
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_5.gridx = 10;
		gbc_btnNewButton_5.gridy = 1;
		panel_1.add(btnNewButton_5, gbc_btnNewButton_5);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 embeddedMediaPlayerComponent.getMediaPlayer().stop();
			}
		});
		
		JPanel panel_2 = new JPanel();
		bottomPane.add(panel_2, BorderLayout.NORTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {50, 700, 50, 0};
		gbl_panel_2.rowHeights = new int[]{15, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel = new JLabel("currentLabel");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		//添加进度条
		
		//给进度条添加鼠标监听事件
		progressBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				float to = (float) x / progressBar.getWidth();
				embeddedMediaPlayerComponent.getMediaPlayer().setTime((long) (to * embeddedMediaPlayerComponent.getMediaPlayer().getLength()));
			}
		});
		
		
		//添加音量控件以及监听事件
		volumnControlSlider = new JSlider ();
		
		volumnControlSlider.setPaintLabels (true);
		volumnControlSlider.setSnapToTicks(true);
		volumnControlSlider.setPaintTicks(true);
		volumnControlSlider.setValue(100);
		volumnControlSlider.setMaximum(120);
		volumnControlSlider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				volumnControlSlider.setValue ((int)(e.getX() * ((float)volumnControlSlider.getMaximum() / volumnControlSlider.getWidth())));
			}
		});
		volumnControlSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				embeddedMediaPlayerComponent.getMediaPlayer().setVolume (volumnControlSlider.getValue());
				lblNewLabel_2.setText ("" + embeddedMediaPlayerComponent.getMediaPlayer().getVolume());
				
			}
		});
		GridBagConstraints gbc_js = new GridBagConstraints();
		gbc_js.insets = new Insets(0, 0, 0, 5);
		gbc_js.gridx = 13;
		gbc_js.gridy = 1;
		panel_1.add (volumnControlSlider, gbc_js);
		
		
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridx = 14;
		gbc_lblNewLabel_2.gridy = 1;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.BOTH;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 0;
		panel_2.add(progressBar, gbc_progressBar);
		progressBar.setPreferredSize (new Dimension(2000, 10));
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText("totalLabel");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		//放置视频得组件
		JPanel panel = new JPanel();
		videoFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		//视频播放列表
	/*	JPanel p = new JPanel ();
		p.setLayout(new BorderLayout(0, 0));
		videoList = new JList ();
		p.add (videoList);
		videoFrame.getContentPane().add(p,BorderLayout.WEST);
		p.setPreferredSize(new Dimension(100, 100));
	*/
		
		
		
		lblNewLabel_2.setText ("" + embeddedMediaPlayerComponent.getMediaPlayer().getVolume());
		//添加视频组件
		panel.add(embeddedMediaPlayerComponent);
		
		//菜单栏
		
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		videoFrame.getContentPane().add (panel_3, BorderLayout.NORTH);
		panel_3.setPreferredSize(new Dimension(25, 30));
		
		menuBar = new JMenuBar();
		panel_3.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("文件");
		menuBar.add(mnNewMenu);
		
		//打开文件
		JMenuItem mntmNewMenuItem = new JMenuItem("打开");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int v = chooser.showOpenDialog(null);
				if (v == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					embeddedMediaPlayerComponent.getMediaPlayer().playMedia(file.getAbsolutePath());
					//储存打开记录
					String sql = "insert into history values (default, ?, ?, ?, default)";
					try {
						String name = file.getName();
						String type = name.subSequence(name.indexOf('.') + 1, name.length()).toString();
						DBHelper.update (sql, file.getName(),type, file.getAbsolutePath());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("退出");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("最近打开");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new HistoryList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		keyBordListner();
		mnNewMenu.add(mntmNewMenuItem_2);
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane.setVisible	(true);
		videoFrame.setVisible (true);
		//播放视频
	//	embeddedMediaPlayerComponent.getMediaPlayer().playMedia("test1.mp4");
		//设置播放时间显示
		while (true) {
			//延迟更新
			Thread.sleep(500);
			long totalTime = embeddedMediaPlayerComponent.getMediaPlayer().getLength();
			long currentTime = embeddedMediaPlayerComponent.getMediaPlayer().getTime();
			VedioTime vt = new VedioTime();
		    vt.timeCalculate  (totalTime, currentTime);
			lblNewLabel.setText (vt.getMinitueCurrent() + ":" + vt.getSecondCurrent());
			lblNewLabel_1.setText (vt.getMinitueTotal() + ":" + vt.getSecondTotal());
			//进度条显示进度
			float percent = (float) currentTime / totalTime;
			progressBar.setValue ((int) (percent * 100));
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public static void keyBordListner(){
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			@Override
			public void eventDispatched(AWTEvent event) {
				// TODO Auto-generated method stub
				if(((KeyEvent)event).getID()==KeyEvent.KEY_PRESSED){
					switch (((KeyEvent)event).getKeyCode())  {
					case KeyEvent.VK_SPACE:{
						System.out.println ("空格键");
						if (embeddedMediaPlayerComponent.getMediaPlayer().isPlaying()) {
							embeddedMediaPlayerComponent.getMediaPlayer().pause();
							btnNewButton.setText ("播放");
						}
						else {
							embeddedMediaPlayerComponent.getMediaPlayer().play();
							btnNewButton.setText ("暂停");
						}
					}
					break;
					case KeyEvent.VK_RIGHT: {
						System.out.println ("快进键");
						float to = (float) ((progressBar.getPercentComplete() * progressBar.getWidth() + 100) / progressBar.getWidth());
						embeddedMediaPlayerComponent.getMediaPlayer().setTime((long)(to * embeddedMediaPlayerComponent.getMediaPlayer().getLength()));
					}
					break;
					case KeyEvent.VK_LEFT: {
						System.out.println ("后退键");
						float to = (float) ((progressBar.getPercentComplete() * progressBar.getWidth() - 100) / progressBar.getWidth());
						embeddedMediaPlayerComponent.getMediaPlayer().setTime((long)(to * embeddedMediaPlayerComponent.getMediaPlayer().getLength()));
					}
					break;
					case KeyEvent.VK_UP: {
						System.out.println ("音量＋");
						volumnControlSlider.setValue(volumnControlSlider.getValue() + 10);
						volumnControlSlider.setValue(volumnControlSlider.getValue());
					}
					break;
					case KeyEvent.VK_DOWN: {
						System.out.println ("音量-");
						volumnControlSlider.setValue(volumnControlSlider.getValue() - 10);
						volumnControlSlider.setValue(volumnControlSlider.getValue());
					}
					break;
					case KeyEvent.VK_ESCAPE: {
						System.out.println ("退出全屏");
						String tx = btnNewButton_5.getText ();
						if (tx == "窗口") {
							embeddedMediaPlayerComponent.getMediaPlayer().setFullScreen(false);
							panel_3.setVisible(true);
							tx = "全屏";
							btnNewButton_5.setText (tx);
						}
					}
					break;
				}
			}
		}
	}, AWTEvent.KEY_EVENT_MASK);
	}
	/*	 public static void mouseClicked(MouseEvent e) {
             int i = e.getButton();
            
                 
				if (e.getClickCount() == 1) {
                     mouseTime = new Timer(350, new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                        	 if (btnNewButton.getText () == "暂停") {
             					embeddedMediaPlayerComponent.getMediaPlayer().pause();
             					btnNewButton.setText ("播放");
             				}
             				else {
             					embeddedMediaPlayerComponent.getMediaPlayer().play();
             					btnNewButton.setText ("暂停");
             				}
                             mouseTime.stop();
                         }
                     });
                     mouseTime.restart();
                 } else if (e.getClickCount() == 2 && mouseTime.isRunning()) {
                     mouseTime.stop();
                     if (embeddedMediaPlayerComponent.getMediaPlayer().isFullScreen()) {
                         
                     } else {
                         
                     }
                 }
             
         }*/
     

	
}
