package ru.ncband.web.server.logic;

import ru.ncband.web.server.db.servises.MessageDB;
import ru.ncband.web.shared.classes.Status;
import ru.ncband.web.shared.properties.BasicProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageCreator {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Print date for message (MM"+ BasicProperty.dateSeparator()+
                                                "DD"+ BasicProperty.dateSeparator()
                                                +"YYYY): ");
        String date = reader.readLine();
        System.out.println("Message:");
        String text = reader.readLine();;
        reader.close();

        MessageDB messageDB = new MessageDB();
        Status status = messageDB.set(date, text);
        System.out.println(status.getMsg());
    }

}
