package org.gustini.library.tools.fileOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.CRC32;

import rentasad.rentasad.library.basicTools.DateTools;

public class FileOperator
{

    /**
     * Gibt den Inhalt einer Datei als String zurueck
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String getFileValue(String fileName) throws FileNotFoundException, IOException
    {
        String fileValue = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        StringBuffer contentOfFile = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null)
        {
            contentOfFile.append(line + "\r\n");
        }
        fileValue = contentOfFile.toString();

        br.close();
        return fileValue;
    }

    /**
     *
     * Description: Gibt zu einem FileNameArray ein daraus erstelltes FileArray zurueck.
     *
     * @param fileNameArray
     * @return
     *         Creation: 13.10.2015 by mst
     */
    public static File[] getFilesFromFileNameArray(String[] fileNameArray)
    {
        ArrayList<File> fileList = new ArrayList<>();
        for (String fileName : fileNameArray)
        {
            fileList.add(new File(fileName));
        }
        return fileList.toArray(new File[0]);
    }
    /**
     * 
     * Description: Move a file to target path and throw different exceptions 
     * 
     * @param sourceFile
     * @param path
     * @throws FileCouldNotDeleteException
     * @throws IOException
     * Creation: 15.12.2016 by mst
     */
    public static void moveFileToPath(File sourceFile, String path) throws FileCouldNotDeleteException, FileAlreadyExistException, IOException
    {
        if (sourceFile.exists() && sourceFile.isFile())
        {
            File targetFile = new File(path + File.separator + sourceFile.getName());
            if (!targetFile.exists())
            {
                if (FileOperator.copyFile(sourceFile, targetFile))
                {
                    if (!sourceFile.delete())
                    {
                        throw new FileCouldNotDeleteException(sourceFile.getAbsolutePath());
                    }
                    
                }else
                {
                    throw new IOException("Error with copy File from " + sourceFile.getAbsoluteFile() + " to " + targetFile.getAbsolutePath());
                }
            }else
            {
                throw new FileAlreadyExistException(targetFile.getAbsolutePath());
            }
            
        } else
        {
            throw new FileNotFoundException(sourceFile.getAbsolutePath());
        }
        
    }

    /**
     *
     * Description: Gibt zu einem FileNameArray ein daraus erstelltes FileArray zurueck.
     *
     * @param fileNameArray
     * @return
     *         Creation: 13.10.2015 by mst
     */
    public static File[] getFilesFromFileNameArray(String[] fileNameArray, String additionalPath)
    {
        ArrayList<File> fileList = new ArrayList<>();
        for (String fileName : fileNameArray)
        {
            String fileNameWithPath = additionalPath + File.separator + fileName;
            fileList.add(new File(fileNameWithPath));
        }
        return fileList.toArray(new File[0]);
    }

    /**
     * 
     * Description: 
     * 
     * @param folderPath
     * @param fileFilter
     * @return
     * Creation: 14.12.2016 by mst
     */
    public static File[] getFilesFromPathWithFileExtension(File folderPath, final String extension)
    {
 
        FilenameFilter filenameFilter = new FilenameFilter()
        {

            @Override
            public boolean accept(File dir, String name)
            {
                if (dir.exists())
                {
                    if (name.endsWith(extension));
                    return true;
                }
                return false;
            }
        };
        if (folderPath.exists() && folderPath.isDirectory())
        {
            return new File(folderPath.getAbsolutePath()).listFiles(filenameFilter);
        } else
        {
            return null;
        }

    }

    /**
     *
     * Description: Gibt zu einer FileNameArrayList ein daraus erstelltes FileArray zurueck.
     *
     * @param fileNamesToProvideArrayList
     * @param tempDirPath
     * @return
     *         Creation: 13.10.2015 by mst
     */
    public static File[] getFilesFromFileNameArray(ArrayList<String> fileNamesToProvideArrayList, String tempDirPath)
    {

        return getFilesFromFileNameArray(fileNamesToProvideArrayList.toArray(new String[0]), tempDirPath);
    }

    /**
     * Gibt den Dateinamen einer Datei ohne den Pfad zurueck
     *
     * @param pathName
     * @return
     */
    public static String getFileNameFromPath(String pathName)
    {
        File file = new File(pathName);
        return file.getName();
    }

    /**
     * Kopiert Datei von File in zu File out.
     *
     * @param in
     * @param out
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static boolean copyFile(File in, File out) throws IOException
    {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try
        {
            inChannel = new FileInputStream(in).getChannel();
            outChannel = new FileOutputStream(out).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e)
        {
            throw e;
        } finally
        {
            try
            {
                if (inChannel != null)
                    inChannel.close();
                if (outChannel != null)
                    outChannel.close();
                System.gc();
            } catch (IOException e)
            {
            }
        }
        return out.exists();
    }

    /**
     *
     * Description:
     *
     * @param files
     * @param destinationPath
     *            without ending File.separator
     * @return
     * @throws IOException
     *             Creation: 13.10.2015 by mst
     */
    public static boolean copyFilesToDestinationPath(File[] files, String destinationPath) throws IOException
    {
        boolean success = true;
        for (File file : files)
        {

            if (file.exists() == false)
            {
                throw new FileNotFoundException(file.getAbsolutePath());
            } else
            {
                String fileName = file.getName();
                File destinationFile = new File(destinationPath + File.separator + fileName);
                copyFile(file, destinationFile);
                success = success && destinationFile.exists();
            }
        }
        return success;
    }

    /**
     * Erstellt ein Verzeichnis mit einem aktuellem Timestamp als Dateinamen.
     *
     * @param parentPath
     * @return String Filename wenn Erzeugung erfolgreich. Wenn Verzeichnis nicht erzeugt werden konnte, gibt es NULL zurueck.
     */
    public static String makeTimeStampDir(String parentPath)
    {

        String timeStampDirName = DateTools.getSQLTimeStampFromDate(GregorianCalendar.getInstance().getTime()).replace(':', '-');
        String dirName = parentPath + File.separator + timeStampDirName;
        File directoryFile = new File(dirName);
        boolean success = directoryFile.mkdir();
        if (success)
        {
            return dirName;
        } else
        {
            System.err.println(String.format("Verzeichnis %s konnte nicht erstellt werden.", dirName));
            return null;
        }
    }

    /**
     *
     * Description: Liest eine Datei aus und gibt deren Inhalt als String zurueck
     *
     * @param file
     * @return
     * @throws IOException
     *             Creation: 23.02.2016 by mst
     */
    public static String readFile(String file) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try
        {
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally
        {
            reader.close();
        }
    }

    public static boolean existDirectory(String destinationPath)
    {
        File file = new File(destinationPath);
        return (file.exists() && file.isDirectory());
    }
    
    public static Long getCrcFromFile(File file) throws IOException
    {
        if (file.exists() && file.isFile())
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            return getCRCFromInputStream(fileInputStream);
        }else
        {
            return null;
        }
    }
    
    

    public static Date getTimeStampFromFile(String fileName)
    {
        File file = new File(fileName);
        return new Date(file.lastModified());
    }
    
    /**
    *
    * @param inputStream
    * @return
    * @throws IOException
    */

   public static Long getCRCFromInputStream(InputStream inputStream) throws IOException
   {

       CRC32 crc32 = new CRC32();
       int counter;
       while ((counter = inputStream.read()) != -1)
       {
           crc32.update(counter);
       }
       inputStream.close();
       return crc32.getValue();
   }

    public static String getEncodingFromFile(File csvFile) throws IOException
    {
        InputStreamReader r = new InputStreamReader(new FileInputStream(csvFile));
        String encoder = r.getEncoding();
        r.close();
        return encoder; 
    }

}
