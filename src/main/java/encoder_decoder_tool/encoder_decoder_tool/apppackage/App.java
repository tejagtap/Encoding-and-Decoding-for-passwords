package main.java.encoder_decoder_tool.encoder_decoder_tool.apppackage;

import java.util.Base64;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class App {
	private Shell shell;
	private Text passTxtBox;
	private Text encodedTxtBox;
	private Text decodedTxtBox;

	public static void main(String[] args) {
		try {
			App window = new App();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		this.shell.open();
		this.shell.layout();
		while (!this.shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	protected void createContents() {
		this.shell = new Shell();
		this.shell.setSize(291, 300);
		this.shell.setText("Encoder - Decoder App");
		this.passTxtBox = new Text(this.shell, 4196352);
		this.passTxtBox.setBounds(29, 44, 189, 21);
		this.encodedTxtBox = new Text( this.shell, 2048);
		this.encodedTxtBox.setBounds(29, 141, 189, 21);
		this.decodedTxtBox = new Text( this.shell, 2048);
		this.decodedTxtBox.setBounds(29, 230, 189, 21);
		Label lblEnterPassword = new Label( this.shell, 0);
		lblEnterPassword.setBounds(29, 23, 104, 15);
		lblEnterPassword.setText("Enter Password");
		Label lblEncryptedKey = new Label( this.shell, 0);
		lblEncryptedKey.setBounds(29, 120, 104, 15);
		lblEncryptedKey.setText("Encoded Key");
		Label lblDecryptedValue = new Label( this.shell, 0);
		lblDecryptedValue.setBounds(29, 209, 104, 15);
		lblDecryptedValue.setText("Decoded value");
		Button btnEncrypt = new Button( this.shell, 0);
		btnEncrypt.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					String str = App.this.passTxtBox.getText();
					if (str == null || str.equalsIgnoreCase(""))
						throw new Exception("Enter some text in password field");
					byte[] bytesEncoded = Base64.getEncoder().encode(str.getBytes());
					App.this.encodedTxtBox.setText(new String(bytesEncoded));
				} catch (Exception e1) {
					MessageDialog.openError(App.this.shell, "Error", "Enter some text in password field");
					return;
				}
			}
		});
		btnEncrypt.setBounds(85, 81, 75, 25);
		btnEncrypt.setText("Encode");
		Button btnDecrypt = new Button( this.shell, 0);
		btnDecrypt.addSelectionListener((SelectionListener) new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					if (App.this.encodedTxtBox.getText() == null || App.this.encodedTxtBox.getText().equalsIgnoreCase(""))
						throw new Exception("No value present for decryption");
					byte[] valueDecoded = Base64.getDecoder().decode(App.this.encodedTxtBox.getText().getBytes());
					App.this.decodedTxtBox.setText(new String(valueDecoded));
				} catch (Exception e2) {
					MessageDialog.openError(App.this.shell, "Error", "No value present for decryption");
					return;
				}
			}
		});
		btnDecrypt.setBounds(85, 178, 75, 25);
		btnDecrypt.setText("Decode");
	}
}
