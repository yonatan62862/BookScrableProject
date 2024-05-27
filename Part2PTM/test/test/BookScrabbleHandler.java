package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class BookScrabbleHandler implements ClientHandler {

   @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        String row;
        try {
            BufferedReader readFromClient = new BufferedReader(new InputStreamReader(inFromclient));
            PrintWriter sendToClient = new PrintWriter(outToClient);
            row = readFromClient.readLine();
            String[] split = row.split(",");
            String type = split[0];
            String[] bookNames = new String[split.length-1];
            System.arraycopy(split, 1, bookNames, 0, split.length - 1);
            if(type.equals("Q")) {
                sendToClient.println(DictionaryManager.get().query(bookNames));
                sendToClient.flush();
            }
            else if(type.equals("C")) {
                sendToClient.println(DictionaryManager.get().challenge(bookNames));
                sendToClient.flush();
            }
            readFromClient.close();
            sendToClient.close();

        } catch (IOException e) {}

    }

    @Override
    public void close() {

    }

}



