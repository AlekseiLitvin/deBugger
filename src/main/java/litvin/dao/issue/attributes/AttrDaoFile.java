package litvin.dao.issue.attributes;

import litvin.exceptions.DaoException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AttrDaoFile implements AttrDao {

    private static final String FILE_PATH = "/";
    private static final String FILE_EXTENSION = ".csv";
    private static final String DELIMITER = ";";

    @Override
    public void addAttribute(String fileName, String attr) {
        String appendix = DELIMITER + attr;
        try {
            synchronized (AttrDaoFile.class) {
                File file = new File(this.getClass().getResource(FILE_PATH + fileName + FILE_EXTENSION).getPath());
                Files.write(Paths.get(file.toURI()), appendix.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void editAttribute(String fileName, String old, String newLine) {
        try {
            File file = new File(this.getClass().getResource(FILE_PATH + fileName + FILE_EXTENSION).getPath());
            Path path = Paths.get(file.toURI());
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(path));
            content = content.replaceAll(old, newLine);
            synchronized (AttrDaoFile.class) {
                Files.write(path, content.getBytes(charset));
            }
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public String[] getAllAttributes(String fileName) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(FILE_PATH + fileName + FILE_EXTENSION)))) {
            synchronized (AttrDaoFile.class) {
                String content = br.readLine();
                return content.split(DELIMITER);
            }
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
