import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * File: MapMenuBar.java Date: Nov 3, 2015 Author: Derek Email:
 * Derek.Benson@tufts.edu Description: TODO
 *
 */

public class MapMenuBar extends JMenuBar {

	/**
	 * 
	 */

	private Map target;

	public MapMenuBar(Map target) {
		this.target = target;
		init();
	}

	public void init() {
		JMenu mnNewMenu = new JMenu("File");
		add(mnNewMenu);

		// Exit Button
		JMenuItem mntmExit = new JMenuItem("Exit");

		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		mnNewMenu.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		add(mnEdit);

		JMenuItem mntmAddAPlane = new JMenuItem("Add a Plane");

		// Add a Plane Button
		mntmAddAPlane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPlaneDialog popup = new AddPlaneDialog(target);
				popup.setVisible(true);
			}
		});

		mnEdit.add(mntmAddAPlane);

		// Remove a Plane Button
		JMenuItem mntmRemovePlane = new JMenuItem("Remove Plane");

		mntmRemovePlane.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				target.removeCurrent();
			}
		});

		mnEdit.add(mntmRemovePlane);

		JMenuItem mntmPlay = new JMenuItem("Play");

		JMenuItem mntmPause = new JMenuItem("Pause");

		mntmPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				target.startAnimation();
				mntmPlay.setEnabled(false);
				mntmPause.setEnabled(true);
			}
		});

		mntmPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				target.pauseAnimation();
				mntmPlay.setEnabled(true);
				mntmPause.setEnabled(false);
			}
		});
		mntmPlay.setEnabled(false);
		mnEdit.add(mntmPlay);
		mnEdit.add(mntmPause);
	}
}
