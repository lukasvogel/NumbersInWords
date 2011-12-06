package numbersinwords;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class MainApplication {
	private static Text input;
	private static Text output;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		final WordConstructor constructor = new WordConstructor();
		
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Numbers in words");
		
		input = new Text(shell, SWT.BORDER);
		input.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if (input.getText().matches("[0-9]+")) 
					output.setText(constructor.constructWord(input.getText()));
				else
					output.setText("This input is no number!");
			}
		});
		input.setBounds(129, 68, 214, 21);
		
		Label lblEnterYourNumber = new Label(shell, SWT.NONE);
		lblEnterYourNumber.setBounds(10, 71, 113, 15);
		lblEnterYourNumber.setText("Enter your Number:");
		
		output = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		output.setEditable(false);
		output.setBounds(10, 145, 414, 73);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
