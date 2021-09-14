package rentasad.library.basicTools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import net.lingala.zip4j.model.FileHeader;
import rentasad.library.tools.commandExecutionTool.CommandExecuter;
import rentasad.library.tools.fileOperator.FileOperator;

/**
 *
 * Gustini GmbH (2015)
 * Creation: 19.02.2015
 * Library
 * gustini.library.tools
 *
 * @author Matthias Staud
 *
 *
 *         Description:
 *
 */
public class ZipTool
{
    String pathTo7ZipExe;

    public ZipTool(
                   String pathTo7ZipExe)
    {
        super();
        this.pathTo7ZipExe = pathTo7ZipExe;
    }

    /**
     *
     * @param zipFileName
     * @param destinationPath
     * @param password
     * @return
     * @throws FileNotFoundException
     */
    public boolean extractEncryptedZip(String zipFileName, String destinationPath, String password) throws FileNotFoundException
    {
        boolean success;
        /*
         * SET parameter=-pgrAEfin-11 -y
         *
         * "c:\Program Files\7-Zip\7z" e %1 %parameter%
         *
         * pause
         */
        File zipFile = new File(zipFileName);
        if (zipFile.exists())
        {
            // Erstellen der Parameterliste
            ArrayList<String> parameterList = new ArrayList<String>();
            // Entpacken der Datei
            parameterList.add(String.format(" e \"%s\"", zipFileName));
            // An folgenden Ort
            parameterList.add(String.format(" -o\"%s\"", destinationPath));
            // mit folgendem Passwort
            parameterList.add(String.format(" -p%s", password));
            parameterList.add(String.format(" -y"));

            String parameterString = StringTool.verketteStringArraylist2String(parameterList);

            String command = "\"" + this.pathTo7ZipExe + "\"" + parameterString;
            CommandExecuter.connect(command);
            success = true;

        } else
        {
            throw new FileNotFoundException(zipFileName);
        }

        return success;

    }

    @SuppressWarnings("unchecked")
    public static FileHeader[] getFileHeadersFromZipFile(final String fileName) throws net.lingala.zip4j.exception.ZipException
    {
        net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(fileName);
        @SuppressWarnings("rawtypes")
        List fileHeaders = zipFile.getFileHeaders();
        return (FileHeader[]) fileHeaders.toArray(new FileHeader[0]);

    }

    /**
     * Entpackt verschluesselte ZIP-Dateien
     * ES WERDEN NUR EINFACHE VERSCHLueSSELUNGSALGORITHMEN UNTERSTueTZT
     *
     * Fuer Strong encryption wird Lizenz von PKWARE benoetigt
     *
     * VIII. Strong Encryption Specification
     * -------------------------------------
     *
     * The Strong Encryption technology defined in this specification is
     * covered under a pending patent application. The use or implementation
     * in a product of certain technological aspects set forth in the current
     * APPNOTE, including those with regard to strong encryption, patching,
     * or extended tape operations requires a license from PKWARE. Portions
     * of this Strong Encryption technology are available for use at no charge.
     * Contact PKWARE for licensing terms and conditions. Refer to section II
     * of this APPNOTE (Contacting PKWARE) for information on how to
     * contact PKWARE.
     *
     *
     * @param fileName
     * @param destinationPath
     * @param password
     * @return
     * @throws IOException
     * @throws ZipException
     * @throws net.lingala.zip4j.exception.ZipException
     */

    public static boolean extractZipFile(final String fileName, final String destinationPath, String password) throws IOException, ZipException, net.lingala.zip4j.exception.ZipException
    {
        boolean success = false;
        net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(fileName);

        if (zipFile.isEncrypted())
        {
            zipFile.setPassword(password);
            zipFile.extractAll(destinationPath);
            success = true;

        } else
        {
            return extractZipFile(fileName, destinationPath);
        }

        return success;
    }

    /**
     * Entpackt unverschluesselte ZIP-Dateien im Zielverzeichnis
     *
     * @param fileName
     *            Pfad und Dateiname des ZIP-Files
     * @param destinationPath
     *            Zielpfad zum Entpacken
     * @return
     * @throws IOException
     */
    public static boolean extractZipFile(final String fileName, final String destinationPath) throws IOException
    {
        ZipFile zipFile = new ZipFile(fileName);
        Enumeration<? extends ZipEntry> enu = zipFile.entries();

        while (enu.hasMoreElements())
        {
            ZipEntry zipEntry = (ZipEntry) enu.nextElement();

            if (zipEntry.isDirectory() == false)
            {
                System.out.println("Dateiname    " + zipEntry.getName());
                System.out.println("Dateigroesse " + zipEntry.getSize());
                System.out.println("komprimiert  " + zipEntry.getCompressedSize());
                BufferedInputStream bis = null;
                bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                // Wegen fehlerhafter tests auskommentiert und an Ende der Datei gestellt.
                // zipFile.close();
                byte[] buffer;
                int avail = bis.available();
                if (avail > 0)
                {
                    buffer = new byte[avail];
                    bis.read(buffer, 0, avail);
                    if (bis != null)
                    {
                        bis.close();
                    }
                    String fileNameWithoutPath = FileOperator.getFileNameFromPath(zipEntry.getName());
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destinationPath + File.separator + fileNameWithoutPath));
                    bos.write(buffer, 0, buffer.length);
                    bos.close();
                }
            }
            // etc.
        }

        boolean success = true;
        zipFile.close();
        return success;
    }

}
