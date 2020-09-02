import uk.co.innoxium.cybernize.util.Download;
import uk.co.innoxium.cybernize.util.MathUtils;
import uk.co.innoxium.cybernize.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MultiDownloadTest extends JDialog {

	private JPanel contentPane;
    private JButton downloadButton;
    private JLabel dataLabel;
    private JProgressBar progressBar1;

    private static MultiDownloadTest dialog;
    private List<String> downloads = new ArrayList<>();
    private MultiDownloadObserver observer = new MultiDownloadObserver(downloads);

    public MultiDownloadTest() {

        dialog = this;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(downloadButton);
        
        // Add some file to the download list
        downloads.add("http://build.lwjgl.org/stable/lwjgl.zip");
        downloads.add("http://silenceengine.goharsha.com/downloads/SEProjectCreator.jar");

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
        downloadButton.addActionListener(e -> {

            try {

                multiDownloadFile(downloads);
            } catch(IOException e1) {

                e1.printStackTrace();
            }
        });
    }

    {
    	// ensures the components have been initialised
        setupUI();
    }

    private void setupUI() {

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        progressBar1 = new JProgressBar();
        progressBar1.setStringPainted(true);
        contentPane.add(progressBar1, BorderLayout.NORTH);
        dataLabel = new JLabel("[0 B] / [0 B]");
        dataLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(dataLabel);
        downloadButton = new JButton();
        downloadButton.setText("Download");
        contentPane.add(downloadButton, BorderLayout.SOUTH);
    }

    class MultiDownloadObserver implements Observer {
    
    	public List<String> downloads;

        public MultiDownloadObserver(List<String> downloads) {
			
        	this.downloads = downloads;
		}

		@Override
        public void update(Observable o, Object arg) {

            Download download = (Download)o;
            switch(download.getStatus()) {

                case Download.DOWNLOADING: {

                    dialog.progressBar1.setValue((int)download.getProgress());
                    dialog.dataLabel.setText(getByteProgress(download));
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
    
    private String getByteProgress(Download download) {
    	
    	return MathUtils.humanReadableByteCount(download.getDownloaded()) + " / " 
    			+ MathUtils.humanReadableByteCount(download.getSize());
    }
    
    private void multiDownloadFile(List<String> downloads) throws IOException {
    	
    	for(String download : downloads) {
    		
    		Utils.downloadFile(download, observer, new File(".", "test"));
    	}
    }

    // Entry Point
    public static void main(String... args) {

    	// Initialise the test
        DownloadObserverTest dialog = new DownloadObserverTest();
        dialog.setTitle("Download Test");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
