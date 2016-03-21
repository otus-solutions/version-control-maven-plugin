package br.org.ccem.maven.rest;

import java.io.IOException;
import java.io.InputStream;

public class ConverterToJson {

    public static String convert(InputStream inputStream) throws IOException {
            java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
            return  s.hasNext() ? s.next() : "";
    }
}
