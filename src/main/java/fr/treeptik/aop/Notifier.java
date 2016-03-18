package fr.treeptik.aop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Notifier implements ThrowsAdvice {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	private void write(String s) throws IOException {

		File file = new File("blog.log"); // TODO: a rajouter en params !
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(file, true);
		fileWriter.write(s);
		fileWriter.close();

	}

	@AfterReturning("execution(* fr.treeptik.dao.*.*save*(..))")
	public void handleSaveNotification(JoinPoint jp) {

		String s = "SAVE \t" + new Date() + " \t"
				+ jp.getSignature() + " \t"
				+ jp.getArgs()[0].getClass().getName() + " \t"
				+ jp.getArgs()[0] + "\n";

		try {
			write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@AfterReturning("execution(* fr.treeptik.dao.*.*update*(..))")
	public void handleUpdateNotification(JoinPoint jp) {

		String s = "UPDATE \t" + new Date() + " \t"
				+ jp.getSignature() + " \t"
				+ jp.getArgs()[0].getClass().getName() + " \t"
				+ jp.getArgs()[0] + "\n";

		try {
			write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@AfterReturning("execution(* fr.treeptik.dao.*.*delete*(..))")
	public void handleDeleteNotification(JoinPoint jp) {

		String s = "DELETE \t" + new Date() + " \t"
				+ jp.getSignature() + " \t"
				+ jp.getArgs()[0].getClass().getName() + " \t"
				+ jp.getArgs()[0] + "\n";

		try {
			write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
