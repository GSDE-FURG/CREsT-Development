package ops;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class ShellScriptOps {
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }

    public static void executeCommands(String path, String commands) throws IOException, InterruptedException {
        String scriptPath = path;

        Process process = Runtime.getRuntime().exec(scriptPath + " " + commands);
        process.waitFor();

        int exitValue = process.exitValue();
        if (exitValue == 0) {
            try {
                String line;

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(process.getInputStream()) );
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
            }
            catch (Exception e) {
                // ...
            }
        } else {
            System.out.println("Script failed with exit value: " + exitValue);
        }
    }
}
