package net.k40s;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 */
public class FileUtils {
  public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

    System.out.println("Writing '" + fileName + "' to zip file");

    File file = new File(fileName);
    FileInputStream fis = new FileInputStream(file);
    ZipEntry zipEntry = new ZipEntry(fileName);
    zos.putNextEntry(zipEntry);

    byte[] bytes = new byte[1024];
    int length;
    while ((length = fis.read(bytes)) >= 0) {
      zos.write(bytes, 0, length);
    }

    zos.closeEntry();
    fis.close();
  }
}
