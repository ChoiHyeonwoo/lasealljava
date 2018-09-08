

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemTrayTest {
	// Ʈ����
	private SystemTray m_tray = SystemTray.getSystemTray();
	// ������
	private TrayIcon m_ti;
	// Ʈ���� ������ Ÿ��Ʋ
	String m_strTrayTitle;
	// Ʈ���� �������� �ʱ⼳��

	private void initTray() {
		// Ʈ���� ������ �̹���
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("red.png"));
		// TrayIcon ����
		m_ti = new TrayIcon(image, m_strTrayTitle, createPopupMenu());
		m_ti.setImageAutoSize(true);
		m_ti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ʈ���� ������ ��ü�� Ŭ�������� �Ͼ �̺�Ʈ

			}
		});
		// SystemTray�� TrayIcon�� �ν��Ͻ��� ��
		try {
			m_tray.add(m_ti);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
	}

	public SystemTrayTest(String strTrayTitle) {

		m_strTrayTitle = strTrayTitle;
		initTray();

	}

	// �˾��޴�
	private PopupMenu createPopupMenu() {
		PopupMenu popupMenu = new PopupMenu();

		MenuItem miQuit = new MenuItem("����");


		miQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exiting...");
				System.exit(0);
			}
		});
		

		// �˾� �޴��� ���
		popupMenu.add(miQuit);
		// �� ����
		popupMenu.addSeparator();
		// �˾� �޴��� ���
		


		return popupMenu;
	}

}
