package com.inteliense.jflux.xml;

import com.inteliense.jflux.files.FileObject;
import org.w3c.dom.Document;
import org.w3c.dom.ranges.DocumentRange;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XML {

    DocumentBuilder builder;
    Document document;

    public XML() {
        try {
            builder = build();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public XML(FileObject file) {
        try {
            builder = build();
            document = builder.parse(file.getFile());
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static DocumentBuilder build() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        return factory.newDocumentBuilder();
    }

}
