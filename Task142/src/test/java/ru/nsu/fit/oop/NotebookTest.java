package ru.nsu.fit.oop;

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
    private void compareNames(List<Entry> entries1, List<Entry> entries2) {
        for (int i = 0; i < entries1.size(); i++) {
            assertEquals(entries1.get(i).getName(), entries2.get(i).getName());
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
        entries.add(new Entry(ft.format(new Date()), "note1"));
        entries.add(new Entry(ft.format(new Date()), "note1"));
        entries.add(new Entry(ft.format(new Date()), "note5"));

        book.parse("notebook -add note1 note1 note5");
        List<Entry> entries2 = mapper.readValue(file, new TypeReference<List<Entry>>() {});

        compareNames(entries, entries2);

        entries.remove(0);
        book.parse("notebook -remove note1");
        entries2 = mapper.readValue(file, new TypeReference<List<Entry>>() {});

        compareNames(entries, entries2);

        entries.remove(0);
        entries.remove(0);
        book.parse("notebook -remove note5 note1");
        entries2 = mapper.readValue(file, new TypeReference<List<Entry>>() {});

        compareNames(entries, entries2);
    }

}