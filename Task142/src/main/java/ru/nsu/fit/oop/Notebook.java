package ru.nsu.fit.oop;

import com.beust.jcommander.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Class that allows working with a JSon notebook to parse commands.
 */
public class Notebook {
    private JCommander notebookCmd;
    private NotebookCommand params;
    private static final SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy;hh:mm");
    private final ObjectMapper mapper;
    private final File notebookFile;
    private List<Entry> entryArr;

    /**
     * The NotebookCommand class describes a JCommander command and contains the values of entered parameters.
     */
    @Parameters
    private static class NotebookCommand {
        @Parameter(names = "-add", variableArity = true)
        private List<String> newEntries = new ArrayList<>();

        @Parameter(names = {"-rm", "-remove"}, variableArity = true)
        private List<String> removedEntries = new ArrayList<>();

        @Parameter(names = "-filter", variableArity = true)
        private List<String> filterParams = new ArrayList<>();

        @Parameter(names = "-show")
        private boolean show = false;
    }

    /**
     * Constructs a new notebook and initializes its values.
     *
     * @param filename - the file that the notebook should work with.
     */
    public Notebook(String filename) {
        mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        notebookFile = new File(filename);
        params = new NotebookCommand();
        notebookCmd = JCommander.newBuilder()
                .addCommand("notebook", params)
                .build();

        //check if file is empty
        if (notebookFile.length() == 0) {
            entryArr = new ArrayList<Entry>();
        } else {
            try {
                entryArr = mapper.readValue(notebookFile, new TypeReference<List<Entry>>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Parses a command string, which either adds, removes or shows entries from the notebook.
     *
     * @param input - the command string that should be parsed.
     * @throws IOException    - can produce an IOException if failed to read from a file.
     * @throws ParseException - can produce a ParseException if the input string cannot be parsed correctly.
     */
    public boolean parse(String input) throws IOException, ParseException {
        String[] args = input.split(" ");

        if (args[0].equals("exit")) {
            return false;
        }

        notebookCmd.parse(args);

        if (!params.removedEntries.isEmpty()) {
            remove();
        }

        if (!params.newEntries.isEmpty()) {
            add();
        }

        if (!params.filterParams.isEmpty()) {
            filter();
        }

        if (params.show) {
            show();
        }

        return true;
    }

    /**
     * Adds any entries entered with the -add parameter to the notebook file.
     *
     * @throws IOException - throws if file cannot be written to.
     */
    private void add() throws IOException {
        List<Entry> temp = new ArrayList<>();

        for (int i = 0; i < params.newEntries.size(); i+=2) {
            if (i == params.newEntries.size() - 1) {
                temp.add(new Entry(ft.format(new Date()), params.newEntries.get(i), ""));
            }
            else {
                temp.add(new Entry(ft.format(new Date()), params.newEntries.get(i), params.newEntries.get(i+1)));
            }
        }

        entryArr.addAll(temp);
        mapper.writeValue(notebookFile, entryArr);
        params.newEntries.clear();
    }

    /**
     * Removes any entries entered with the -remove parameter from the json file.
     *
     * @throws IOException - throws if file cannot be written to.
     */
    private void remove() throws IOException {
        for (String name : params.removedEntries) {
            for (Entry e : entryArr) {
                if (e.getName().equals(name)) {
                    entryArr.remove(e);
                    break;
                }
            }
        }
        mapper.writeValue(notebookFile, entryArr);

        params.removedEntries.clear();
    }

    /**
     * Prints any entries filtered by the entered date and keywords.
     *
     * @throws ParseException - if the entered date has wrong formatting.
     */
    private void filter() throws ParseException {
        if (params.filterParams.size() < 2)
            throw new ParameterException("filter param require at least 2 arguments!");

        Date l = ft.parse(params.filterParams.get(0));
        Date r = ft.parse(params.filterParams.get(1));
        params.filterParams.remove(0);
        params.filterParams.remove(0);

        for (Entry e : entryArr) {
            Date d = ft.parse(e.getDate());
            if (d.compareTo(l) >= 0 && d.compareTo(r) <= 0) {
                if (params.filterParams.isEmpty()) System.out.println(e.toStr());
                else {
                    boolean containsAll = true;
                    for (String s : params.filterParams) {
                        if (!e.getName().contains(s)) {
                            containsAll = false;
                            break;
                        }
                    }
                    if (containsAll) System.out.println(e.toStr());
                }
            }
        }

        params.filterParams.clear();
    }

    /**
     * Prints all entries contained in the notebook file.
     */
    private void show() {
        for (Entry e : entryArr) {
            System.out.println(e.toStr());
        }

        params.show = false;
    }
}
