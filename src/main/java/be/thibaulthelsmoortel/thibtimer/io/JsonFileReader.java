package be.thibaulthelsmoortel.thibtimer.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Thibault Helsmoortel
 * @since 01/02/2018
 */
public final class JsonFileReader {

    private static final Logger LOGGER = LogManager.getLogger(JsonFileReader.class);
    private static final Object LOCKER_OBJ = new Object();

    private JsonFileReader() {
        // Prevent instantiation
    }

    public static JsonElement readFromResource(URL url) {
        synchronized (LOCKER_OBJ) {
            LOGGER.info("Trying to read json from: {}.", url.toString());
            JsonElement result = null;
            try (InputStream is = url.openStream()) {
                JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(is, Charset.defaultCharset())));
                JsonParser parser = new JsonParser();
                result = parser.parse(reader);
            } catch (IOException e) {
                LOGGER.error("Could not read from resource {}.", url.toString(), e);
            }

            return result;
        }
    }

}
