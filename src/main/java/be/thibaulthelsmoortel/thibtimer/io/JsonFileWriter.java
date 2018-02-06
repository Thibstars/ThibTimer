package be.thibaulthelsmoortel.thibtimer.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Thibault Helsmoortel
 * @since 01/02/2018
 */
public final class JsonFileWriter {

    private static final Logger LOGGER = LogManager.getLogger(JsonFileWriter.class);
    private static final Object LOCKER_OBJ = new Object();

    private JsonFileWriter() {
        // Prevent instantiation
    }

    public static void writeJsonToFile(String destinationPath, Object object) {
        synchronized (LOCKER_OBJ) {
            LOGGER.info("Trying to write an object as json to: {}.", destinationPath);
            GsonBuilder gsonBuilder = new GsonBuilder();
            try (FileWriter writer = new FileWriter(destinationPath)) {
                Gson gson = gsonBuilder.create();
                gson.toJson(object, writer);
            } catch (IOException e) {
                LOGGER.error("Could not write json to file.", e);
            }
        }
    }

}
