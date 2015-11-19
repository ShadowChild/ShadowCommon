import io.github.shadowchild.common.util.Download;
import io.github.shadowchild.common.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Zach Piddock on 18/11/2015.
 */
public class DownloadObserverTest extends JDialog {

    private JPanel contentPane;
    private JButton downloadButton;
    private JProgressBar progressBar1;

    private static DownloadObserverTest dialog;

    public DownloadObserverTest() {

        dialog = this;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(downloadButton);

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        downloadButton.addActionListener(e -> {

            try {

                Utils.downloadFile("http://build.lwjgl.org/stable/lwjgl.zip", new DownloadObserver(), new File(".", "test"));
            } catch (IOException e1) {

                e1.printStackTrace();
            }
        });
    }

    {
        setupUI();
    }

    private void setupUI() {

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        progressBar1 = new JProgressBar();
        progressBar1.setStringPainted(true);
        contentPane.add(progressBar1, BorderLayout.CENTER);
        downloadButton = new JButton();
        downloadButton.setText("Download");
        contentPane.add(downloadButton, BorderLayout.SOUTH);
    }

    class DownloadObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {

            Download download = (Download) o;
            switch (download.getStatus()) {

                case Download.DOWNLOADING: {

                    dialog.progressBar1.setValue((int) download.getProgress());
                    break;
                }
                case Download.COMPLETE: {

                    JOptionPane.showMessageDialog(dialog, "File completed successfully");
                }
            }
        }
    }

    private void onCancel() {

        dispose();
    }

    public static void main(String... args) {

        DownloadObserverTest dialog = new DownloadObserverTest();
        dialog.setTitle("Download Test");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }

}
