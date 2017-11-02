package test.samples;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import net.steppschuh.markdowngenerator.table.Table;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapDemo {

    public static void main(String[] args) {

        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER)
                .addRow("Field Name", "Field Value", "Data Type");

        Map<String, Object> finalMap = new LinkedHashMap<String, Object>();
        finalMap.put("Zara", 8);
        finalMap.put("Mahnaz", 31);
        finalMap.put("Ayan", "12");
        finalMap.put("Daisy", "14");

        /*System.out.println();
        System.out.println(" Map Elements");
        System.out.print("\t" + finalMap);*/

        for (Map.Entry<String, Object> entry : finalMap.entrySet()) {
            tableBuilder.addRow(entry.getKey(), entry.getValue(), entry.getValue().getClass().getSimpleName());
        }

        System.out.println(tableBuilder.build().toString());

        MutableDataSet options = new MutableDataSet();

        options.set(Parser.EXTENSIONS, Arrays.asList(

                TablesExtension.create()

        ));

        options.set(TablesExtension.COLUMN_SPANS, false)
                .set(TablesExtension.MIN_HEADER_ROWS, 1)
                .set(TablesExtension.MAX_HEADER_ROWS, 1)
                .set(TablesExtension.APPEND_MISSING_COLUMNS, true)
                .set(TablesExtension.DISCARD_EXTRA_COLUMNS, true)
                .set(TablesExtension.WITH_CAPTION, false)
                .set(TablesExtension.HEADER_SEPARATOR_COLUMN_MATCH, true);

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(tableBuilder.build().toString());
        String html = renderer.render(document);

        System.out.println("\n");
        System.out.println(html);




    }
}
