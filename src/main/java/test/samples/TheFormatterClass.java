package test.samples;

//import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
//import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
//import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import net.steppschuh.markdowngenerator.table.Table;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@MyAnnotation(name="someName",  value = "Hello World")
public class TheFormatterClass {

    public static void main(String[] args) {
        Class aClass = TheFormatterClass.class;//Obtaining the class information of 'TheClass'
        Annotation[] annotations = aClass.getAnnotations();//through that retrieve the annotations

        for(Annotation annotation : annotations){
            if(annotation instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                //System.out.println("name:" + myAnnotation.name());
                //System.out.println("value: " + myAnnotation.value());

                Table.Builder tableBuilder = new Table.Builder()
                        .withAlignments(Table.ALIGN_RIGHT, Table.ALIGN_LEFT)
                        .withRowLimit(7)
                        .addRow("Name", "Value");

                tableBuilder.addRow(myAnnotation.name(), myAnnotation.value());

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

                //tableBuilder.build().setTrimmingIndicator("|");
                Node document = parser.parse(tableBuilder.build().toString());
                String html = renderer.render(document);
                System.out.println("\n");
                System.out.println(html);
            }
        }
    }
}
