package ru.nsu.fit.oop;

import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NotebookTest {
    private void compareEntries(List<Entry> entries1, List<Entry> entries2) {
        for (int i = 0; i < entries1.size(); i++) {
            assertEquals(entries1.get(i).getName(), entries2.get(i).getName());
            assertEquals(entries1.get(i).getContent(), entries2.get(i).getContent());
        }
    }

    private static final SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy;hh:mm");

    @Test
    public void testNotebook() throws IOException, ParseException {
        File file = new File("notebook.json");
        Notebook book = new Notebook("notebook.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(ft.format(new Date()), "note1", "content1"));
        entries.add(new Entry(ft.format(new Date()), "note1", "content2"));
        entries.add(new Entry(ft.format(new Date()), "note5", "content3"));

        book.parse("notebook -add note1 content1 note1 content2 note5 content3");
        List<Entry> entries2 = mapper.readValue(file, new TypeReference<List<Entry>>() {});

        compareEntries(entries, entries2);

        entries.remove(0);
        book.parse("notebook -remove note1");
        entries2 = mapper.readValue(file, new TypeReference<List<Entry>>() {});

        compareEntries(entries, entries2);

        entries.remove(0);
        entries.remove(0);
        book.parse("notebook -remove note5 note1");
        entries2 = mapper.readValue(file, new TypeReference<List<Entry>>() {});

        compareEntries(entries, entries2);
    }

    @Test
    public void testNotebookInvalidCommands() throws IOException, ParseException {
        File file = new File("notebook.json");
        Notebook book = new Notebook("notebook.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        assertThrows(ParameterException.class, ()->book.parse("notebook -a"));
        assertThrows(ParameterException.class, ()->book.parse("something"));
        assertThrows(ParameterException.class, ()->book.parse("notebook -add"));
        assertThrows(ParameterException.class, ()->book.parse("notebook -rm"));
        assertThrows(ParameterException.class, ()->book.parse("notebook -filter"));
        assertThrows(ParameterException.class, ()->book.parse("notebook -filter 13.12.2021;06:19"));
        assertThrows(ParseException.class, ()->book.parse("notebook -filter wrongdate wrongdate"));
    }

}