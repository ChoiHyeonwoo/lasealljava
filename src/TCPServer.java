
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TCPServer {
	private ArrayList<Echo> echos = new ArrayList<Echo>();
	ServerSocket serverSocket = null;
	Socket client = null;
	String ServerIP = null;

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
	int t = (int) (dimScreen.getWidth());
	int q = (int) (dimScreen.getHeight());
	int x = t / 2;
	int y = q / 2;
	
	JFrame jf = new JFrame("LaseAll");
	JLabel jl;
    
	public static int drawCount = 0; // 객체
    
	public int[] mouseRemote(int _x, int _y) {
		int temp_x;
		int temp_y;
		temp_x = x + _x;
		temp_y = y + _y;

		if (temp_x < 0)
			temp_x = 0;
		else if (temp_x >= t)
			temp_x = t;

		if (temp_y < 0)
			temp_y = 0;
		else if (temp_y >= q)
			temp_y = q;

		if (x < temp_x && y < temp_y) {
			while (true) {
				if (x != temp_x)
					x++;
				if (y != temp_y)
					y++;

				if (x == temp_x && y == temp_y)
					break;
			}
		} else if (x < temp_x && y > temp_y) {
			while (true) {
				if (x != temp_x)
					x++;
				if (y != temp_y)
					y--;

				if (x == temp_x && y == temp_y)
					break;
			}
		} else if (x > temp_x && y < temp_y) {
			while (true) {
				if (x != temp_x)
					x--;
				if (y != temp_y)
					y++;

				if (x == temp_x && y == temp_y)
					break;
			}
		} else if (x > temp_x && y > temp_y) {
			while (true) {
				if (x != temp_x)
					x--;
				if (y != temp_y)
					y--;

				if (x == temp_x && y == temp_y)
					break;
			}

		}
		int arr[] = new int[2];
		arr[0] = x;
		arr[1] = y;
		return arr;

	}

	public void startServer() {
		try {
			ServerIP = InetAddress.getLocalHost().getHostAddress();

			System.out.println(ServerIP);
			System.out.println("S : Connection...");
			serverSocket = new ServerSocket(3000);

			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jl = new JLabel(ServerIP, JLabel.CENTER);
			jl.setFont(new Font("Dialog", Font.PLAIN, 30));
			jf.getContentPane().add(jl, BorderLayout.CENTER);
			jf.setSize(500, 200);
			jf.setLocation(t/2-200, q/2-200);

            Dimension d = new Dimension(400,200);
            jf.setPreferredSize(d);
			jf.pack();
			jf.setVisible(true);

				try {
					client = serverSocket.accept();
					jf.setVisible(false);
					Echo echo = new Echo();
					echos.add(echo);
					echo.start();
					
				} catch (SocketTimeoutException e) {
					e.printStackTrace();
				}

		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class Echo extends Thread {
		BufferedReader in;
		PrintWriter out;
		boolean flag = false;
		boolean drawFlag = false;
		boolean stringFlag = false;
		boolean figureFlag = false;
		FrameTest ft;
		SystemTrayTest st;
		Container ct;
		PanelTest pt;
		String pColor = "0xffff0000";
		ColorTest colT;

		public Echo() throws IOException {
			InputStream is = client.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = client.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			out = new PrintWriter(bw, true);
		}

		@Override
		public void run() {
			try {
				Robot robot = new Robot();
				st = new SystemTrayTest("Test");
				colT = ColorTest.getInstance();
				ft = FrameTest.getInstance();
				ct = ft.getContentPane();
				ct.setVisible(false);
				ft.setVisible(false);
				System.out.println(client.getInetAddress()+":"+client.getPort()+"과 연결이 되었습니다.");
				while (true) {
					System.out.println("S : Receiving...");
					try {
						String str = in.readLine();
						System.out.println("S : Received : " + str);
						
						switch (str) {
						case "back": {
							if (pt != null) {
								flag = false;
								ct.remove(pt);
								pt = null;
								
								ArrayTest.arrString.clear();
								ArrayTest.arrDraw.clear();
								ArrayTest.arrFigure.clear();
								
								ft.setVisible(false);
								ct.setVisible(false);
							}
							robot.keyPress(KeyEvent.VK_UP);
							break;
						}

						case "go": {
							if (pt != null) {
								flag = false;
								ct.remove(pt);
								pt = null;
								
								ArrayTest.arrString.clear();
								ArrayTest.arrDraw.clear();
								ArrayTest.arrFigure.clear();
								
								ft.setVisible(false);
								ct.setVisible(false);
							}
							robot.keyPress(KeyEvent.VK_DOWN);
							break;
						}
						case "mouseon": {
				
							if (pt != null) {
								flag = false;
								ct.remove(pt);
								pt = null;
								ft.setVisible(false);
								ct.setVisible(false);
							}
							
							flag = false;
							drawFlag = false;
							stringFlag = false;
							figureFlag = false;
							
							if (stringFlag == false && flag == false && drawFlag == false && figureFlag == false) {
								while (flag == false) {
									String str2 = in.readLine();
									String str3 = in.readLine();

									if (str2.equals("subMenuOff") || str3.equals("subMenuOff")) {
										pt.mode = "redpointer";
										flag = true;
										break;
									} else if (str2.equals("true")) {
										robot.mousePress(InputEvent.BUTTON1_MASK);
										robot.mouseRelease(InputEvent.BUTTON1_MASK);
									} else {
										int x = Integer.parseInt(str2);
										int y = Integer.parseInt(str3);

										robot.mouseMove(mouseRemote(x, y)[0], mouseRemote(x, y)[1]);
									}
								}
							}
							break;
						}

						case "drawon": {
							if (pt == null) {
								pt = new PanelTest();
								ct.add(pt);

								ct.setVisible(true);
								ft.setVisible(true);
							}
							flag = false;
							stringFlag = false;
							drawFlag = true;
							figureFlag = false;
							pt.mode = "drawpointer";
							if (drawFlag == true && flag == false && stringFlag == false && figureFlag == false) {

								while (drawFlag == true) {
									String str2 = in.readLine();
									String str3 = in.readLine();

									if (str2.equals("subMenuOff") || str3.equals("subMenuOff")) {
										pt.mode = "redpointer";
										drawFlag = false;

										break;
									}
									else if (str2.equals("asdf")) {
										colT.setDrawColor(colT.stringToColor(str3));

									}

									else if (str3.equals("true")) {
										drawCount++;
										System.out.println(drawCount);
										pt.mode = "realdraw";

									}

									else if (str3.equals("false")) {
										pt.mode = "drawpointer";

									}

									else {
										int x = Integer.parseInt(str2);
										int y = Integer.parseInt(str3);

										pt.pointer(x, y);

									}
								}
							}
							break;
						}

						case "true": {

							if (pt == null) {
								pt = new PanelTest();
								ct.add(pt);

								ct.setVisible(true);
								ft.setVisible(true);
							}
							pt.mode = "redpointer";
							stringFlag = false;
							flag = true;
							drawFlag = false;
							figureFlag = false;
							while (flag == true && drawFlag == false && stringFlag == false && figureFlag == false) {

								String str2 = in.readLine();
								String str3 = in.readLine();

								if (str3.equals("false")) {
									flag = false;
									break;
								}

								if (flag == true) {
									int x = Integer.parseInt(str2);
									int y = Integer.parseInt(str3);

									pt.pointer(x, y);

								}

							}
							break;
						}	
						case "figureon": {
							if (pt == null) {
								pt = new PanelTest();
								ct.add(pt);

								ct.setVisible(true);
								ft.setVisible(true);
							}
							
							pt.mode = "figuremode";
							stringFlag = false;
							flag = false;
							drawFlag = false;
							figureFlag = true;
							
							if (drawFlag == false && flag == false && stringFlag == false && figureFlag == true) {

								while (figureFlag == true) {
									String str2 = in.readLine();
									String str3 = in.readLine();


									if (str2.equals("subMenuOff") || str3.equals("subMenuOff")) {
										pt.mode = "redpointer";
										figureFlag = false;
										break;
									}
									
									else if(str2.equals("true")){
										pt.figuremode = true;
										pt.repaint();
										break;
									}								
							
									else if (str2.equals("asdf")){
										if (str3.equals("bigTriangle")) {
											PanelTest.figureVal = 1;								
										}
										else if (str3.equals("bigCircle")) {
											PanelTest.figureVal = 2;
										}
										else if (str3.equals("bigRect")){
											PanelTest.figureVal = 3;
										}
										else if (str3.equals("mediumTriangle")) {
											PanelTest.figureVal = 4;								

										}
										else if (str3.equals("mediumCircle")) {
											PanelTest.figureVal = 5;
										}
										else if (str3.equals("mediumRect")){
											PanelTest.figureVal = 6;
										}
										else if (str3.equals("smallTriangle")) {
											PanelTest.figureVal = 7;								

										}
										else if (str3.equals("smallCircle")) {
											PanelTest.figureVal = 8;
										}
										else if (str3.equals("smallRect")){
											PanelTest.figureVal = 9;
										}
									}
									else {
										int x = Integer.parseInt(str2);
										int y = Integer.parseInt(str3);

										pt.pointer(x, y);
									}
								}
							}
							break;
						}
						case "stringon": {
							if (pt == null) {
								pt = new PanelTest();
								ct.add(pt);

								ct.setVisible(true);
								ft.setVisible(true);
							}
							pt.mode = "inputstring";
							flag = false;
							drawFlag = false;
							stringFlag = true;
							figureFlag = false;
							if (stringFlag == true && flag == false && drawFlag == false && figureFlag == false) {
								while (stringFlag == true) {
									String str2 = in.readLine();
									String str3 = in.readLine();

									if (str2.equals("subMenuOff") || str3.equals("subMenuOff")) {
										pt.mode = "redpointer";
										drawFlag = false;
										flag = true;
										break;
									}

									else if (str2.equals("true")) {
										String temp = "";

										temp = in.readLine();

										pt.drawString = temp;
										PanelTest.stringmode = true;
										pt.repaint();

									}

									else {
										int x = Integer.parseInt(str2);
										int y = Integer.parseInt(str3);

										pt.pointer(x, y);
									}
								}
							}
							break;
						}
						case "false": {
							System.out.println("false 들어옴");
						}
						default:
							break;
						}
						out.println("Server Received " + str);
					} catch (IOException | NullPointerException e) {
						jl.setText("Could not get Input/Output from server" + client.getInetAddress()+":"+client.getPort()+"과의 접속이 끊겼습니다.");
						jf.setVisible(true);
						robot.delay(5000);
						System.exit(1);
					}
				}
			} catch (AWTException e1) {
				e1.printStackTrace();
			} finally {
				try {
					if (client != null) {
						client.close();					

						jl.setText(client.getInetAddress()+":"+client.getPort()+"과의 접속이 끊겼습니다.");
						jf.setVisible(true);
						echos.remove(this);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
			
		new TCPServer().startServer();
	}
}
