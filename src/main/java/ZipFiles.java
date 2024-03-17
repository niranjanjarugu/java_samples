import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {


    public static void main(String[] args) {
//        File dir = new File("D:\\javaSamples\\Samples");
//        String zipDirName = "Niranjan_java_samples.zip";
//        ZipFiles zipFiles = new ZipFiles();
//        zipFiles.zipDirectory(dir, zipDirName);
    }

    private void zipDirectory(final File fileDirectory, final String zipDirectory) {
        try {
            final List<String> filesListInDir = new ArrayList<String>();
            populateFilesList(filesListInDir, fileDirectory);
            final FileOutputStream fos = new FileOutputStream(zipDirectory);
            final ZipOutputStream zos = new ZipOutputStream(fos);
            for(String filePath : filesListInDir){
                final ZipEntry zipEntry = new ZipEntry(filePath.substring(fileDirectory.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(zipEntry);
                final FileInputStream fis = new FileInputStream(filePath);
                final byte[] buffer = new byte[1024*10];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void populateFilesList(final List<String> filesListInDir, final File dir) throws IOException {
        final File[] files = dir.listFiles();
        for(File file : files){
            if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
            else populateFilesList(filesListInDir, file);
        }
    }

}